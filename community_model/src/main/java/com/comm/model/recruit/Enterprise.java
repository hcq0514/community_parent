package com.comm.model.recruit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "comm_enterprise")
public class Enterprise implements Serializable {

    @Id
    private String id;
    @ApiModelProperty("企业名称")
    private String name;
    @ApiModelProperty("企业简介")
    private String summary;
    @ApiModelProperty("企业地址")
    private String address;
    @ApiModelProperty("标签列表")
    private String labels;
    @ApiModelProperty("坐标")
    private String coordinate;
    @ApiModelProperty("是否热门")
    private boolean hot;
    @ApiModelProperty("LOGO")
    private String logo;
    @ApiModelProperty("职位数")
    private Integer jobCount;
    @ApiModelProperty("URL")
    private String url;
}
