package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 工作日志
 *
 * @author Michael
 */
public class WorkLog extends CommonDomain {

    // 日志内容
    @NotNull(message = "工作日志内容不能为空!")
    private String content;

    // 日志日期
    @NotNull(message = "发生日期不能为空!")
    private Date occurDate;

    // 评论数
    private Integer commentCounts;

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
}
