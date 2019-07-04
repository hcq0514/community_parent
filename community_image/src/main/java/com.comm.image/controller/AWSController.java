package com.comm.image.controller;

import com.comm.image.service.impl.AwsS3FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aws")
public class AWSController {

    @Autowired
    AwsS3FileServiceImpl awsS3Wrapper;

    @GetMapping("token")
    @ResponseBody
    public void getToken() throws Exception {
        String token = awsS3Wrapper.getToken();
        System.out.println(token);

    }


}
