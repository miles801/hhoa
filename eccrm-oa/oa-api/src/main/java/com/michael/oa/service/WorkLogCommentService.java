package com.michael.oa.service;

import com.michael.oa.bo.WorkLogCommentBo;
import com.michael.oa.domain.WorkLogComment;
import com.michael.oa.vo.WorkLogCommentVo;
import com.ycrl.core.pager.PageVo;

import java.util.List;

/**
 * @author Michael
 */
public interface WorkLogCommentService {

    /**
     * 保存
     */
    String save(WorkLogComment workLogComment);

    /**
     * 更新
     */
    void update(WorkLogComment workLogComment);

    /**
     * 分页查询
     */
    PageVo pageQuery(WorkLogCommentBo bo);


    /**
     * 根据ID查询对象的信息
     */
    WorkLogCommentVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    /**
     * 查询指定日志下的所有评论记录
     *
     * @param workLogId 工作日志ID
     * @return 评论记录
     */
    List<WorkLogCommentVo> queryByWorkLog(String workLogId);

}
