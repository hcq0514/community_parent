package com.comm.image.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : hcq
 * @date : 2019/7/3
 */
@Configuration
public class AliyunOSSConfig {

    @Value("${aliyunoss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunoss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunoss.endpoint}")
    private String endpoint;

    @Bean
    public OSSClient init() {
        // 创建OSSClient实例。
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

}
