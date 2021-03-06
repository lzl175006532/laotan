package com.laotan.net.entity;


import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description:账号表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="Account对象", description="用户登录账号表")
@TableName(value = "tb_account")
public class Account extends BaseEntity{

    @ApiModelProperty(value = "用户手机号(账号)")
    private String cellPhone ;

    @ApiModelProperty(value = "短信验证码")
    private String verifyCode;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "应聘者信息完善状态，默认为N，表示应聘者没有完善简历，如果为Y表示简历已经创建完成")
    private String userInitInfo;

    @ApiModelProperty(value = "招聘者信息完善状态，默认为N，表示招聘者没有完善简历，如果为Y表示公司信息已经完成")
    private String bossInitInfo;

    @ApiModelProperty(value = "登录成功之后生成token")
    private String token;

    @TableField(exist = false)
    @ApiModelProperty(value = "登录类型（PASSWORD-手机号密码登录，VERIFYCODE-手机号验证码登录）")
    private String type;

}
