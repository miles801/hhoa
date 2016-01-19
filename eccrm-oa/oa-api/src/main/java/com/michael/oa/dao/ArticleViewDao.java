package com.michael.oa.dao;

import com.michael.oa.bo.ArticleViewBo;
import com.michael.oa.domain.ArticleView;

import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
public interface ArticleViewDao {

    String save(ArticleView articleView);

    void update(ArticleView articleView);

    /**
     * 高级查询接口
     */
    List<ArticleView> query(ArticleViewBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(ArticleViewBo bo);

    ArticleView findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(ArticleView articleView);

    /**
     * 查看指定文章是否被某人阅读过
     *
     * @param articleId 文章ID
     * @param userId    阅读者ID
     * @return 阅读时间
     */
    Date find(String articleId, String userId);
}
