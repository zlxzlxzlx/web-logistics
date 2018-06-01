package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Student_course;

import java.util.List;
import java.util.Map;

public interface Student_courseMapper extends BaseMapper<Student_course>{
    List<Map<Object,Object>> selectByCTU(Map<String ,Object>map);
    List<Map<Object,Object>>  getAllStudentByCourseId(Map<String ,Object>map);
    List<Map<Object,Object>> getAllStudentByCourseIdForClass(Map<String ,Object>map);
}