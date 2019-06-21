package com.comm.sms.service;

import com.comm.sms.SmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hcq
 * @date : 2019/6/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SmsApplication.class, SmsServiceTest.class})
public class SmsServiceTest {

    @Autowired
    SmsService smsService;


    @Test
    public void init() {
    }
}
