package com.comm.model.qa;

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
 * 实体类
 * @author Administrator
 *
 */
@Data
@Entity
@Table(name="comm_answer")
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Answer implements Serializable{

	@ApiModelProperty("编号")
	@Id
    @GeneratedValue(generator="jpa-uuid")
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
	private LocalDateTime createTime;
	@ApiModelProperty("更新日期")
	private LocalDateTime updateTime;
}
