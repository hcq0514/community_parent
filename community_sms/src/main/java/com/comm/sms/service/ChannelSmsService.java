package com.comm.sms.service;


import com.comm.model.sms.App;
import com.comm.model.sms.Sms;
import com.comm.model.sms.Template;
import com.comm.model.sms.dto.SendSmsResult;

public interface ChannelSmsService {

	/**
	 * 使用模版来发送短信调用具体的短信平台发送短信
	 * @param app 应用
	 * @param template 消息
	 * @param sms
	 * @return
	 */
	SendSmsResult sendByTemplate(App app, Template template, Sms sms) ;

	/**
	 * 使用模版来发送短信调用具体的短信平台发送短信
	 * @param app 应用
	 * @param sms 消息
	 * @return
	 */
	SendSmsResult sendByContent(App app, Sms sms) ;


	/**
	 * 获取短信渠道名称
	 * @return
	 */
	String getChannel();

}
