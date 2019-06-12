package com.comm.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "comm_label")
@Data
public class Label implements Serializable {
    @Id
    private String id;
    @ApiModelProperty(" 标签名称")
    private String labelName;
    @ApiModelProperty("状态")
    private boolean state;
    @ApiModelProperty(" 使用数量")
    private Long useCount;
    @ApiModelProperty(" 关注数")
    private Long fans;
    @ApiModelProperty(" 是否推荐")
    private boolean recommend;
}
