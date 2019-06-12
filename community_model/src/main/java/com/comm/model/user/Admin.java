package com.comm.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Data
@Entity
@Table(name="comm_admin")
public class Admin implements Serializable{

	@Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	private String id;
	@ApiModelProperty("登陆名称")
	private String loginName;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("状态")
	private boolean state;
}
