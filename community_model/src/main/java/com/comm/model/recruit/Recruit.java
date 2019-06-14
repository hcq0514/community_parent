package com.comm.model.recruit;

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
@Table(name="comm_recruit")
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Recruit implements Serializable {

	@Id
    @GeneratedValue(generator="jpa-uuid")
	private String id;
	@ApiModelProperty("//职位名称")
	private String jobName;
	@ApiModelProperty("//薪资范围")
	private String salary;
	@ApiModelProperty("//经验要求")
	private String workYears;
	@ApiModelProperty("//学历要求")
	private String education;
	@ApiModelProperty("//任职方式")
	private String type;
	@ApiModelProperty("//办公地址")
	private String address;
	@ApiModelProperty("//企业ID")
	private String enterpriseId;
	@ApiModelProperty("//创建日期")
	private java.util.Date createTime;
	@ApiModelProperty("//状态")
	private Boolean state;
	@ApiModelProperty("//是否推荐")
	private Boolean recommend;
	@ApiModelProperty("//网址")
	private String url;
	@ApiModelProperty("//标签")
	private String label;
	@ApiModelProperty("//职位描述")
	private String description;
	@ApiModelProperty("//职位要求")
	private String requireSkill;
}
