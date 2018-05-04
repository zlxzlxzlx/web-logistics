package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.School;
import com.sun.javafx.collections.MappingChange;

import java.util.List;
import java.util.Map;

public interface SchoolMapper extends BaseMapper<School>{
   List<Map<Object,Object>> getAllSchool(Map<String,  Object> params);
    void delSchools(List ids);
    School schoolCodeUnique(String school_code);
}