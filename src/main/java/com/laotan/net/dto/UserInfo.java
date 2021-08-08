package com.laotan.net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserInfo {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "第一行显示，格式：工作 10 年 · 30岁  本科  在职")
    private String no1Value;

    @ApiModelProperty(value = "状态:在职、离职、在校")
    private String status;

    @ApiModelProperty(value = "手机号码")
    private String cellPhone;

    @ApiModelProperty(value = "头像文件名称")
    private String headImgName;

    @ApiModelProperty(value = "头像文件路径")
    private String headImgUrl;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "参加工作时间")
    private String joinWorkStartTime;

    @ApiModelProperty(value = "个人邮箱")
    private String email;
}
