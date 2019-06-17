package com.comm.model.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("短信参数")
    private String params;

    @ApiModelProperty("短信系统生成的验证码")
    private String validateCode;

    @ApiModelProperty("短信系统生成的验证码")
    private Integer templateId;

    @ApiModelProperty("发送状态（0：失败，1：接口调用成功，2：发送成功）")
    private Integer sendStatus;

    @ApiModelProperty("短信平台发送失败代码")
    private String failCode;

    @ApiModelProperty("短信发送渠道")
    private String channel;

    @ApiModelProperty("重试次数")
    private Byte retry;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
