package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.College;
import com.fzu.edu.model.School;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
public interface CollegeService extends IService<College> {
   List<Map<Object,Object>> getAllCollege(String college_code ,String college_name);
    void delCollege(Integer id);
    void delColleges(List ids);
    College addCollege(String college_code, String college_name,String college_detail,String  school_id);
    int collegeCodeUnique(String college_code);
    List<Map<Object, Object>> getAllCollegeForSelect(String school_id);


}
