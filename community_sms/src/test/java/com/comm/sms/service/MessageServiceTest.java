package com.comm.sms.service;

import com.comm.model.sms.dto.MessageDTO;
import com.comm.sms.SmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author : hcq
 * @date : 2019/6/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SmsApplication.class,MessageServiceTest.class})
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void send() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAppId(1);
        messageDTO.setMobile("13113051027");
        messageDTO.setTemplateId(1);
        Map map = new HashMap();
        map.put("code","1442");
        map.put("phone","131130501027");
        messageDTO.setParams(map);
        messageService.send(messageDTO);
    }

    @Test
    public void sendSms() throws InterruptedException {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAppId(1);
        messageDTO.setMobile("13113051027");
        messageDTO.setTemplateId(1);
        messageService.sendSms(messageDTO);
    }

    @Test
    public void init() {
    }
}
