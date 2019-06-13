package com.comm.model.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Channel implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(generator="jpa-uuid")
	private String id;
	@ApiModelProperty("频道名称")
	private String name;
	@ApiModelProperty("状态")
	private Boolean state;
}
