package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Elective;

import java.util.List;
import java.util.Map;

public interface ElectiveMapper extends BaseMapper<Elective> {

    Elective selectByUserId(Map<String,  Object> params);
   List<Map<Object,Object>> getAllElectiveByUserId(Map<String,Object>params);
    List<Map<Object,Object>> getAllCourseForStudentFromElective(Map<String ,Object> params);
    List<Map<Object,Object>> getAllStudentByCourseId(Map<String ,Object> map);
    List<Map<Object,Object>> getAllStudentByCourseIdForClass(Map<String ,Object> map);
}