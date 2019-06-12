package com.comm.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
	private String id;
	@ApiModelProperty("登陆名称")
	private String loginName;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("状态")
	private boolean state;
}
