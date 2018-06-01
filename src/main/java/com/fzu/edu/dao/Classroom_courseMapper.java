package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Classroom_course;

import java.util.List;
import java.util.Map;

public interface Classroom_courseMapper extends BaseMapper<Classroom_course>{
   Classroom_course selectByClassId(Map<String ,Object>map);
    void delClassroomCourseById(Integer id);
}