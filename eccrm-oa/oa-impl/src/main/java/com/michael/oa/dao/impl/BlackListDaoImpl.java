package com.michael.oa.dao.impl;

import com.michael.oa.bo.BlackListBo;
import com.michael.oa.dao.BlackListDao;
import com.michael.oa.domain.BlackList;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.utils.string.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("blackListDao")
public class BlackListDaoImpl extends HibernateDaoHelper implements BlackListDao {

    @Override
    public String save(BlackList blackList) {
        return (String) getSession().save(blackList);
    }

    @Override
    public void update(BlackList blackList) {
        getSession().update(blackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BlackList> query(BlackListBo bo) {
        Criteria criteria = createCriteria(BlackList.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(BlackListBo bo) {
        Criteria criteria = createRowCountsCriteria(BlackList.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + BlackList.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(BlackList blackList) {
        Assert.notNull(blackList, "要删除的对象不能为空!");
        getSession().delete(blackList);
    }

    @Override
    public BlackList findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (BlackList) getSession().get(BlackList.class, id);
    }

    private void initCriteria(Criteria criteria, BlackListBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
        // 多个关键字匹配
        if (bo != null && StringUtils.isNotEmpty(bo.getKeywords())) {
            String keyArr[] = bo.getKeywords().split("\\s+|,");
            Disjunction disjunction = Restrictions.disjunction();
            for (String keyword : keyArr) {
                disjunction.add(Restrictions.like("keywords", keyword, MatchMode.ANYWHERE));
            }
            criteria.add(disjunction);
        }
    }

}