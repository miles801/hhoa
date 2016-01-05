package com.michael.oa.vo;

import com.ycrl.base.common.CommonVo;

/**
 * @author Michael
 */
public class BlackListVo extends CommonVo {
    private String type;
    private String typeName;

    private String name;

    // 客户信息
    private String info;

    // 原因
    private String reason;

    // 关键字
    private String keywords;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
