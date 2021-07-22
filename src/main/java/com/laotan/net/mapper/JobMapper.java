package com.laotan.net.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laotan.net.entity.Job;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Repository
public interface JobMapper extends BaseMapper<Job> {

    @Select("select * from tb_job where code = #{code}")
    Job selectByCode(@Param("code") String code);
}
