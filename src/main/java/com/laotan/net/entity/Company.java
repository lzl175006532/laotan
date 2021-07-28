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

import java.util.List;

/**
 * <p>
 * 公司注册表
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Company信息对象", description="公司注册表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@TableName("tb_company")
public class Company extends BaseEntity {

    @ApiModelProperty(value = "所属招聘者id")
    private Integer bossId;

    //基本信息
    @ApiModelProperty(value = "基本信息-企业logo")
    private String logo;

    @ApiModelProperty(value = "基本信息-企业简称")
    private String compSimpleName;

    @ApiModelProperty(value = "基本信息-所属行业")
    private String compBusiness;

    @ApiModelProperty(value = "基本信息-公司规模")
    private String compScale;

    @ApiModelProperty(value = "基本信息-融资情况")
    private String condition;

    @ApiModelProperty(value = "企业介绍")
    private String compDetail;

    @ApiModelProperty(value = "企业官网")
    private String compUccn;

    @ApiModelProperty(value = "企业地址")
    private String compUrl;

    //工商信息
    @ApiModelProperty(value = "工商信息-企业全称")
    private String compFullName;

    @ApiModelProperty(value = "工商信息-企业法人")
    private String compLegalPerson;

    @ApiModelProperty(value = "工商信息-注册资本")
    private String compCapital;

    @ApiModelProperty(value = "工商信息-企业类型")
    private String compType;

    @ApiModelProperty(value = "工商信息-统一代码")
    private String compUnicode;

    @ApiModelProperty(value = "工商信息-营业执照url")
    private String businessLicenseUrl;

    @ApiModelProperty(value = "工商信息-营业执照文件名称")
    private String businessLicenseName;

    @ApiModelProperty(value = "工商信息-营业执照文件")
    @TableField(exist = false)
    private MultipartFile businessLicenseFile;

    @ApiModelProperty(value = "0-未审核，1-审核通过，2-审核拒绝")
    private Integer status;

    @ApiModelProperty(value = "企业相册")
    @TableField(exist = false)
    private List<CompImg> imgList;

    @ApiModelProperty(value = "企业相册图片文件")
    @TableField(exist = false)
    private MultipartFile[] imgFiles;

    @ApiModelProperty(value = "企业福利")
    @TableField(exist = false)
    private List<CompWelfare> comWelfareList;

}
