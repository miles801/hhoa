package com.michael.oa.dao.impl;

import com.michael.oa.bo.CommentBo;
import com.michael.oa.dao.CommentDao;
import com.michael.oa.domain.Comment;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("commentDao")
public class CommentDaoImpl extends HibernateDaoHelper implements CommentDao {

    @Override
    public String save(Comment comment) {
        return (String) getSession().save(comment);
    }

    @Override
    public void update(Comment comment) {
        getSession().update(comment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> query(CommentBo bo) {
        Criteria criteria = createCriteria(Comment.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(CommentBo bo) {
        Criteria criteria = createRowCountsCriteria(Comment.class);
        initCriteria(criteria, bo);
        criteria.addOrder(Order.desc("createdDatetime"));
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Comment.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Comment comment) {
        Assert.notNull(comment, "要删除的对象不能为空!");
        getSession().delete(comment);
    }

    @Override
    public Comment findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Comment) getSession().get(Comment.class, id);
    }

    private void initCriteria(Criteria criteria, CommentBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}