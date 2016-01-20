package com.michael.oa.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.MatchModel;

import java.util.Date;

/**
 * @author Michael
 */
public class WorkLogCommentBo implements BO {

    @Condition
    private String workLogId;

    @Condition
    private String creatorId;

    @Condition(matchMode = MatchModel.GE, target = "createdDatetime")
    private Date startDate;
    @Condition(matchMode = MatchModel.LT, target = "createdDatetime")
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(String workLogId) {
        this.workLogId = workLogId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
