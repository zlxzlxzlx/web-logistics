package com.fzu.edu.model;

public class Classroom_course {
    private Integer id;

    private Integer classId;

    private Integer week;

    private Integer freeStart;

    private Integer freeEnd;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getFreeStart() {
        return freeStart;
    }

    public void setFreeStart(Integer freeStart) {
        this.freeStart = freeStart;
    }

    public Integer getFreeEnd() {
        return freeEnd;
    }

    public void setFreeEnd(Integer freeEnd) {
        this.freeEnd = freeEnd;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}