package com.michael.oa.service;

import com.michael.oa.bo.CommentBo;
import com.michael.oa.domain.Comment;
import com.michael.oa.vo.CommentVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface CommentService {

    /**
     * 保存
     */
    String save(Comment comment);

    /**
     * 分页查询
     */
    PageVo pageQuery(CommentBo bo);

    /**
     * 根据ID查询对象的信息
     */
    CommentVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);


}
