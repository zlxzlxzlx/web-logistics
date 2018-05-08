package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Course;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.Userinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3.
 */
public interface CourseService extends IService<Course> {
   void addCourseRow(String code,String name,String class_hour,Integer type,Integer teacher_id,Integer major_id,Integer school_id,String class_time,String start_time,String end_time);
   void updateCourseRow(Integer id,String code,String name,String class_hour,Integer type,Integer teacher_id,Integer major_id,Integer school_id, String class_time,String start_time,String end_time);
   List<HashMap> getAllCourse(String name, String code);
   void delCourse(Integer id);
   void delCourses(List ids);
   List getAllTeacherAndMajor();
}
