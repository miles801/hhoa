package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 模块
 *
 * @author Michael
 */
public class Module extends CommonDomain implements AttachmentSymbol {
    @NotNull(message = "模块名称不能为空!")
    @Length(max = 40)
    private String name;

    @NotNull(message = "模块类型不能为空!")
    private String type;

    // 模块的logo
    private String logo;
    // 简介
    @Length(max = 1000, message = "简介的最大长度为500个汉字!")
    private String summary;

    // 排序号
    @Min(value = 0, message = "排序号最小值为:0")
    @Max(value = 10000, message = "排序号最大值为:10000")
    private Integer sequenceNo;

    // 过期时间
    private Date expiredDate;

    // 模块负责人
    @Length(max = 40)
    private String ownerId;
    @Length(max = 50)
    private String ownerName;

    // 帖子数量
    private Integer articleCounts;
    // 最后发帖时间
    private Date articleLastTime;

    // 最后发帖人
    private String articleLastPId;
    private String articleLastPName;

    public String getArticleLastPId() {
        return articleLastPId;
    }

    public void setArticleLastPId(String articleLastPId) {
        this.articleLastPId = articleLastPId;
    }

    public String getArticleLastPName() {
        return articleLastPName;
    }

    public void setArticleLastPName(String articleLastPName) {
        this.articleLastPName = articleLastPName;
    }

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

    @Override
    public String businessId() {
        return getId();
    }
}
