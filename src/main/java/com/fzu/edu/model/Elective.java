package com.fzu.edu.model;

public class Elective {
    private Integer id;

    private Integer userId;

    private String courseId;

    private Integer flag;

    public Integer getSick_leave() {
        return sick_leave;
    }

    public Integer getThink_leave() {
        return think_leave;
    }

    public Integer getLate() {
        return late;
    }

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public void setSick_leave(Integer sick_leave) {
        this.sick_leave = sick_leave;
    }

    public void setThink_leave(Integer think_leave) {
        this.think_leave = think_leave;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public void setAbsenteeism(Integer absenteeism) {
        this.absenteeism = absenteeism;
    }

    private Integer sick_leave;
    private Integer think_leave;
    private Integer late;
    private Integer absenteeism;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}