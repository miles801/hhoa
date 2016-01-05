package com.michael.oa.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 知识
 *
 * @author Michael
 */
public class Knowledge extends CommonDomain implements AttachmentSymbol {
    @NotNull
    @Length(max = 100, message = "标题最多为50个汉字!")
    private String title;

    @NotNull(message = "知识类型不能为空!")
    @Length(max = 40)
    private String type;

    @NotNull
    @Length(max = 100, message = "关键字最多为50个汉字!")
    private String keywords;

    // 外部链接
    @Length(max = 1000, message = "链接地址过长!")
    private String url;

    @NotNull(message = "内容不能为空!")
    private String content;

    // 机构ID，用作数据权限用
    @Length(max = 40)
    private String orgId;
    @Length(max = 60)
    private String orgName;

    @Override
    public String businessId() {
        return getId();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
