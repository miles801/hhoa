package com.michael.oa.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

/**
 * @author Michael
 */
public class KnowledgeBo implements BO{

    @Condition(matchMode = MatchModel.LIKE,likeMode = LikeModel.ANYWHERE)
    private String title;

    @Condition
    private String type;

    @Condition(matchMode = MatchModel.LIKE,likeMode = LikeModel.ANYWHERE)
    private String keywords;

    @Condition(matchMode = MatchModel.LIKE,likeMode = LikeModel.ANYWHERE)
    private String content;

    @Condition
    private String orgId;

    @Condition
    private String status;

    private String keywordsOrTitle;

    public String getKeywordsOrTitle() {
        return keywordsOrTitle;
    }

    public void setKeywordsOrTitle(String keywordsOrTitle) {
        this.keywordsOrTitle = keywordsOrTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
