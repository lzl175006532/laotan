package com.laotan.net.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laotan.net.entity.PublishJob;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Repository
public interface PublishJobMapper extends BaseMapper<PublishJob> {

    @Select("select * from tb_publish_job where boss_id = #{bossId}")
    List<PublishJob> selectJobByBossId(Integer bossId);

    @Select("<script>select * from tb_publish_job  " +
            "<if test = 'jobIntentionNameList != null'>" +
            " where 1 != 1" +
            "<foreach item='jobIntentionName' index='index' collection='jobIntentionNameList' open='(' separator=',' close=')'>" +
            " or job_name like '%${jobIntentionName}%'" +
            "</foreach>" +
            "</if>" +
            "</script>")
    List<PublishJob> selectJobByJobIntentionNameList(List<String> jobIntentionNameList);
}
