package com.comm.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.comm.*")
@EntityScan("com.comm.model.*")
@EnableSwagger2
public class ActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class);
    }


}
