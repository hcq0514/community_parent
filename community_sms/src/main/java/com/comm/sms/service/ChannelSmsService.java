package com.comm.sms.service;


import com.comm.model.sms.App;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.MessageDTO;
import com.comm.model.sms.dto.SendMessageResult;

public interface ChannelSmsService {

	/**
	 * 调用具体的短信平台发送短信
	 * @param app
	 * @param template
	 * @param message
	 * @return
	 */
	SendMessageResult send(App app, Template template, MessageDTO message) ;

	/**
	 * 调用具体的短信平台批量发送短信
	 * @param app
	 * @param mobile
	 * @param content
	 * @return
	 */
	SendMessageResult batchSend(App app, String[] mobile, String content) ;

	/**
	 * 获取短信渠道名称
	 * @return
	 */
	String getChannel();

}
