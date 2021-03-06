package com.laotan.net.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 15:10
 * @Description:发布职位表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="PublishJob对象", description="发布职位表")
@TableName(value = "tb_publish_job")
public class PublishJob extends BaseEntity{

    @ApiModelProperty(value = "发布人id")
    private Integer bossId ;

    @ApiModelProperty(value = "公司名称")
    private String compName ;

    @ApiModelProperty(value = "公司id")
    private String compId ;

    @ApiModelProperty(value = "职位名称")
    private String jobName ;

    @ApiModelProperty(value = "职位描述")
    private String detail ;

    @ApiModelProperty(value = "工作地点-省id")
    private Integer addressProvinceId ;

    @ApiModelProperty(value = "工作地点-市id")
    private Integer addressCityId ;

    @ApiModelProperty(value = "工作地点-县/区id")
    private Integer addressCountyId ;

    @ApiModelProperty(value = "工作地点详细信息")
    private String addressDetail ;

    @ApiModelProperty(value = "经验要求")
    private String experienceRequire ;

    @ApiModelProperty(value = "学历要求")
    private String educationRequire ;

    @ApiModelProperty(value = "薪资范畴")
    private String salaryCategory ;

}
