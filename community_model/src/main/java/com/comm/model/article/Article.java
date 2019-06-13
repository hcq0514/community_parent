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
@Table(name="comm_article")
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class Article implements Serializable{

	@ApiModelProperty("ID")
	@Id
	@GeneratedValue(generator="jpa-uuid")
	private String id;
	@ApiModelProperty("专栏ID")
	private String columnId;
	@ApiModelProperty("用户ID")
	private String userId;
	@ApiModelProperty("标题")
	private String title;
	@ApiModelProperty("文章正文")
	private String content;
	@ApiModelProperty("文章封面")
	private String image;
	@ApiModelProperty("发表日期")
	private LocalDateTime createTime;
	@ApiModelProperty("修改日期")
	private LocalDateTime updateTime;
	@ApiModelProperty("是否公开")
	private Boolean open;
	@ApiModelProperty("是否置顶")
	private Boolean top;
	@ApiModelProperty("浏览量")
	private Integer visitCount;
	@ApiModelProperty("点赞数")
	private Integer likeCount;
	@ApiModelProperty("评论数")
	private Integer comment;
	@ApiModelProperty("审核状态")
	private boolean state;
	@ApiModelProperty("所属频道")
	private String channelId;
	@ApiModelProperty("URL")
	private String url;
	@ApiModelProperty("类型")
	private String type;
}
