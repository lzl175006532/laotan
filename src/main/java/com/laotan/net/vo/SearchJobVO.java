package com.laotan.net.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Copyright 通泰信诚
 * @Author lizilong
 * @Description 查询职位VO
 * @Date 16:40$ 2021/7/23$
 **/
@Data
public class SearchJobVO {

    @ApiModelProperty(value = "分页对象")
    private Page page;

    @ApiModelProperty(value = "模糊查询，傻瓜式搜索：职位名称、公司名称、工作地址什么的和这个匹配上就查出来")
    private String keyword;

    @ApiModelProperty(value = "公司名称(如果按公司名称查询公司，此字段不能为空)")
    private String compName;

    @ApiModelProperty(value = "职位名称")
    private String jobName;

    @ApiModelProperty(value = "工作地点-省id")
    private Integer provinceId ;

    @ApiModelProperty(value = "工作地点-市id")
    private Integer cityId ;

    @ApiModelProperty(value = "工作地点-县/区id")
    private Integer countyId ;

    @ApiModelProperty(value = "手机号")
    private String cellPhone;

    @ApiModelProperty(value = "登录token")
    private String token;

    @ApiModelProperty(value = "用户身份（USER-应聘者，BOSS-招聘者）")
    private String identity;

}
