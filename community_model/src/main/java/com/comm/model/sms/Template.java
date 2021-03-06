package com.comm.model.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_template")
@Accessors(chain = true)
public class Template {

    @ApiModelProperty("短信模板id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("第三方模板Id")
    private String channelTemplateId;

    @ApiModelProperty("模板名称")
    private String name;

    @ApiModelProperty("短信类型 0代表验证码，1代表通用")
    private Integer type;

    @ApiModelProperty("模板内容")
    private String content;

    @ApiModelProperty("默认应用id")
    private Integer appId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
