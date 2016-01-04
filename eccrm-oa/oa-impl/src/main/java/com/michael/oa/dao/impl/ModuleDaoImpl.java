package com.michael.oa.dao.impl;

import com.michael.oa.bo.ModuleBo;
import com.michael.oa.dao.ModuleDao;
import com.michael.oa.domain.Module;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("moduleDao")
public class ModuleDaoImpl extends HibernateDaoHelper implements ModuleDao {

    @Override
    public String save(Module module) {
        return (String) getSession().save(module);
    }

    @Override
    public void update(Module module) {
        getSession().update(module);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Module> query(ModuleBo bo) {
        Criteria criteria = createCriteria(Module.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(ModuleBo bo) {
        Criteria criteria = createRowCountsCriteria(Module.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Module.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Module module) {
        Assert.notNull(module, "要删除的对象不能为空!");
        getSession().delete(module);
    }

    @Override
    public Module findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Module) getSession().get(Module.class, id);
    }

    private void initCriteria(Criteria criteria, ModuleBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}