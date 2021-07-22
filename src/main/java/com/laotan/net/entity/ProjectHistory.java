package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 项目经历表
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ProjectHistory对象", description="项目经历表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_project_history")
public class ProjectHistory extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目时间(原型上格式为yyyy-MM-dd ~ yyyy-MM-dd，客户端拼接字符串传过来)")
    private String projectTime;

    @ApiModelProperty(value = "项目描述")
    private String projectDetail;
}
