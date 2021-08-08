package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教育经历表
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="educationHistory对象", description="教育经历表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_education_history")
public class EducationHistory extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "大学名称")
    private String universityName;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "在校时间(原型上格式为yyyy-MM-dd ~ yyyy-MM-dd，客户端拼接字符串传过来)")
    private String inSchoolTime;
}
