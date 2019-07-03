package com.comm.image.controller;

import com.aliyun.oss.OSSClient;
import com.comm.image.service.AwsS3Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aws")
public class AWSController {

    @Autowired
    AwsS3Wrapper awsS3Wrapper;

    @GetMapping("token")
    @ResponseBody
    public void getToken() throws Exception {
        String token = awsS3Wrapper.getToken();
        System.out.println(token);

    }


}
