package com.michael.oa.service;

import com.michael.oa.bo.ModuleBo;
import com.michael.oa.domain.Module;
import com.michael.oa.vo.ModuleVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface ModuleService {

    /**
     * 业务参数：模块类型
     */
    String TYPE = "OA_MKLX";
    /**
     * 保存
     */
    String save(Module module);

    /**
     * 更新
     */
    void update(Module module);

    /**
     * 分页查询
     */
    PageVo pageQuery(ModuleBo bo);

    /**
     * 根据ID查询对象的信息
     */
    ModuleVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
