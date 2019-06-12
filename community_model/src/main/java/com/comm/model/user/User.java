package com.comm.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name="comm_user")
public class User implements Serializable{

	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	private String id;
	@ApiModelProperty("//手机号码")
	private String mobile;
	@ApiModelProperty("//密码")
	private String password;
    @ApiModelProperty("//昵称")
    private String nickname;
    @ApiModelProperty("//性别")
    private String sex;
    @ApiModelProperty("//出生年月日")
    private java.util.Date birthday;
    @ApiModelProperty("//头像")
    private String avatar;
    @ApiModelProperty("//E-Mail")
    private String email;
    @ApiModelProperty("///注册日期")
    private java.util.Date regdate;
    @ApiModelProperty("//修改日期")
    private java.util.Date updatedate;
    @ApiModelProperty("///最后登陆日期")
    private java.util.Date lastdate;
    @ApiModelProperty("//在线时长（分钟）")
    private Long online;
    @ApiModelProperty("//兴趣")
    private String interest;
    @ApiModelProperty("//个性")
    private String personality;
    @ApiModelProperty("//粉丝数")
    private Integer fanscount;
    @ApiModelProperty("//关注数")
    private Integer followcount;
}
