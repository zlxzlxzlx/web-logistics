package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.College;
import com.fzu.edu.model.Major;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
public interface MajorService extends IService<Major> {
    List<Map<Object,Object>> getAllMajor(String major_code , String major_name);
    void delMajor(Integer id);
    void delMajors(List ids);
   Major addMajor(String major_code,String  major_name,String major_detail);
   int majorCodeUnique(String major_code);
}
