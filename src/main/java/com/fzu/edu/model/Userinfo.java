package com.fzu.edu.model;

public class Userinfo {
    private Integer id;

    private String userName;

    private Integer mark;

    private String imageUrl;
    private String passwd;

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    private Long registDate;

    private Long lastLoginDate;

    private Integer flag;
    private Integer login_method;

    public Integer getCollege_id() {
        return college_id;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public void setCollege_id(Integer college_id) {
        this.college_id = college_id;
    }

    private Integer school_id;
    private Integer college_id;

    public void setLogin_method(Integer login_method) {
        this.login_method = login_method;
    }

    public Integer getLogin_method() {
        return login_method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName ;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Long registDate) {
        this.registDate = registDate;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}