package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Student_course;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
public interface StudentCourseService extends IService<Student_course> {

    void addStudentCourse(String params);
    List<Map<Object,Object>>  getAllStudentByCourseId(Integer course_id,String user_name,Integer teacher_id);
    List<Map<Object,Object>> getAllStudentByCourseIdForClass(Integer course_id,Integer teacher_id);
    void updateElectiveByClass(Integer elective_id,Integer mark,Float value);
}
