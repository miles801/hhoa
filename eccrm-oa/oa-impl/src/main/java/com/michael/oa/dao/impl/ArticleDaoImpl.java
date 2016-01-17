package com.michael.oa.dao.impl;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.dao.ArticleDao;
import com.michael.oa.domain.Article;
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
@Repository("articleDao")
public class ArticleDaoImpl extends HibernateDaoHelper implements ArticleDao {

    @Override
    public String save(Article article) {
        return (String) getSession().save(article);
    }

    @Override
    public void update(Article article) {
        getSession().update(article);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> query(ArticleBo bo) {
        Criteria criteria = createCriteria(Article.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(ArticleBo bo) {
        Criteria criteria = createRowCountsCriteria(Article.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Article.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Article article) {
        Assert.notNull(article, "要删除的对象不能为空!");
        getSession().delete(article);
    }

    @Override
    public Article findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Article) getSession().get(Article.class, id);
    }

    private void initCriteria(Criteria criteria, ArticleBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
        if (bo != null && StringUtils.isNotEmpty(bo.getKeywords())) {
            String keywords[] = bo.getKeywords().split("\\s|,");
            Disjunction disjunction = Restrictions.disjunction();
            for (String keyword : keywords) {
                disjunction.add(Restrictions.like("keywords", keyword, MatchMode.ANYWHERE));
            }
            criteria.add(disjunction);
        }
    }

}