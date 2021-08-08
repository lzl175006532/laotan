package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.util.FileUtils;
import com.laotan.net.common.util.TokenUtil;
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
    @Autowired
    private FileUtils fileUtils;


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
        if(user == null){
            return null;
        }
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
    public User saveOrUpdateJBXXInfo(User user) {
        //处理附件:头像文件信息
        if(user != null && user.getHeadImgFile() != null){
            StringBuffer filePath = new StringBuffer();
            String fileName = fileUtils.uploadFile(user.getHeadImgFile(), filePath);
            user.setHeadImgUrl(filePath.toString());
            user.setHeadImgName(fileName);
        }
        this.saveOrUpdate(user);
        return user;
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

    @Override
    public User saveOrUpdateGRYSInfo(User user) {
        Integer id = user.getId();
        User userDB = super.getById(id);
        userDB.setAdvantage(user.getAdvantage());
        super.saveOrUpdate(userDB);
        return userDB;
    }

}
