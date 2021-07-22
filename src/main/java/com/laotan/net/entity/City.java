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
 * @Description: 城市表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="City对象", description="城市表")
@TableName(value = "tb_city")
public class City extends BaseEntity{

    @ApiModelProperty(value = "父级id")
    private Integer pid ;

    @ApiModelProperty(value = "城市名称")
    private String cityname ;

    @ApiModelProperty(value = "层级")
    private Integer type;

}
