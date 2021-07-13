package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 工作经历表
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="WorkHistory对象", description="工作经历表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_work_history")
public class WorkHistory extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "公司名称")
    private String compName;

    @ApiModelProperty(value = "公司id")
    private Integer compId;

    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "在职时间(原型上格式为yyyy-MM-dd ~ yyyy-MM-dd，客户端拼接字符串传过来)")
    private String onJobTime;

    @ApiModelProperty(value = "工作描述")
    private String jobDetail;

}
