package com.comm.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "comm_city")
@Data
@GenericGenerator(name="jpa-uuid", strategy="uuid")
public class City implements Serializable {
    @Id
    @GeneratedValue(generator="jpa-uuid")
    private String id;
    @ApiModelProperty("城市名称")
    private String name;
    @ApiModelProperty("是否热门")
    private Boolean hot;
}
