package com.fzu.edu.model;

public class purviewType {
    private Integer id;

    private String purviewName;

    private String purviewCode;

    private String parentPurviewCode;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurviewName() {
        return purviewName;
    }

    public void setPurviewName(String purviewName) {
        this.purviewName = purviewName == null ? null : purviewName.trim();
    }

    public String getPurviewCode() {
        return purviewCode;
    }

    public void setPurviewCode(String purviewCode) {
        this.purviewCode = purviewCode == null ? null : purviewCode.trim();
    }

    public String getParentPurviewCode() {
        return parentPurviewCode;
    }

    public void setParentPurviewCode(String parentPurviewCode) {
        this.parentPurviewCode = parentPurviewCode == null ? null : parentPurviewCode.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}