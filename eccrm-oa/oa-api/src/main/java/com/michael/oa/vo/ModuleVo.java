package com.michael.oa.vo;

import com.ycrl.base.common.CommonVo;

import java.util.Date;

/**
 * @author Michael
 */
public class ModuleVo extends CommonVo {
    private String name;

    private String type;
    private String typeName;

    // 模块的logo
    private String logo;
    // 简介
    private String summary;

    // 排序号
    private Integer sequenceNo;

    // 过期时间
    private Date expiredDate;

    // 模块负责人
    private String ownerId;
    private String ownerName;

    private String statusName;

    // 帖子数量
    private Integer articleCounts;
    // 最后发帖时间
    private Date articleLastTime;


    public Integer getArticleCounts() {
        return articleCounts;
    }

    public void setArticleCounts(Integer articleCounts) {
        this.articleCounts = articleCounts;
    }

    public Date getArticleLastTime() {
        return articleLastTime;
    }

    public void setArticleLastTime(Date articleLastTime) {
        this.articleLastTime = articleLastTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
