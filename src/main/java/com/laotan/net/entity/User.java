package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

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
@ApiModel(value="User信息对象", description="用户信息表（应聘者）")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_user")
public class User extends BaseEntity {

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "状态:在职、离职、在校")
    private String status;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "手机号码")
    private String cellPhone;

    @ApiModelProperty(value = "头像文件名称")
    private String headImgName;

    @ApiModelProperty(value = "头像文件路径")
    private String headImgUrl;

    @ApiModelProperty(value = "头像图片")
    @TableField(exist = false)
    private MultipartFile headImgFile;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "参加工作时间")
    private String joinWorkStartTime;

    @ApiModelProperty(value = "个人邮箱")
    private String email;

    @ApiModelProperty(value = "个人优势")
    private String advantage;

    @ApiModelProperty(value = "求职意向")
    @TableField(exist = false)
    private List<JobIntention> jobIntentionList;

    @TableField(exist = false)
    @ApiModelProperty(value = "工作经历")
    private List<WorkHistory> workHistoryList;

    @TableField(exist = false)
    @ApiModelProperty(value = "项目经历")
    private List<ProjectHistory> projectHistoryList;

    @TableField(exist = false)
    @ApiModelProperty(value = "教育经历")
    private List<EducationHistory> educationHistoryList;
}
