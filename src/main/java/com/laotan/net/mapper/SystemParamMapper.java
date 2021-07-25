package com.laotan.net.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laotan.net.entity.SystemParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2019-10-29
 */
@Repository
public interface SystemParamMapper extends BaseMapper<SystemParam> {
    /**
     * 分页查询所有
     * @param page
     * @return
     */
    @Select("select * from tb_system_param ")
    IPage<SystemParam> pageSelectList(Page page);

    /**
    * 功能描述: 根据参数key查询值
    * @Author: lzl
    * @Date: 2019/10/29
    **/
    @Select("<script>select PARAM_VALUE from tb_system_param where PARAM_NAME = #{paramName}<if test = 'type != null and \"\" !=type' > and type = #{type}</if></script>")
    String selectByParamKey(@Param("paramName") String paramName,@Param("type") String type);

    /**
     * 根据key修改value
     * @return int
     * @author liubo
     * @date 2019/10/31 16:37
     */
    @Update("update system_param set param_value=#{value} where param_name=#{key}")
    int updateByParamKey(@Param("key") String key, @Param("value") String value);
}
