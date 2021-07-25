package com.laotan.net.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/6/9 9:38
 * @Description: 实体集成的base类
 */
@Data
public class BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间",hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间",hidden = true)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登录人名称")
    @TableField(exist = false)
    private String loginUsername;

    @ApiModelProperty(value = "登录人手机号")
    @TableField(exist = false)
    private String loginCellPhone;

    @ApiModelProperty(value = "登录人id")
    @TableField(exist = false)
    private Integer loginUserId;

    @ApiModelProperty(value = "登录类型：USER、BOSS")
    @TableField(exist = false)
    private String loginType;

}
