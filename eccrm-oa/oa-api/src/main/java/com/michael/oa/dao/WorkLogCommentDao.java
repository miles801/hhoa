package com.michael.oa.dao;

import com.michael.oa.bo.WorkLogCommentBo;
import com.michael.oa.domain.WorkLogComment;

import java.util.List;

/**
 * @author Michael
 */
public interface WorkLogCommentDao {

    String save(WorkLogComment workLogComment);

    void update(WorkLogComment workLogComment);

    /**
     * 高级查询接口
     */
    List<WorkLogComment> query(WorkLogCommentBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(WorkLogCommentBo bo);

    WorkLogComment findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(WorkLogComment workLogComment);
}
