package com.michael.oa.dao;

import com.michael.oa.bo.CommentBo;
import com.michael.oa.domain.Comment;

import java.util.List;

/**
 * @author Michael
 */
public interface CommentDao {

    String save(Comment comment);

    void update(Comment comment);

    /**
     * 高级查询接口
     */
    List<Comment> query(CommentBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(CommentBo bo);

    Comment findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Comment comment);
}
