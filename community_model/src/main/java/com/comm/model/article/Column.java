package com.comm.model.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Data
@Entity
@Table(name="comm_column")
public class Column implements Serializable{

	@ApiModelProperty("ID")
	@Id
	private String id;
	@ApiModelProperty("专栏名称")
	private String name;
	@ApiModelProperty("专栏简介")
	private String summary;
	@ApiModelProperty("用户ID")
	private String userId;
	@ApiModelProperty("申请日期")
	private java.util.Date createTime;
	@ApiModelProperty("审核日期")
	private java.util.Date checkTime;
	@ApiModelProperty("状态")
	private boolean state;
}
