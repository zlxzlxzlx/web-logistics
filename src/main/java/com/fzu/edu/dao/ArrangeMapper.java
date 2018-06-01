package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Arrange;

import java.util.List;
import java.util.Map;

public interface ArrangeMapper extends BaseMapper<Arrange>{
    List<Map<Object,Object>> getAllArrange(Map<String ,Object>map);
    List<Map<Object, Object>> getCourseForClass(Integer user_id);
}