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
@Table(name="comm_channel")
public class Channel implements Serializable{

	@ApiModelProperty("ID")
	@Id
	private String id;
	@ApiModelProperty("频道名称")
	private String name;
	@ApiModelProperty("状态")
	private boolean state;
}
