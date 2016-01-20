package com.michael.oa.dao.impl;

import com.michael.oa.bo.WorkLogBo;
import com.michael.oa.dao.WorkLogDao;
import com.michael.oa.domain.WorkLog;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.hibernate.filter.FilterFieldType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("workLogDao")
public class WorkLogDaoImpl extends HibernateDaoHelper implements WorkLogDao {

    @Override
    public String save(WorkLog workLog) {
        return (String) getSession().save(workLog);
    }

    @Override
    public void update(WorkLog workLog) {
        getSession().update(workLog);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WorkLog> query(WorkLogBo bo) {
        Criteria criteria = createCriteria(WorkLog.class, "PREVILEGE_WORK_LOG_QUERY", "creatorId", FilterFieldType.EMPLOYEE);
        initCriteria(criteria, bo);
        criteria.addOrder(Order.desc("occurDate"));
        return criteria.list();
    }

    @Override
    public Long getTotal(WorkLogBo bo) {
        Criteria criteria = createRowCountsCriteria(WorkLog.class, "PREVILEGE_WORK_LOG_QUERY", "creatorId", FilterFieldType.EMPLOYEE);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + WorkLog.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(WorkLog workLog) {
        Assert.notNull(workLog, "要删除的对象不能为空!");
        getSession().delete(workLog);
    }

    @Override
    public WorkLog findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (WorkLog) getSession().get(WorkLog.class, id);
    }

    private void initCriteria(Criteria criteria, WorkLogBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}