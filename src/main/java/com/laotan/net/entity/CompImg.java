package com.laotan.net.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 15:10
 * @Description:企业相册表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="CompImg对象", description="企业相册表")
@TableName(value = "tb_comp_img")
public class CompImg extends BaseEntity{

    @ApiModelProperty(value = "图片地址")
    private String url ;

    @ApiModelProperty(value = "图片附件")
    @TableField(exist = false)
    private MultipartFile imgFile;

    @ApiModelProperty(value = "图片名称")
    private String imgName ;

    @ApiModelProperty(value = "所属公司id")
    private Integer compId ;


}
