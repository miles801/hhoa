package com.michael.oa.dao.impl;

import com.michael.oa.bo.KnowledgeBo;
import com.michael.oa.dao.KnowledgeDao;
import com.michael.oa.domain.Knowledge;
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
@Repository("knowledgeDao")
public class KnowledgeDaoImpl extends HibernateDaoHelper implements KnowledgeDao {

    @Override
    public String save(Knowledge knowledge) {
        return (String) getSession().save(knowledge);
    }

    @Override
    public void update(Knowledge knowledge) {
        getSession().update(knowledge);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Knowledge> query(KnowledgeBo bo) {
        Criteria criteria = createCriteria(Knowledge.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(KnowledgeBo bo) {
        Criteria criteria = createRowCountsCriteria(Knowledge.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Knowledge.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Knowledge knowledge) {
        Assert.notNull(knowledge, "要删除的对象不能为空!");
        getSession().delete(knowledge);
    }

    @Override
    public Knowledge findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Knowledge) getSession().get(Knowledge.class, id);
    }

    private void initCriteria(Criteria criteria, KnowledgeBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
        if (bo != null) {
            String keywordsOrTitle = bo.getKeywordsOrTitle();
            if (StringUtils.isNotEmpty(keywordsOrTitle)) {
                String keywords[] = keywordsOrTitle.split("\\s+|,");
                Disjunction disjunction = Restrictions.disjunction();
                for (String keyword : keywords) {
                    disjunction.add(Restrictions.like("keywords", keyword, MatchMode.ANYWHERE));
                    disjunction.add(Restrictions.like("title", keyword, MatchMode.ANYWHERE));
                }
                criteria.add(disjunction);
            }
        }
    }

}