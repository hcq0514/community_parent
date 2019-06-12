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
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Admin implements Serializable{

	@Id
    @GeneratedValue(generator="jpa-uuid")
	private String id;
	@ApiModelProperty("登陆名称")
	private String loginName;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("状态")
	private boolean state;
}
