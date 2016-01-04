package com.michael.oa.dao;

import com.michael.oa.bo.ModuleBo;
import com.michael.oa.domain.Module;

import java.util.List;

/**
 * @author Michael
 */
public interface ModuleDao {

    String save(Module module);

    void update(Module module);

    /**
     * 高级查询接口
     */
    List<Module> query(ModuleBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(ModuleBo bo);

    Module findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Module module);
}
