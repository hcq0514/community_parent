package com.comm.sms.service;

import com.comm.model.sms.dto.MessageDTO;
import com.comm.sms.SmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        messageService.send(messageDTO);
    }

    @Test
    public void sendSms() {
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
