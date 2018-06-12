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
   void addCourseRow(String code,String name,String class_hour,Integer major_id,Integer school_id);
   void updateCourseRow(Integer id,String code,String name,Integer major_id,Integer school_id, String class_hour);
   List<HashMap> getAllCourse(String name, String code,Integer college_id,Integer school_id);
   void delCourse(Integer id);
   void delCourses(List ids);
   List getAllTeacherAndMajor();
   List<Map<Object, Object>> getAllCourseBySchoolId(Integer school_id,Integer college,String keyWord,Integer mark);
  List<Map<Object, Object>> getAllCourseForStudent(Integer school_id,Integer college_id,Integer user_id,String keyWord,Integer mark);
   List<Map<Object, Object>> getCourseForClass(Integer user_id);
}
