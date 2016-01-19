package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;

import javax.validation.constraints.NotNull;

/**
 * @author Michael
 */
public class ArticleView extends CommonDomain {

    @NotNull(message = "文章ID不能为空!")
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
