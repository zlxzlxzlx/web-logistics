package com.fzu.edu.model;

public class School {
    private Integer id;

    private String schoolCode;

    private String schoolName;

    private Integer flag;

    private String schoolDetail;

    private String passwd;

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSchoolDetail() {
        return schoolDetail;
    }

    public void setSchoolDetail(String schoolDetail) {
        this.schoolDetail = schoolDetail == null ? null : schoolDetail.trim();
    }
}