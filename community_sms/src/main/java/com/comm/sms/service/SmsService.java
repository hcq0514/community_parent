package com.comm.sms.service;


import com.alibaba.fastjson.JSONObject;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.SmsExceptionCode;
import com.comm.model.sms.App;
import com.comm.model.sms.Sms;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.SendSmsResult;
import com.comm.sms.constant.SmsStatus;
import com.comm.sms.dao.AppDao;
import com.comm.sms.dao.SmsDao;
import com.comm.sms.dao.TemplateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static com.comm.common.redis.RedisKeyConstant.COMM_SMS_APP;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private AppDao appDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private SmsDao smsDao;

    @Autowired
    private ChannelSMSServices channelSMSServices;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    private static BlockingQueue<Sms> smsBlockingQueue = new LinkedBlockingDeque<>();

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public void sendSmsByContent(String mobile, String content, int appcode, int cloned, boolean captcha) {
        try {
            Sms sms = new Sms()
                    .setMobile(mobile)
                    .setContent(content)
                    .setSendStatus(SmsStatus.CREATED)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
            smsDao.save(sms);
            logger.info("写入消息进阻塞队列，sms:[{}]", sms);
            smsBlockingQueue.put(sms);
        } catch (Exception e) {
            logger.error("写入message失败,exception:[{}]", e.getMessage());
        }
    }

    public void sendSmsByTemplate(String mobile, long templateId, int appcode, int cloned, boolean captcha, Map params) {
        try {
            Sms sms = new Sms()
                    .setMobile(mobile)
                    .setTemplateId(templateId)
                    .setSendStatus(SmsStatus.CREATED)
                    .setParams(JSONObject.toJSONString(params))
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
            smsDao.save(sms);
            logger.info("写入消息进阻塞队列，sms:[{}]", sms);
            smsBlockingQueue.put(sms);
        } catch (Exception e) {
            logger.error("写入message失败,exception:[{}]", e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        fixedThreadPool.submit(() -> {
            //监听短信队列
            while (true) {
                if (smsBlockingQueue.size() > 0) {
                    Sms sms = smsBlockingQueue.poll();
                    try {
                        logger.info("开始消费队列消息，threadId:[{}],sms:[{}]", Thread.currentThread().getId(), sms);
                        send(sms);
                    } catch (Exception e) {
                        logger.info("队列消费失败,sms:[{}],exception:[{}]", sms, e.getMessage());
                    }
                }
            }
        });
    }


    private SendSmsResult send(Sms sms) {
        String appKey = COMM_SMS_APP.setArg(sms.getAppId());
        String appVal = stringRedisTemplate.opsForValue().get(appKey);
        App app = null;
        if (StringUtils.isEmpty(appVal)) {
            Optional<App> appOptional = appDao.findById(sms.getAppId());
            if (!appOptional.isPresent()) {
                ExceptionCast.cast(SmsExceptionCode.SMS_APP_NOT_EXIST);
            }
            stringRedisTemplate.opsForValue().set(appKey, JSONObject.toJSONString(appOptional.get()));
        } else {
            app = JSONObject.parseObject(appVal, App.class);
        }
        logger.info("开始发送短信:{}", sms);
        Iterator<ChannelSmsService> iterator = channelSMSServices.iterator();
        SendSmsResult result;
        int retryNum = 0;
        String channel;
        do {
            ChannelSmsService channelSMSService = iterator.next();
            channel = channelSMSService.getChannel();
            try {
                //判断根据模版发送还是根据内容发送(模版发送几乎少用，为增加代码可读性，此处不使用redis)
                if (sms.getTemplateId() != null) {
                    Optional<Template> templateOptional = templateDao.findById(sms.getTemplateId());
                    if (!templateOptional.isPresent() ) {
                        ExceptionCast.cast(SmsExceptionCode.SMS_TEMPLATE_NOT_EXIST);
                    }
                    result = channelSMSService.sendByTemplate(app, templateOptional.get(), sms);
                } else {
                    result = channelSMSService.sendByContent(app, sms);
                }
                break;
            } catch (Exception e) {
                result = new SendSmsResult(false, e.getMessage());
                if (iterator.hasNext()) {
                    retryNum++;
                }
            }
        } while (iterator.hasNext());

        if (result.isStatus()) {
            sms.setSendStatus(SmsStatus.SUCCESS);
            logger.info("短信发送成功，sms:[{}]", sms);
        } else {
            sms.setSendStatus(SmsStatus.FAILED);
            logger.info("短信发送失败，smsId:[{}],e:[{}]", sms.getId(), result.getResponse());
        }
        sms.setRetry(retryNum);
        sms.setResponse(result.getResponse());
        sms.setChannel(channel);
        sms.setUpdateTime(LocalDateTime.now());
        smsDao.save(sms);
        return result;
    }
}


