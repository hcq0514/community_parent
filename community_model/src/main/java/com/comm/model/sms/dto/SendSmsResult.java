package com.comm.model.sms.dto;

import lombok.Data;

@Data
public class SendSmsResult {

	private boolean status;

	private String response;

	public SendSmsResult(boolean status, String response) {
		this.status = status;
		this.response = response;
	}

}
