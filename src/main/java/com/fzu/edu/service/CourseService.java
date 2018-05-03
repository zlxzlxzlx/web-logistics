package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3.
 */
public interface CourseService extends IService<Course> {
   void addCourseRow(String code,String name,String class_hour,Integer type,String teacher_name,String class_time,String start_time,String end_time);
   void updateCourseRow(Integer id,String code,String name,String class_hour,Integer type,String teacher_name,String class_time,String start_time,String end_time);
   List<Course> getAllCourse(String name, String code);
   void delCourse(Integer id);
   void delCourses(List ids);
}
