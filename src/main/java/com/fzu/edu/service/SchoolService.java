package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.School;
import com.fzu.edu.model.Userinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
public interface SchoolService extends IService<School> {
    List<Map<Object, Object>> getAllSchool(String school_code,String school_name);
    List<Map<Object, Object>> getAllSchoolForSelect();
    void delSchool(Integer id);
    void delSchools(List ids);
    int schoolCodeUnique(String school_code);
    School addSchool(String school_code, String school_name,String school_detail);
    School login(String account,String pwd);
    School getSchoolId(String code);
}
