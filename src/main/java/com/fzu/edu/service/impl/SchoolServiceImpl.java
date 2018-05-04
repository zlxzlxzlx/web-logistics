package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.dao.UserInfoMapper;
import com.fzu.edu.model.School;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.SchoolService;
import com.fzu.edu.service.UserInfoService;
import com.fzu.edu.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/5/2.
 */
@Service("schoolService")
@Transactional(rollbackFor = Exception.class)
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper,School> implements SchoolService{
  @Resource
  private  SchoolMapper schoolMapper;
   public   List<Map<Object, Object>> getAllSchool(String school_code,String school_name){
    Map<String,Object> map=new HashMap<String, Object>();
       map.put("school_code",school_code);
       map.put("school_name",school_name);
       return schoolMapper.getAllSchool(map);
    }

    public   void  delSchool(Integer id){
      School school=schoolMapper.selectById(id);
        school.setFlag(1);
        schoolMapper.updateById(school);
    }
    public void delSchools(List ids){
        schoolMapper.delSchools(ids);
    }

    public int schoolCodeUnique(String school_code){
        int result = -1;
        School school =  schoolMapper.schoolCodeUnique(school_code);
        if(school!=null){
            result = 1;
        }
        return result;
    }
    public    School addSchool(String school_code, String school_name,String school_detail){
        School school=new School();
        school.setFlag(0);
        school.setSchoolCode(school_code);
        school.setSchoolName(school_name);
        school.setSchoolDetail(school_detail);
        schoolMapper.insert(school);
        return school;
    }
}
