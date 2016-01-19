package com.michael.oa.service.impl;

import com.michael.oa.bo.CommentBo;
import com.michael.oa.dao.ArticleDao;
import com.michael.oa.dao.CommentDao;
import com.michael.oa.domain.Article;
import com.michael.oa.domain.Comment;
import com.michael.oa.service.CommentService;
import com.michael.oa.vo.CommentVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Resource
    private ArticleDao articleDao;

    @Override
    public synchronized String save(Comment comment) {
        ValidatorUtils.validate(comment);
        String id = commentDao.save(comment);
        // 更新文章的评论数量、最后评论人
        String articleId = comment.getArticleId();
        Article article = articleDao.findById(articleId);
        Assert.notNull(article, "保存评论失败:ID为[" + articleId + "]的文章不存在!");
        article.setLastCommentId(SecurityContext.getEmpId());
        article.setLastCommentName(SecurityContext.getEmpName());
        article.setLastCommentTime(new Date());
        article.setCommentCounts(article.getCommentCounts() == null ? 1 : (article.getCommentCounts() + 1));
        return id;
    }

    @Override
    public PageVo pageQuery(CommentBo bo) {
        Assert.notNull(bo, "查询文章评论：文章ID不能为空!");
        Assert.hasText(bo.getArticleId(), "查询文章评论：文章ID不能为空!");
        PageVo vo = new PageVo();
        Long total = commentDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Comment> commentList = commentDao.query(bo);
        List<CommentVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(commentList, CommentVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public CommentVo findById(String id) {
        Comment comment = commentDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(comment, CommentVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            commentDao.deleteById(id);
        }
    }

}
