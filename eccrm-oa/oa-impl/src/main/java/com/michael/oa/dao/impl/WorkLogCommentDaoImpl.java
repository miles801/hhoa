package com.michael.oa.dao.impl;

import com.michael.oa.bo.WorkLogCommentBo;
import com.michael.oa.dao.WorkLogCommentDao;
import com.michael.oa.domain.WorkLogComment;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("workLogCommentDao")
public class WorkLogCommentDaoImpl extends HibernateDaoHelper implements WorkLogCommentDao {

    @Override
    public String save(WorkLogComment workLogComment) {
        return (String) getSession().save(workLogComment);
    }

    @Override
    public void update(WorkLogComment workLogComment) {
        getSession().update(workLogComment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WorkLogComment> query(WorkLogCommentBo bo) {
        Criteria criteria = createCriteria(WorkLogComment.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(WorkLogCommentBo bo) {
        Criteria criteria = createRowCountsCriteria(WorkLogComment.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + WorkLogComment.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(WorkLogComment workLogComment) {
        Assert.notNull(workLogComment, "要删除的对象不能为空!");
        getSession().delete(workLogComment);
    }

    @Override
    public WorkLogComment findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (WorkLogComment) getSession().get(WorkLogComment.class, id);
    }

    private void initCriteria(Criteria criteria, WorkLogCommentBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}