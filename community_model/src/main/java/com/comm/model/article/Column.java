package com.comm.model.article;

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
@Table(name="comm_column")
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Column implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(generator="jpa-uuid")
	private String id;
	@ApiModelProperty("专栏名称")
	private String name;
	@ApiModelProperty("专栏简介")
	private String summary;
	@ApiModelProperty("用户ID")
	private String userId;
	@ApiModelProperty("申请日期")
	private LocalDateTime createTime;
	@ApiModelProperty("审核日期")
	private LocalDateTime checkTime;
	@ApiModelProperty("状态")
	private Boolean state;
}
