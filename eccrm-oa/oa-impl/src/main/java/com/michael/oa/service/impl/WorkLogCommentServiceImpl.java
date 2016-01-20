package com.michael.oa.service.impl;

import com.michael.oa.bo.WorkLogCommentBo;
import com.michael.oa.dao.WorkLogCommentDao;
import com.michael.oa.dao.WorkLogDao;
import com.michael.oa.domain.WorkLog;
import com.michael.oa.domain.WorkLogComment;
import com.michael.oa.service.WorkLogCommentService;
import com.michael.oa.vo.WorkLogCommentVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.pager.Pager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("workLogCommentService")
public class WorkLogCommentServiceImpl implements WorkLogCommentService {
    @Resource
    private WorkLogCommentDao workLogCommentDao;

    @Resource
    private WorkLogDao workLogDao;

    @Override
    public String save(WorkLogComment workLogComment) {
        // 验证与保存
        ValidatorUtils.validate(workLogComment);
        String id = workLogCommentDao.save(workLogComment);

        // 更改评论数
        String workLogId = workLogComment.getWorkLogId();
        WorkLog workLog = workLogDao.findById(workLogId);
        Assert.notNull(workLog, "评论日志失败：工作日志" + workLogId + "不存在!");
        Assert.isTrue(workLog.getCommentCounts() < 100, "评论日志失败：该日志被评论的次数过多!");
        workLog.setCommentCounts(workLog.getCommentCounts() + 1);
        return id;
    }

    @Override
    public void update(WorkLogComment workLogComment) {
        Assert.isTrue(false, "更新评论失败：该功能不存在!");
        workLogCommentDao.update(workLogComment);
    }

    @Override
    public PageVo pageQuery(WorkLogCommentBo bo) {
        PageVo vo = new PageVo();
        Long total = workLogCommentDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<WorkLogComment> workLogCommentList = workLogCommentDao.query(bo);
        List<WorkLogCommentVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(workLogCommentList, WorkLogCommentVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public WorkLogCommentVo findById(String id) {
        WorkLogComment workLogComment = workLogCommentDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(workLogComment, WorkLogCommentVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            workLogCommentDao.deleteById(id);
        }
    }

    @Override
    public List<WorkLogCommentVo> queryByWorkLog(String workLogId) {
        Assert.hasText(workLogId, "查询评论记录失败：工作日志ID不能为空!");
        WorkLogCommentBo bo = new WorkLogCommentBo();
        bo.setWorkLogId(workLogId);
        Pager.addOrder("createdDatetime", false);
        List<WorkLogComment> comments = workLogCommentDao.query(bo);
        return BeanWrapBuilder.newInstance().wrapList(comments, WorkLogCommentVo.class);
    }
}
