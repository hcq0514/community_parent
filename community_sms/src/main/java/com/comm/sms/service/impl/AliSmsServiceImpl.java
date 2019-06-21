package com.comm.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.comm.model.sms.App;
import com.comm.model.sms.Sms;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.SendSmsResult;
import com.comm.sms.constant.SmsChannels;
import com.comm.sms.service.ChannelSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(SmsChannels.ALI_SMS)
public class AliSmsServiceImpl implements ChannelSmsService {

    private static final Logger logger = LoggerFactory.getLogger(AliSmsServiceImpl.class);

    @Value("${sms.aliyun.regionId}")
    private String regionId;

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Autowired
    private IAcsClient client;


    @Override
    public SendSmsResult sendByTemplate(App app, Template template, Sms sms) {

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", sms.getMobile());
        request.putQueryParameter("SignName", app.getSignName());
        request.putQueryParameter("TemplateCode", template.getChannelTemplateId());
        request.putQueryParameter("TemplateParam", sms.getParams());
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            return new SendSmsResult(false, e.getMessage());
        }
        if (Objects.requireNonNull(response).getHttpResponse().isSuccess()) {
            return new SendSmsResult(true, response.getData());
        } else {
            return new SendSmsResult(false, response.getData());
        }
    }

    @Override
    public SendSmsResult sendByContent(App app, Sms sms) {
        return null;
    }


    @Override
    public String getChannel() {
        return SmsChannels.ALI_SMS;
    }

}
