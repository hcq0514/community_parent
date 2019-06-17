package com.comm.sms.controller;

import com.comm.model.sms.dto.MessageDTO;
import com.comm.sms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : hcq
 * @date : 2019/6/17
 */
@Controller
@RequestMapping("answer")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("test")
    public void add() throws InterruptedException {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAppId(1);
        messageDTO.setMobile("13113051027");
        messageDTO.setTemplateId(1);
        messageService.sendSms(messageDTO);
    }
}
