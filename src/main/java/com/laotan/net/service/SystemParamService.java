package com.laotan.net.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.Job;
import com.laotan.net.entity.SystemParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-29
 */
public interface SystemParamService extends IService<SystemParam> {
    /**
     * 分页查询系统参数
     * @param page
     * @return
     */
    IPage<SystemParam> pageSelectList(Page<SystemParam> page);

    /**
     * 查询所有系统参数
     * @return
     */
    List selectList();

    /**
     * 添加系统参数
     * @param systemParam
     * @return
     */
    Integer insert(SystemParam systemParam);

    /**
    * 功能描述:根据id集合删除
    * @Author: lzl
    * @Date: 2019/10/29
    **/
    Integer delete(List<String> ids);
    /**
    * 功能描述: 根据id删除
    * @Author: lzl
    * @Date: 2019/10/29
    **/
    Integer deleteById(Integer id);

    /**
    * 功能描述: 修改
    * @Author: lzl
    * @Date: 2019/10/29
    **/
    Integer update(SystemParam systemParam);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SystemParam selectOne(Integer id);

    /**
    * 功能描述: 根据参数名查询参数值
    * @Author: lzl
    * @Date: 2019/10/29
    **/
    String selectByParamKey(String paramName,String type);

    /*
    根据类型查询列表集合
     */
    List<SystemParam> selectByType(String type);
}
