package com.fzu.edu.model;

public class College {
    private Integer id;

    private String collegeCode;

    private String collegeName;

    private Integer flag;

    public String  getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    private String school_id;

    private String collegeDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode == null ? null : collegeCode.trim();
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCollegeDetail() {
        return collegeDetail;
    }

    public void setCollegeDetail(String collegeDetail) {
        this.collegeDetail = collegeDetail == null ? null : collegeDetail.trim();
    }
}