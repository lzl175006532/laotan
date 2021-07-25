package com.laotan.net.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="Token对象", description="Token表")
@TableName(value = "tb_user_token")
public class UserToken {
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id ;

    @ApiModelProperty(value = "客户端token")
    private String clientToken ;

    @ApiModelProperty(value = "用户登录账号,目前是手机号")
    private String account;

    @ApiModelProperty(value = "生成token的盐")
    private String salt;

    @ApiModelProperty(value = "token失效时间")
    private LocalDateTime failureTime ;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime ;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime ;



}
