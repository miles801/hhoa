package eccrm.base.employee.vo;

import com.ycrl.base.common.CommonVo;

/**
* Generated by liuxq on 2014-10-22.
*/

public class BlankListVo extends CommonVo {

    private String titleTxt;
    private String busMatter;
    /**
     * 岗位id
     */
    private String dutyId;
    private String busMatterName ;
    private String statusName ;
    private String dutyName ;
    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getBusMatter() {
        return busMatter;
    }

    public void setBusMatter(String busMatter) {
        this.busMatter = busMatter;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public String getBusMatterName() {
        return busMatterName;
    }

    public void setBusMatterName(String busMatterName) {
        this.busMatterName = busMatterName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }
}
