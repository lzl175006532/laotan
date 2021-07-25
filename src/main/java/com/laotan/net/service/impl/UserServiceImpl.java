package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.TokenUtil;
import com.laotan.net.mapper.UserMapper;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JobIntentionService jobIntentionService;
    @Autowired
    private WorkHistoryService workHistoryService;
    @Autowired
    private ProjectHistoryService projectHistoryService;
    @Autowired
    private EducationHistoryService educationHistoryService;
    @Autowired
    private AccountService accountService;


    @Override
    public User saveUserInfo(User user) {
        boolean save = super.save(user);
        if(save){
            //保存求职意向
            List<JobIntention> jobIntentionList = user.getJobIntentionList();
            if(jobIntentionList != null && jobIntentionList.size() > 0){
                for (JobIntention jobIntention:jobIntentionList) {
                    jobIntention.setUserId(user.getId());
                }
                jobIntentionService.saveBatch(jobIntentionList);
            }
            //保存工作经历
            List<WorkHistory> workHistoryList = user.getWorkHistoryList();
            if(workHistoryList != null && workHistoryList.size() > 0){
                for (WorkHistory workHistory:workHistoryList) {
                    workHistory.setUserId(user.getId());
                }
                workHistoryService.saveBatch(workHistoryList);
            }
            //保存项目经历
            List<ProjectHistory> projectHistoryList = user.getProjectHistoryList();
            if(projectHistoryList != null && projectHistoryList.size() > 0){
                for (ProjectHistory projectHistory:projectHistoryList) {
                    projectHistory.setUserId(user.getId());
                }
                projectHistoryService.saveBatch(projectHistoryList);
            }
            //保存教育经历
            List<EducationHistory> educationHistoryList = user.getEducationHistoryList();
            if(educationHistoryList != null && educationHistoryList.size() > 0){
                for (EducationHistory educationHistory:educationHistoryList) {
                    educationHistory.setUserId(user.getId());
                }
                educationHistoryService.saveBatch(educationHistoryList);
            }
        }
        //根据手机号查询账号表，设置账号为已完善简历信息
        String cellPhone = user.getCellPhone();
        Account account = accountService.selectByCellPhone(cellPhone);
        account.setUserInitInfo("Y");
        accountService.updateById(account);
        return user;
    }

    @Override
    public User selectUserInfoByCellPhone(String cellPhone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User :: getCellPhone,cellPhone);
        User user = userMapper.selectOne(wrapper);
        //查询求职意向
        List<JobIntention> jobIntentionList = jobIntentionService.selectByUserId(user.getId());
        user.setJobIntentionList(jobIntentionList);
        //工作经历
        List<WorkHistory> workHistories = workHistoryService.selectByUserId(user.getId());
        user.setWorkHistoryList(workHistories);
        //项目经历
        List<ProjectHistory> projectHistories = projectHistoryService.selectByUserId(user.getId());
        user.setProjectHistoryList(projectHistories);
        //教育经历
        List<EducationHistory> educationHistories = educationHistoryService.selectByUserId(user.getId());
        user.setEducationHistoryList(educationHistories);
        return user;
    }

    @Override
    public User saveOrUpdateInfo(User user) {
        Integer id = user.getId();
        if(id == null || id == 0){
            return this.saveUserInfo(user);
        }else{
            User userDB = super.getById(id);
            if(!StringUtils.isEmpty(user.getAdvantage())){
                userDB.setAdvantage(user.getAdvantage());
                super.updateById(userDB);
            }
            //更新求职意向
            List<JobIntention> jobIntentionList = user.getJobIntentionList();
            if(jobIntentionList != null && jobIntentionList.size() > 0){
                //删除之前求职意向
                jobIntentionService.deleteByUserId(user.getId());
                for (JobIntention jobIntention:jobIntentionList) {
                    jobIntention.setUserId(user.getId());
                }
                jobIntentionService.saveBatch(jobIntentionList);
                userDB.setJobIntentionList(jobIntentionList);
            }
            //更新工作经历
            List<WorkHistory> workHistoryList = user.getWorkHistoryList();
            if(workHistoryList != null && workHistoryList.size() > 0){
                //删除工作经历
                workHistoryService.deleteByUserId(user.getId());
                for (WorkHistory workHistory:workHistoryList) {
                    workHistory.setUserId(user.getId());
                }
                workHistoryService.saveBatch(workHistoryList);
                userDB.setWorkHistoryList(workHistoryList);
            }
            //更新项目经历
            List<ProjectHistory> projectHistoryList = user.getProjectHistoryList();
            if(projectHistoryList != null && projectHistoryList.size() > 0){
                //删除项目经历
                projectHistoryService.deleteByUserId(user.getId());
                for (ProjectHistory projectHistory:projectHistoryList) {
                    projectHistory.setUserId(user.getId());
                }
                projectHistoryService.saveBatch(projectHistoryList);
                userDB.setProjectHistoryList(projectHistoryList);
            }
            //更新教育经历
            List<EducationHistory> educationHistoryList = user.getEducationHistoryList();
            if(educationHistoryList != null && educationHistoryList.size() > 0){
                //删除教育经历
                educationHistoryService.deleteByUserId(user.getId());
                for (EducationHistory educationHistory:educationHistoryList) {
                    educationHistory.setUserId(user.getId());
                }
                educationHistoryService.saveBatch(educationHistoryList);
                userDB.setEducationHistoryList(educationHistoryList);
            }
            return userDB;
        }
    }

    @Override
    public User selectUserInfoByToken(String token) {
        Account account = accountService.getById(TokenUtil.getAccoutIdByToken(token));
        if(account == null){
            return null;
        }
        User user = this.selectUserInfoByCellPhone(account.getCellPhone());
        return user;
    }

}
