package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Course;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CourseMapper extends BaseMapper<Course> {
    int deleteByPrimaryKey(Integer id);

    void insert1(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    List<HashMap> getAllCourse(Map<String,Object> map);
    void delCourses(List ids);
    List<HashMap> getAllTeacher();
    List<HashMap> getAllCollege();
    List<HashMap> getAllSchool();
    List<Map<Object,Object>> getAllCourseBySchoolId(Map<String,Object> map);
    List<Map<Object,Object>> getAllCourseForStudentFromCourse(Map<String,Object> map);
}