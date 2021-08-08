package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.dto.UserDTO;
import com.laotan.net.dto.UserInfo;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 17:56
 * @Description: 前端应聘者用户信息
 */
@RestController
@RequestMapping("app/user")
@Api(tags = {"前端应聘者用户信息"})
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JobIntentionService jobIntentionService;
    @Autowired
    private WorkHistoryService workHistoryService;
    @Autowired
    private ProjectHistoryService projectHistoryService;
    @Autowired
    private EducationHistoryService educationHistoryService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户基本信息
     */
    @ApiOperation(value="保存或更新用户基本信息", notes="保存或更新用户基本信息")
    @PostMapping(value = "/saveOrUpdateJBXXInfo")
    public JsonResult saveOrUpdateJBXXInfo( User user) {
        if(user == null || StringUtils.isEmpty(user.getLoginCellPhone())){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"loginCellPhone参数不能为空");
        }
        if(!StringUtils.isEmpty(user.getBirthday()) && user.getBirthday().length() != 10){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"birthday参数格式不对");
        }
        if(!StringUtils.isEmpty(user.getJoinWorkStartTime()) && user.getJoinWorkStartTime().length() != 10){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"joinWorkStartTime参数格式不对");
        }
        String cellPhone = user.getLoginCellPhone();
        Account account = accountService.selectByCellPhone(cellPhone);
        if(account == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL.getCode(),"数据库中没有"+cellPhone+"账号信息");
        }
        logger.info("保存或更新用户注册简历信息，姓名为{}",user.getUsername());
        User saveUserDB = userService.saveOrUpdateJBXXInfo(user);
        return new JsonResult(ResultStatusCode.SUCCESS,saveUserDB);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户个人优势
     */
    @ApiOperation(value="保存或更新用户个人优势", notes="保存或更新用户个人优势")
    @PostMapping(value = "/saveOrUpdateGRYSInfo")
    public JsonResult saveOrUpdateGRYSInfo( User user) {
        if(user == null || user.getId() == null || user.getId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"用户id不能为空");
        }
        logger.info("保存或更新用户个人优势");
        User saveUserDB = userService.saveOrUpdateGRYSInfo(user);
        return new JsonResult(ResultStatusCode.SUCCESS,saveUserDB);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户求职意向
     */
    @ApiOperation(value="保存或更新用户求职意向", notes="保存或更新用户求职意向")
    @PostMapping(value = "/saveOrUpdateQZYXInfo")
    public JsonResult saveOrUpdateQZYXInfo( JobIntention jobIntention) {
        if(jobIntention == null || jobIntention.getUserId() == null || jobIntention.getUserId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"userId不能为空");
        }
        logger.info("保存或更新用户求职意向");
        jobIntentionService.saveOrUpdate(jobIntention);
        return new JsonResult(ResultStatusCode.SUCCESS,jobIntention);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 删除用户求职意向
     */
    @ApiOperation(value="删除用户求职意向", notes="删除用户求职意向")
    @PostMapping(value = "/deleteQZYXInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobIntentionId", value = "求职意向id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult saveOrUpdateQZYXInfo( Integer jobIntentionId) {
        if(jobIntentionId == null || jobIntentionId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"jobIntentionId不能为空");
        }
        logger.info("删除用户求职意向");
        jobIntentionService.deleteById(jobIntentionId);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户工作经历
     */
    @ApiOperation(value="保存或更新用户工作经历", notes="保存或更新用户工作经历")
    @PostMapping(value = "/saveOrUpdateGZJLInfo")
    public JsonResult saveOrUpdateGZJLInfo( WorkHistory workHistory) {
        if(workHistory == null || workHistory.getUserId() == null || workHistory.getUserId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"userId不能为空");
        }
        logger.info("保存或更新用户工作经历");
        workHistoryService.saveOrUpdate(workHistory);
        return new JsonResult(ResultStatusCode.SUCCESS,workHistory);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 删除用户工作经历
     */
    @ApiOperation(value="删除用户工作经历", notes="删除用户工作经历")
    @PostMapping(value = "/deleteGZJLInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workHistoryId", value = "工作经历id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult deleteGZJLInfo( Integer workHistoryId) {
        if(workHistoryId == null || workHistoryId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"workHistoryId不能为空");
        }
        logger.info("删除用户工作经历");
        workHistoryService.deleteById(workHistoryId);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户项目经历
     */
    @ApiOperation(value="保存或更新用户项目经历", notes="保存或更新用户项目经历")
    @PostMapping(value = "/saveOrUpdateXMJLInfo")
    public JsonResult saveOrUpdateXMJLInfo( ProjectHistory projectHistory) {
        if(projectHistory == null || projectHistory.getUserId() == null || projectHistory.getUserId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"userId不能为空");
        }
        logger.info("保存或更新用户项目经历");
        projectHistoryService.saveOrUpdate(projectHistory);
        return new JsonResult(ResultStatusCode.SUCCESS,projectHistory);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 删除用户项目经历
     */
    @ApiOperation(value="删除用户项目经历", notes="删除用户项目经历")
    @PostMapping(value = "/deleteXMJLInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectHistoryId", value = "项目经历id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult deleteXMJLInfo( Integer projectHistoryId) {
        if(projectHistoryId == null || projectHistoryId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"projectHistoryId不能为空");
        }
        logger.info("删除用户项目经历");
        workHistoryService.deleteById(projectHistoryId);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户教育经历
     */
    @ApiOperation(value="保存或更新用户教育经历", notes="保存或更新用户教育经历")
    @PostMapping(value = "/saveOrUpdateJYJLInfo")
    public JsonResult saveOrUpdateJYJLInfo( EducationHistory educationHistory) {
        if(educationHistory == null || educationHistory.getUserId() == null || educationHistory.getUserId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"userId不能为空");
        }
        logger.info("保存或更新用户教育经历");
        educationHistoryService.saveOrUpdate(educationHistory);
        return new JsonResult(ResultStatusCode.SUCCESS,educationHistory);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 删除用户教育经历
     */
    @ApiOperation(value="删除用户教育经历", notes="删除用户教育经历")
    @PostMapping(value = "/deleteJYJLInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "educationHistoryId", value = "教育经历id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult deleteJYJLInfo( Integer educationHistoryId) {
        if(educationHistoryId == null || educationHistoryId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"educationHistoryId不能为空");
        }
        logger.info("删除用户项目教育经历");
        educationHistoryService.deleteById(educationHistoryId);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }

    @ApiOperation(value="根据token获取用户信息", notes="根据token获取用户信息")
    @PostMapping(value = "/selectUserInfoByToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", required = true, defaultValue = ""),
    })
    public JsonResult selectUserInfoByToken(String token){
        logger.info("根据token:{}获取用户信息",token);
        if(StringUtils.isEmpty(token)){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"token不能为空");
        }
        User userDB = userService.selectUserInfoByToken(token);
        if(userDB == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL.getCode(),"token无效或数据库无资源");
        }
        UserDTO dto = this.transitionDTOByUser(userDB);
        return new JsonResult(ResultStatusCode.SUCCESS,userDB);
    }

    @ApiOperation(value="根据手机号获取用户信息", notes="根据手机号获取用户信息")
    @PostMapping(value = "/selectUserInfoByCellPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult selectUserInfoByCellPhone(String cellPhone){
        logger.info("根据手机号:{}获取用户信息",cellPhone);
        if(StringUtils.isEmpty(cellPhone)){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"cellPhone不能为空");
        }
        User userDB = userService.selectUserInfoByCellPhone(cellPhone);
        if(userDB == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL.getCode(),"数据库没有此手机号用户信息");
        }
        UserDTO dto = this.transitionDTOByUser(userDB);
        return new JsonResult(ResultStatusCode.SUCCESS,dto);
    }

    private UserDTO transitionDTOByUser(User userDB) {
        //个人优势
        UserDTO dto = new UserDTO();
        List<Map<String,String>> advamtageList = new ArrayList<>();
        Map<String,String> advamtageMap = new HashMap<>();
        if(StringUtils.isEmpty(userDB.getAdvantage())){
            dto.setAdvantageList(null);
        }else{
            advamtageMap.put("advamtage",userDB.getAdvantage());
            advamtageList.add(advamtageMap);
            dto.setAdvantageList(advamtageList);
        }
        //基本信息封装
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userDB.getId());
        userInfo.setBirthday(userDB.getBirthday());
        userInfo.setCellPhone(userDB.getCellPhone());
        userInfo.setEmail(userDB.getEmail());
        userInfo.setHeadImgName(userDB.getHeadImgName());
        userInfo.setJoinWorkStartTime(userDB.getJoinWorkStartTime());
        userInfo.setSex(userDB.getSex());
        userInfo.setStatus(userDB.getStatus());
        userInfo.setUsername(userDB.getUsername());
        userInfo.setHeadImgUrl(userDB.getHeadImgUrl());
        userInfo.setSignature(userDB.getSignature());
        //app第一行显示，格式：工作 10 年 · 30岁  本科  在职
        StringBuffer no1ValueBuffer = new StringBuffer();
        no1ValueBuffer.append("工作 ");
        String joinWorkStartTimeString = userInfo.getJoinWorkStartTime();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate joinWorkStartTime = LocalDate.parse(joinWorkStartTimeString, fmt);
        int years = joinWorkStartTime.until(LocalDate.now()).getYears();
        no1ValueBuffer.append(years + " 年 · ");
        String birthdayString = userInfo.getBirthday();
        LocalDate birthday = LocalDate.parse(birthdayString, fmt);
        int age = birthday.until(LocalDate.now()).getYears();
        no1ValueBuffer.append(age + "岁  ");
        List<EducationHistory> educationHistories = educationHistoryService.selectByUserId(userDB.getId());
        if(educationHistories != null && educationHistories.size() > 0){
            String education = educationHistories.get(0).getEducation();
            no1ValueBuffer.append(education + "  ");
        }
        no1ValueBuffer.append(userInfo.getStatus());
        userInfo.setNo1Value(no1ValueBuffer.toString());

        userInfoList.add(userInfo);
        dto.setBaseInfoList(userInfoList);
        dto.setEducationHistoryList(userDB.getEducationHistoryList());
        dto.setJobIntentionList(userDB.getJobIntentionList());
        dto.setProjectHistoryList(userDB.getProjectHistoryList());
        dto.setWorkHistoryList(userDB.getWorkHistoryList());
        return dto;
    }

}
