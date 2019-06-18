package com.comm.model.sms.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Map;


@Data
public class MessageDTO {

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 模板id
	 */
	private String templateId;

    @ApiParam("应用id")
    private String appId;

    @ApiParam("是否为验证码")
    private Boolean captcha;


	/**
	 * 短信参数，不超过200个字符
	 */
	private Map<String, Object> params;



}
