package com.laotan.net.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.laotan.net.entity.EducationHistory;
import com.laotan.net.entity.JobIntention;
import com.laotan.net.entity.ProjectHistory;
import com.laotan.net.entity.WorkHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDTO {


    @ApiModelProperty(value = "个人优势")
    private List<Map<String,String>> advantageList;

    @ApiModelProperty(value = "基本信息")
    private List<UserInfo> baseInfoList;

    @ApiModelProperty(value = "求职意向")
    private List<JobIntention> jobIntentionList;

    @ApiModelProperty(value = "工作经历")
    private List<WorkHistory> workHistoryList;

    @ApiModelProperty(value = "项目经历")
    private List<ProjectHistory> projectHistoryList;

    @ApiModelProperty(value = "教育经历")
    private List<EducationHistory> educationHistoryList;
}
