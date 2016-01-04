package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 黑户
 *
 * @author Michael
 */
@Entity
@Table(name = "OA_BLACK_LIST")
public class BlackList extends CommonDomain {
    @NotNull(message = "黑户类型不能为空!")
    @Length(max = 40)
    private String type;

    @NotNull(message = "客户名称不能为空!")
    @Length(max = 60)
    private String name;


    // 客户信息
    @Length(max = 100)
    private String info;

    // 原因
    @Length(max = 1000)
    private String reason;

    // 关键字
    @Length(max = 100)
    private String keywords;

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
