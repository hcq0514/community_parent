package com.comm.model.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "t_app")
public class App {

    @ApiModelProperty("应用id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("应用名称")
    private String name;

    @ApiModelProperty("签名私钥")
    private String privateKey;

    @ApiModelProperty("短信签名")
    private String signName;

    @ApiModelProperty("第三方渠道SDK配置参数")
    private String channelParams;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}
