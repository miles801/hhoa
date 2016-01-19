package com.michael.oa.vo;

import com.ycrl.base.common.CommonVo;

/**
 * @author Michael
 */
public class CommentVo extends CommonVo {

    private String articleId;
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
