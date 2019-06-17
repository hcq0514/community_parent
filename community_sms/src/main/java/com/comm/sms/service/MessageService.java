package com.comm.sms.service;


import com.comm.model.sms.App;
import com.comm.model.sms.Message;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.MessageDTO;
import com.comm.model.sms.dto.SendMessageResult;
import com.comm.sms.constant.SmsStatus;
import com.comm.sms.dao.AppDao;
import com.comm.sms.dao.MessageDao;
import com.comm.sms.dao.TemplateDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private AppDao appMapper;

    @Autowired
    private TemplateDao templateMapper;

    @Autowired
    private MessageDao messageMapper;

    @Autowired
    private ChannelSMSServices channelSMSServices;


    private static BlockingQueue<MessageDTO> smsBlockingQueue = new LinkedBlockingDeque<>();

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);


    public SendMessageResult send(MessageDTO messageDTO) {
        Integer templateId = messageDTO.getTemplateId();
        Optional<Template> templateOptional = templateMapper.findById(templateId);
        if (!templateOptional.isPresent()) {
//            throw new BaseException(BaseResultEnum.TEMPLATE_NOT_EXIST);
        }
        Optional<App> appOptional = appMapper.findById(messageDTO.getAppId());
        if (!appOptional.isPresent()) {
//            throw new BaseException(BaseResultEnum.APP_NOT_EXIST);
        }
        Map<String, Object> params = messageDTO.getParams();
        logger.info("开始发送短信:{}", templateOptional.get());
        Iterator<ChannelSmsService> iterator = channelSMSServices.iterator();
        SendMessageResult result;
        byte retry = 0;
        String channel;
        do {
            ChannelSmsService channelSMSService = iterator.next();
            channel = channelSMSService.getChannel();
            try {
                result = channelSMSService.send(appOptional.get(), templateOptional.get(), messageDTO);
                break;
            } catch (Exception e) {
                logger.error("渠道短信发送异常", e);
                result = new SendMessageResult(false, e.getMessage());
                if (iterator.hasNext()) {
                    retry++;
                }
            }
        } while (iterator.hasNext());

        try {
            Message message = new Message();
            message.setMobile(messageDTO.getMobile());
            message.setParams(new ObjectMapper().writeValueAsString(params));
            message.setTemplateId(templateOptional.get().getId());
            if (result.isStatus()) {
                message.setSendStatus(SmsStatus.SENDING);
            } else {
                message.setSendStatus(SmsStatus.FAILED);
            }
            if (retry > 0) {
                message.setRetry(retry);
            }
            message.setResponse(result.getResponse());
            message.setChannel(channel);
            message.setCreateTime(LocalDateTime.now());
            message.setUpdateTime(LocalDateTime.now());
            Message save = messageMapper.save(message);
            logger.info("{}插入结果:{},生成的自增id为:{}", message, message.getId());
        } catch (Exception e) {
            logger.error("插入Message数据异常" + messageDTO, e);
        }
        return result;
    }


    public void sendSms(MessageDTO messageDTO) throws InterruptedException {
        smsBlockingQueue.put(messageDTO);
    }

    @PostConstruct
    public void init() {
        fixedThreadPool.submit(() -> {
            //监听短信队列
            while (true) {
                if (smsBlockingQueue.size() > 0) {
                    System.out.println("队列添加一条数据");
                    send(smsBlockingQueue.poll());
                }
            }
        });
    }
}

