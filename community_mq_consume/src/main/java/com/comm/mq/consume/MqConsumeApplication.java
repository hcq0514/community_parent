package com.comm.mq.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.comm.*")
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
public class MqConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsumeApplication.class);
    }

}
