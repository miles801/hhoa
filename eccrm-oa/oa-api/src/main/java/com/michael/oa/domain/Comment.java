package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;

import javax.validation.constraints.NotNull;

/**
 * 评论
 *
 * @author Michael
 */
public class Comment extends CommonDomain {

    @NotNull(message = "文章ID不能为空!")
    private String articleId;
    @NotNull(message = "评论内容不能为空!")
    private String content;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
