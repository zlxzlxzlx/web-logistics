package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ElectiveMapper;
import com.fzu.edu.model.Elective;
import com.fzu.edu.service.ElectiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
@Service("electiveService")
@Transactional(rollbackFor = Exception.class)
public class ElectiveServiceImpl extends ServiceImpl<ElectiveMapper,Elective> implements ElectiveService{

    @Resource
    private  ElectiveMapper electiveMapper;
    public  int addElectiveForStudent(String course_id,Integer user_id){
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("course_id",course_id);
        map.put("user_id",user_id);
        Elective electiveTmp=electiveMapper.selectByUserId(map);
        if(electiveTmp==null){
            Elective elective=new Elective();
            elective.setUserId(user_id);
            elective.setCourseId(course_id);
            elective.setFlag(0);
            electiveMapper.insert(elective);
            return 1;
        }else{
            return 2;
        }
    }
    //选课结果
    public List<Map<Object,Object>> getAllElectiveByUserId(Integer user_id,String keyWord,Integer mark){
        Map<String ,Object> map =new HashMap<String, Object>();
        map.put("user_id",user_id);
        map.put("keyWord",keyWord);
        map.put("mark",mark);
        return electiveMapper.getAllElectiveByUserId(map);

    }
    //退选
    public  void delSelectElective(Integer id){
      Elective elective=electiveMapper.selectById(id);
        elective.setFlag(1);
        electiveMapper.updateById(elective);
    }
}
