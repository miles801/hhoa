package eccrm.base.position.vo;


/**
 * Generated by chenl on 2014-10-13.
 */

public class ClassifyVo {

    private String id;
    private String name;
    private String parentId;
    private Integer seqNo;
    private String parentName;
    /**
     * 用于表明是否具有子节点
     */
    private Boolean isParent;
    /**
     * 用于表明是否具有岗位
     */
    private Boolean hasPosition;
    private String path;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(Boolean hasPosition) {
        this.hasPosition = hasPosition;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
}
