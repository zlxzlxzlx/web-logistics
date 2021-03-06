package com.fzu.edu.model;

public class Student_course {
    private Integer id;

    private Integer courseId;

    private Integer teacherId;

    private Integer userId;

    private Integer sick_leave;
    private Integer think_leave;
    private Integer flag;
    private Integer late;
    private Integer absenteeism;
    private float ordinary_grade;

    public void setOrdinary_grade(float ordinary_grade) {
        this.ordinary_grade = ordinary_grade;
    }

    public void setNormal_proportion(String normal_proportion) {
        this.normal_proportion = normal_proportion;
    }

    public void setFinal_exam_proportion(String final_exam_proportion) {
        this.final_exam_proportion = final_exam_proportion;
    }

    public void setFinal_grade(float final_grade) {
        this.final_grade = final_grade;
    }

    public void setFinal_exam_garde(float final_exam_garde) {
        this.final_exam_garde = final_exam_garde;
    }

    private float final_exam_garde;

    public float getOrdinary_grade() {
        return ordinary_grade;
    }

    public float getFinal_exam_garde() {
        return final_exam_garde;
    }

    public float getFinal_grade() {
        return final_grade;
    }

    public String getFinal_exam_proportion() {
        return final_exam_proportion;
    }

    public String getNormal_proportion() {
        return normal_proportion;
    }

    private float final_grade;
    private String final_exam_proportion;
    private String normal_proportion;

    public void setSick_leave(Integer sick_leave) {
        this.sick_leave = sick_leave;
    }

    public void setAbsenteeism(Integer absenteeism) {
        this.absenteeism = absenteeism;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public void setThink_leave(Integer think_leave) {
        this.think_leave = think_leave;
    }

    public Integer getThink_leave() {
        return think_leave;
    }

    public Integer getLate() {
        return late;
    }

    public Integer getSick_leave() {
        return sick_leave;
    }

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}