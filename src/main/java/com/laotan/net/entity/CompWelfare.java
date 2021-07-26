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
 * @Description:企业相册表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="CompWelfare对象", description="企业福利")
@TableName(value = "tb_comp_welfare")
public class CompWelfare extends BaseEntity{

    @ApiModelProperty(value = "福利名称")
    private String name ;

    @ApiModelProperty(value = "福利类型：自定义-CUSTOM，默认-DEFAULT")
    private String type ;

    @ApiModelProperty(value = "所属公司id")
    private Integer compId ;
}
