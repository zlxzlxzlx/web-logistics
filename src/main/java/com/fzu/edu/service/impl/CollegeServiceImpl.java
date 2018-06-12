package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.CollegeMapper;
import com.fzu.edu.dao.CourseMapper;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.model.College;
import com.fzu.edu.model.School;
import com.fzu.edu.service.CollegeService;
import com.fzu.edu.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/5/2.
 */
@Service("collegeService")
@Transactional(rollbackFor = Exception.class)
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper,College> implements CollegeService{
  @Resource
  private CollegeMapper collegeMapper;
    @Resource
  private CourseMapper courseMapper;
    @Resource
    private SchoolMapper schoolMapper;
  public List<Map<Object,Object>> getAllCollege(String college_code , String college_name,Integer school_id){
      Map<String ,Object> map =new HashMap<String, Object>();
      map.put("college_code",college_code);
      map.put("college_name",college_name);
      map.put("school_id",school_id);
      return collegeMapper.getAllCollege(map);
  }
    public  void delCollege(Integer id){
        College college=collegeMapper.selectById(id);
        college.setFlag(1);
        collegeMapper.updateById(college);
    }
    public  void delColleges(List ids){
        collegeMapper.delColleges(ids);
    }
    public    College addCollege(String college_code, String college_name,String college_detail,String user_code,String school_id){
        School school = schoolMapper.selectByAccount(user_code);
        College college=new College();
        college.setFlag(0);
        college.setCollegeCode(college_code);
        college.setCollegeName(college_name);
        college.setCollegeDetail(college_detail);
        if (school!=null){
            college.setSchool_id(school.getId().toString());
        }else {
            college.setSchool_id(school_id);
        }

        collegeMapper.insert(college);
        return college;
    }

    public int collegeCodeUnique(String college_code){
        int result = -1;
        College college =  collegeMapper.collegeCodeUnique(college_code);
        if(college!=null){
            result = 1;
        }
        return result;
    }
    public  List<Map<Object, Object>> getAllCollegeForSelect(String school_id){
        return collegeMapper.getAllCollegeForSelect(school_id);
    }

}
