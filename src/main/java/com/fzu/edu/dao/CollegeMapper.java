package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.College;

import java.util.List;
import java.util.Map;

public interface CollegeMapper extends BaseMapper<College>{
    List<Map<Object,Object>> getAllCollege(Map<String,  Object> params);
    void delColleges(List ids);
    College collegeCodeUnique(String college_code);
    List<Map<Object, Object>> getAllCollegeForSelect(String school_id);
}