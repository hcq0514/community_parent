package com.comm.model.sms.dto;

import lombok.Data;

@Data
public class SendMessageResult {

	private boolean status;

	private String response;

	public SendMessageResult(boolean status,String response) {
		this.status = status;
		this.response = response;
	}

}
