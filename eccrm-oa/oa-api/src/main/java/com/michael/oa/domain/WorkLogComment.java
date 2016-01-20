package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;

import javax.validation.constraints.NotNull;

/**
 * 日志评论
 *
 * @author Michael
 */
public class WorkLogComment extends CommonDomain {

    // 工作日志ID
    @NotNull(message = "日志评论必须关联日志!")
    private String workLogId;

    // 评论内容
    @NotNull(message = "评论内容不能为空!")
    private String content;

    public String getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(String workLogId) {
        this.workLogId = workLogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
