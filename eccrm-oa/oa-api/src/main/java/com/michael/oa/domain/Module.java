package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;
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
public class Module extends CommonDomain {
    @NotNull(message = "模块名称不能为空!")
    private String name;

    @NotNull(message = "模块类型不能为空!")
    private String type;

    private String picture;
    private String video;
    // 简介
    @Length(max = 1000, message = "简介的最大长度为500个汉字!")
    private String summary;

    // 是否顶置
    private Boolean isTop;

    // 是否允许评论
    private Boolean allowComment;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
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
