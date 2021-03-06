package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 帖子、文章
 *
 * @author Michael
 */
public class Article extends CommonDomain implements AttachmentSymbol {

    @NotNull(message = "标题")
    @Length(max = 50, min = 2, message = "标题必须是2-50个汉字!")
    private String title;

    @NotNull(message = "所属模块")
    @Length(max = 40)
    private String moduleId;
    @Length(max = 40)
    private String moduleName;

    @NotNull(message = "作者")
    private String authorId;
    @NotNull(message = "作者名称")
    private String authorName;
    // 发布时间
    private Date publishTime;

    // 摘要
    @Length(max = 200, message = "摘要不能超过100个汉字!")
    private String summary;

    @NotNull(message = "内容")
    private String content;
    // 视频地址
    @Length(max = 200, message = "视频地址的长度只能是200个字符!")
    private String videoUrl;

    // 关键字
    @Length(max = 40, message = "关键字不能超过20个汉字!")
    private String keywords;

    // 浏览量
    private Integer viewCounts;

    // 评论数量
    private Integer commentCounts;
    // 最后访问时间
    private Date lastViewTime;
    // 最后评论时间
    private Date lastCommentTime;
    // 最后一次进行评论的人
    private String lastCommentId;
    private String lastCommentName;

    public Integer getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public Date getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Date lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }

    public String getLastCommentId() {
        return lastCommentId;
    }

    public void setLastCommentId(String lastCommentId) {
        this.lastCommentId = lastCommentId;
    }

    public String getLastCommentName() {
        return lastCommentName;
    }

    public void setLastCommentName(String lastCommentName) {
        this.lastCommentName = lastCommentName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String businessId() {
        return getId();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
