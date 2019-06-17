package com.comm.model.sms.dto;

import lombok.Data;

@Data
public class ValidateCodeDTO {

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 模板id
	 */
	private String templateId;

	/**
	 * 验证码
	 */
	private String validateCode;

}
