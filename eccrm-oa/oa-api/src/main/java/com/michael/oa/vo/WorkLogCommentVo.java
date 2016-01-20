package com.michael.oa.vo;

import com.ycrl.base.common.CommonVo;

/**
 * @author Michael
 */
public class WorkLogCommentVo extends CommonVo {
    private String workLogId;
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
