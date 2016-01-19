package com.michael.oa.dao.impl;

import com.michael.oa.bo.ArticleViewBo;
import com.michael.oa.dao.ArticleViewDao;
import com.michael.oa.domain.ArticleView;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;


/**
 * @author Michael
 */
@Repository("articleViewDao")
public class ArticleViewDaoImpl extends HibernateDaoHelper implements ArticleViewDao {

    @Override
    public String save(ArticleView articleView) {
        return (String) getSession().save(articleView);
    }

    @Override
    public void update(ArticleView articleView) {
        getSession().update(articleView);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleView> query(ArticleViewBo bo) {
        Criteria criteria = createCriteria(ArticleView.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(ArticleViewBo bo) {
        Criteria criteria = createRowCountsCriteria(ArticleView.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + ArticleView.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(ArticleView articleView) {
        Assert.notNull(articleView, "要删除的对象不能为空!");
        getSession().delete(articleView);
    }

    @Override
    public ArticleView findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (ArticleView) getSession().get(ArticleView.class, id);
    }

    @Override
    public Date find(String articleId, String userId) {
        Assert.hasText(articleId, "文章ID不能为空!");
        Assert.hasText(userId, "阅读人ID不能为空!");
        return (Date) createCriteria(ArticleView.class)
                .setProjection(Projections.property("createdDatetime"))
                .add(Restrictions.eq("creatorId", userId))
                .add(Restrictions.eq("articleId", articleId))
                .uniqueResult();
    }

    private void initCriteria(Criteria criteria, ArticleViewBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}