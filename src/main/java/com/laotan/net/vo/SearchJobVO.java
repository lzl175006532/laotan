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

    @ApiModelProperty(value = "公司名称")
    private String compName;

    @ApiModelProperty(value = "职位名称")
    private String jobName;

    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    @ApiModelProperty(value = "手机号")
    private String cellPhone;

    @ApiModelProperty(value = "用户身份（USER-应聘者，BOSS-招聘者）")
    private String identity;

}
