package com.laotan.net.entity;


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
 * @Description: 职位表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value="Job对象", description="职位表")
@TableName(value = "tb_job")
public class Job extends BaseEntity{

    @ApiModelProperty(value = "编号")
    private String code ;

    @ApiModelProperty(value = "名称")
    private String name ;

    @ApiModelProperty(value = "父级编号")
    private String parentCode;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
