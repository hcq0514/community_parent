package com.comm.model.sms.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BatchMessageDTO {

    /**
     * 手机号码
     */
    private Set<String> mobile;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 短信内容
     */
    private String content;
}
