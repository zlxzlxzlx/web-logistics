package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.CollegeMapper;
import com.fzu.edu.dao.MajorMapper;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.model.College;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.School;
import com.fzu.edu.service.MajorService;
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
@Service("majorService")
@Transactional(rollbackFor = Exception.class)
public class MajorServiceImpl extends ServiceImpl<MajorMapper,Major> implements MajorService{

    @Resource
    private MajorMapper majorMapper;
    public List<Map<Object,Object>> getAllMajor(String major_code , String major_name){
        Map<String ,Object> map =new HashMap<String, Object>();
        map.put("major_code",major_code);
        map.put("major_name",major_name);
        return majorMapper.getAllMajor(map);
    }
    public  void delMajor(Integer id){
        Major major=majorMapper.selectById(id);
        major.setFlag(1);
        majorMapper.updateById(major);
    }
    public  void delMajors(List ids){
        majorMapper.delMajors(ids);
    }
    public    Major addMajor(String major_code,String  major_name,String major_detail){
      Major major=new Major();
        major.setFlag(0);
        major.setMajorCode(major_code);
        major.setMajorDetail(major_detail);
        major.setMajorName(major_name);
        majorMapper.insert(major);
        return major;
    }
    public int majorCodeUnique(String major_code){
        int result = -1;
        Major major =  majorMapper.majorCodeUnique(major_code);
        if(major!=null){
            result = 1;
        }
        return result;
    }
}
