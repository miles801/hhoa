package com.michael.oa.dao;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.domain.Article;
import com.michael.oa.vo.ArticleVo;
import java.util.List;

/**
 * @author Michael
 */
public interface ArticleDao {

    String save(Article article);

    void update(Article article);

    /**
     * 高级查询接口
     */
    List<Article> query(ArticleBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(ArticleBo bo);

    Article findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Article article);
}
