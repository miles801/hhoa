package com.michael.oa.service.impl;

import com.michael.oa.bo.ModuleBo;
import com.michael.oa.dao.ModuleDao;
import com.michael.oa.domain.Module;
import com.michael.oa.service.ModuleService;
import com.michael.oa.vo.ModuleVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService, BeanWrapCallback<Module, ModuleVo> {
    @Resource
    private ModuleDao moduleDao;

    @Override
    public String save(Module module) {
        ValidatorUtils.validate(module);
        String id = moduleDao.save(module);
        return id;
    }

    @Override
    public void update(Module module) {
        ValidatorUtils.validate(module);
        moduleDao.update(module);
    }

    @Override
    public PageVo pageQuery(ModuleBo bo) {
        PageVo vo = new PageVo();
        Long total = moduleDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Module> moduleList = moduleDao.query(bo);
        List<ModuleVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(moduleList, ModuleVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public ModuleVo findById(String id) {
        Module module = moduleDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(module, ModuleVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            moduleDao.deleteById(id);
        }
    }


    @Override
    public void doCallback(Module module, ModuleVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        // 模块类型
        vo.setTypeName(container.getBusinessName(TYPE, module.getType()));
    }
}
