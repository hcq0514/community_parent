package com.comm.model.qa;

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
@Table(name="comm_answer")
public class Answer implements Serializable{

	@ApiModelProperty("编号")
	@Id
	private String id;
	@ApiModelProperty("问题ID")
	private String problemId;
	@ApiModelProperty("回答内容")
	private String content;
	@ApiModelProperty("回答人ID")
	private String userId;
	@ApiModelProperty("回答人昵称")
	private String nickName;

	@ApiModelProperty("创建日期")
	private java.util.Date createTime;
	@ApiModelProperty("更新日期")
	private java.util.Date updateTime;
}
