package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Boss信息对象", description="老板信息表（招聘者）")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_boss")
public class Boss extends BaseEntity {

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String cellPhone;

    @ApiModelProperty(value = "企业全称")
    private String companyName;

    @ApiModelProperty(value = "职位")
    private String position;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "个人邮箱")
    private String email;

    @ApiModelProperty(value = "个性签名")
    private String advantage;

    @ApiModelProperty(value = "公司信息")
    @TableField(exist = false)
    private Company company;



}
