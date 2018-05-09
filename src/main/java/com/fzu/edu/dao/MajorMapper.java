package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Major;

import java.util.List;
import java.util.Map;

public interface MajorMapper extends BaseMapper<Major>{
    List<Map<Object,Object>> getAllMajor(Map<String,  Object> params);
    void delMajors(List ids);
    Major majorCodeUnique(String major_code);
}