package com.michael.oa.service.impl;

import com.michael.oa.bo.WorkLogBo;
import com.michael.oa.dao.WorkLogDao;
import com.michael.oa.domain.WorkLog;
import com.michael.oa.service.WorkLogService;
import com.michael.oa.vo.WorkLogVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Service("workLogService")
public class WorkLogServiceImpl implements WorkLogService {
    @Resource
    private WorkLogDao workLogDao;

    @Override
    public String save(WorkLog workLog) {
        workLog.setOccurDate(new Date());
        ValidatorUtils.validate(workLog);
        workLog.setCommentCounts(0);
        String id = workLogDao.save(workLog);
        return id;
    }

    @Override
    public void update(WorkLog workLog) {
        workLogDao.update(workLog);
    }

    @Override
    public PageVo pageQuery(WorkLogBo bo) {
        PageVo vo = new PageVo();
        Long total = workLogDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<WorkLog> workLogList = workLogDao.query(bo);
        List<WorkLogVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(workLogList, WorkLogVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public WorkLogVo findById(String id) {
        WorkLog workLog = workLogDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(workLog, WorkLogVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            workLogDao.deleteById(id);
        }
    }


}
