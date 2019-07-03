package com.comm.image.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/aliyun")
public class AliyunController {

    @Value("${aliyunoss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunoss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunoss.endpoint}")
    private String endpoint;

    @Value("${aliyunoss.bucketName}")
    private String bucketName;


    @GetMapping("upload")
    @ResponseBody
    public String testUpload() {

        String objectName = "test";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

// 关闭OSSClient。
        ossClient.shutdown();
        return "上传成功";
    }
}
