package com.comm.model.sms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuerySendResult {

	/**
	 * 查询是否成功
	 */
	private boolean success;

	private byte sendStatus;

	private String content;

	private Date receiveDate;

	private String failCode;

	public QuerySendResult(boolean success, byte sendStatus, String content, Date receiveDate, String failCode) {
		this.success = success;
		this.sendStatus = sendStatus;
		this.content = content;
		this.receiveDate = receiveDate;
		this.failCode = failCode;
	}

}
