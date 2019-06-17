package com.comm.model.sms.dto;

import lombok.Data;

@Data
public class SendMessageResult {

	private boolean success;

	private String failCode;


	public SendMessageResult(boolean status) {
		this.success = status;
	}

	public SendMessageResult(boolean status,String failCode) {
		this.success = status;
		this.failCode = failCode;
	}

}
