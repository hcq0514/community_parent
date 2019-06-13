package com.comm.model.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/6/13
 */
@Data
@Entity
@Table(name = "comm_activity")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Activity implements Serializable {

    @ApiModelProperty("ID")
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("活动名称")
    private String name;
    @ApiModelProperty("大会简介")
    private String summary;
    @ApiModelProperty("详细说明")
    private String detail;
    @ApiModelProperty("主办方")
    private String sponsor;
    @ApiModelProperty("活动图片")
    private String image;
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("报名截止时间")
    private LocalDateTime lastApplyTime;
    @ApiModelProperty("举办地点")
    private String address;
    @ApiModelProperty("是否可见")
    private Boolean state;
    @ApiModelProperty("城市id")
    private String cityId;
}
