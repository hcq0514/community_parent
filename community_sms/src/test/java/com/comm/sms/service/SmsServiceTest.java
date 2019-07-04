package com.comm.sms.service;

import com.comm.sms.SmsApplication;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
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
    public void init() throws Exception {
            String response = null;
            try {
                AndroidNotification androidNotification = AndroidNotification.builder()
                        .setTitle("hello")
                        .setBody("77777")
                        .setClickAction("notification_hbopen")
                        .build();
                AndroidConfig androidConfig = AndroidConfig.builder()
                        .setNotification(androidNotification)
                        .build();
                String registrationToken = "daHpgP8dV5Q:APA91bGJEt_Ymd5CIOe6u3kWTI8UEqSMHzvfvCRusig_vPQf5vW4zY5o9VUHNobZnv3aiq9fKvjFJgj-KRXJWYZKdhTKUwUBXT0u9kmkTDewFp3RXHqu6-h_v4PYN1RNzUeHqjlrXmtE";
                Message message = Message.builder()
                        .setAndroidConfig(androidConfig)
                        .setToken(registrationToken)
                        .build();
                response = FirebaseMessaging.getInstance().send(message);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
    }
