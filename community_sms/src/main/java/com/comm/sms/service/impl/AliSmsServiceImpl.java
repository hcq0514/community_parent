package com.comm.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.comm.model.sms.App;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.MessageDTO;
import com.comm.model.sms.dto.SendMessageResult;
import com.comm.sms.constant.Channels;
import com.comm.sms.service.ChannelSmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Service(Channels.ALI_SMS)
public class AliSmsServiceImpl implements ChannelSmsService {

    private static final Logger logger = LoggerFactory.getLogger(AliSmsServiceImpl.class);

    @Value("${sms.aliyun.regionId}")
    private String regionId;

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    private IAcsClient client;

    @PostConstruct
    public void initConfig() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
    }

    @Override
    public SendMessageResult send(App app, Template template, MessageDTO message) {

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", message.getMobile());
        request.putQueryParameter("SignName", "cq");
        request.putQueryParameter("TemplateCode",template.getChannelTemplateId());

        Map<String, Object> params = message.getParams();
        try {
            request.putQueryParameter("TemplateParam", new ObjectMapper().writeValueAsString(params));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
//        做短信数量限制
        if (Objects.requireNonNull(response).getHttpResponse().isSuccess()) {
            return new SendMessageResult(true, response.getData());
        } else {
            logger.warn("阿里云通讯短信发送失败,原因:{},错误代码:{},请求Id:{}", response.getData());
            return new SendMessageResult(false, response.getData());
        }
    }


    @Override
    public SendMessageResult batchSend(App app, String[] mobile, String content) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getChannel() {
        return Channels.ALI_SMS;
    }

}
