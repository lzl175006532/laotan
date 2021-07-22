package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="JobIntention对象", description="求职意向表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_job_intention")
public class JobIntention extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "求职类型：全职、兼职、实习")
    private String type;

    @ApiModelProperty(value = "工作城市")
    private String workCity;

    @ApiModelProperty(value = "工作城市Id")
    private Integer workCityId;

    @ApiModelProperty(value = "期望职位名称")
    private String expectJobName;

    @ApiModelProperty(value = "期望职位id")
    private Integer expectJobId;

    @ApiModelProperty(value = "期望行业名称")
    private String expectVocationName;

    @ApiModelProperty(value = "期望行业Id")
    private String expectVocationId;

    @ApiModelProperty(value = "期望薪资名称")
    private String expectMoneyName;

    @ApiModelProperty(value = "期望薪资Id")
    private String expectMoneyId;
}
