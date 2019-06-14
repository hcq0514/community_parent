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
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "comm_problem")
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Problem implements Serializable {

    @Id
    @GeneratedValue(generator="jpa-uuid")
    private String id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;
    @ApiModelProperty("修改日期")
    private LocalDateTime updateTime;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("浏览量")
    private Long visitCount;
    @ApiModelProperty("点赞数")
    private Long likeCount;
    @ApiModelProperty("回复数")
    private Long answerCount;
    @ApiModelProperty("是否解决")
    private Boolean solve;
    @ApiModelProperty("回复人昵称")
    private String answerName;
    @ApiModelProperty("回复日期")
    private LocalDateTime answerTime;
}
