package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *    系统参数表
 * </p>
 *
 * @author lzl
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemParam对象", description="系统参数表")
@TableName("tb_system_param")
public class SystemParam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数名称")
    private String paramName;

    @ApiModelProperty(value = "参数值")
    private String paramValue;

    @ApiModelProperty(value = "类型，用于区分不同类型数据源")
    private String type;

    @ApiModelProperty(value = "备注说明")
    private String detail;

}
