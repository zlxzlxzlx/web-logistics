package com.fzu.edu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ElectiveMapper;
import com.fzu.edu.model.Elective;
import com.fzu.edu.service.ElectiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.x509.OIDMap;

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
            elective.setAbsenteeism(0);
            elective.setLate(0);
            elective.setSick_leave(0);
            elective.setThink_leave(0);
            elective.setOrdinary_grade(100);
            elective.setFinal_grade(0);
            elective.setFinal_exam_garde(0);
            elective.setNormal_proportion(0+"%");
            elective.setFinal_exam_proportion(0+"%");
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
    //获取课程底下的所有学生
    public  List<Map<Object,Object>>getAllStudentByCourseId(String course_id,String user_name){
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("course_id",course_id);
        map.put("user_name",user_name);
        return electiveMapper.getAllStudentByCourseId(map);
    }
    //获取课程底下的所有学生(课堂点名名单)
    public  List<Map<Object,Object>>getAllStudentByCourseIdForClass(String course_id){
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("course_id",course_id);
        return electiveMapper.getAllStudentByCourseIdForClass(map);
    }
    public void updateElectiveByClass(Integer elective_id,Integer mark){
        Elective elective=electiveMapper.selectById(elective_id);
        if(mark==1){
            elective.setAbsenteeism(elective.getAbsenteeism()+1);
            //旷课
        }
        if(mark==2){
           elective.setLate(elective.getLate()+1);
            //迟到
        }
        if(mark==3){
            elective.setSick_leave(elective.getSick_leave()+1);
            //病假
        }
        if(mark==4){
            elective.setThink_leave(elective.getThink_leave()+1);
            //事假
        }
        electiveMapper.updateById(elective);
    }

    public void updateFinalGrade(String params){

        JSONObject jsonObject=JSONObject.parseObject(params);
        JSONArray jsonArray=jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){
            Elective elective= electiveMapper.selectById(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
            elective.setNormal_proportion(jsonArray.getJSONObject(i).getString("normal_proportion"));
            elective.setFinal_exam_proportion(jsonArray.getJSONObject(i).getString("final_exam_proportion"));
            elective.setFinal_grade(Float.parseFloat(jsonArray.getJSONObject(i).getString("final_grade")));
            elective.setFinal_exam_garde(Float.parseFloat(jsonArray.getJSONObject(i).getString("final_exam_garde")));
            electiveMapper.updateById(elective);
        }

    }

}
