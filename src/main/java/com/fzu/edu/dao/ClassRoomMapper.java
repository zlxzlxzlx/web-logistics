package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Classroom;
import com.fzu.edu.model.Classroom_course;

import java.util.List;
import java.util.Map;

public interface ClassRoomMapper extends BaseMapper<Classroom>{
  List<Map<Object,Object>> getAllClassRoom(Map<String ,Object>map);
  List<Map<String ,Object>> getAllClassRoomBSchoolId(Map<String ,Object>map);
}