package com.fzu.edu.model;

public class Major {
    private Integer id;

    private String majorCode;

    private String majorName;

    private Integer flag;

    private String majorDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode == null ? null : majorCode.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMajorDetail() {
        return majorDetail;
    }

    public void setMajorDetail(String majorDetail) {
        this.majorDetail = majorDetail == null ? null : majorDetail.trim();
    }
}