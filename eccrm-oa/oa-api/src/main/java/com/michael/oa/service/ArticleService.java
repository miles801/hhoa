package com.michael.oa.service;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.domain.Article;
import com.michael.oa.vo.ArticleVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface ArticleService {

    /**
     * 保存
     */
    String save(Article article);

    /**
     * 更新
     */
    void update(Article article);

    /**
     * 分页查询
     */
    PageVo pageQuery(ArticleBo bo);

    /**
     * 根据ID查询对象的信息
     */
    ArticleVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    /**
     * 批量发布
     *
     * @param ids
     */
    void publish(String[] ids);

    /**
     * 阅读文章
     * 如果文章已经被阅读过，则返回null
     * 如果成功阅读文章，返回最新的阅读数量
     *
     * @param articleId 文章ID
     * @return 最新的阅读数量
     */
    Integer view(String articleId);
}
