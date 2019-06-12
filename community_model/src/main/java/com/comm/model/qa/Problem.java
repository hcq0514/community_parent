package com.comm.model.qa;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "comm_problem")
public class Problem implements Serializable {

    @Id
    private String id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("创建日期")
    private java.util.Date createTime;
    @ApiModelProperty("修改日期")
    private java.util.Date updateTime;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("浏览量")
    private Long visitCount;
    @ApiModelProperty("点赞数")
    private Long likeCount;
    @ApiModelProperty("回复数")
    private Long reply;
    @ApiModelProperty("是否解决")
    private boolean solve;
    @ApiModelProperty("回复人昵称")
    private String replyName;
    @ApiModelProperty("回复日期")
    private java.util.Date replyTime;
}
