package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ArrangeMapper;
import com.fzu.edu.dao.Classroom_courseMapper;
import com.fzu.edu.model.Arrange;
import com.fzu.edu.model.Classroom_course;
import com.fzu.edu.service.ArrangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/29.
 */
@Service("arrangeService")
@Transactional(rollbackFor = Exception.class)
public class ArrangeServiceImpl extends ServiceImpl<ArrangeMapper,Arrange> implements ArrangeService{

    @Resource
    private ArrangeMapper arrangeMapper;
    @Resource
    private Classroom_courseMapper classroom_courseMapper;
    public void teachingManage(Integer class_id,Integer course_id,Integer start_week,Integer end_week,String week,String classTimeStart,String classTimeEnd,Integer user_id){
        String[]  strWeek = new String[20];
        String[]  strClassTime = new String[20];
        String[]  endClassTime = new String[20];
        strWeek=week.split("/");
        strClassTime=classTimeStart.split("/");
        endClassTime=classTimeEnd.split("/");
        for(int i=0;i<strWeek.length;i++){
            Arrange arrange=new Arrange();
            arrange.setClassId(class_id);
            arrange.setCourseId(course_id);
            arrange.setStartWeek(start_week);
            arrange.setEndWeek(end_week);
            arrange.setWeek(Integer.parseInt(strWeek[i]));
            arrange.setSectionNumber(strClassTime[i]+"-"+endClassTime[i]);
            arrange.setUser_id(user_id);
            arrange.setFlag(0);
            arrangeMapper.insert(arrange);
            Map<String ,Object> map=new HashMap<String, Object>();
            map.put("class_id",class_id);
            map.put("week",strWeek[i]);
            map.put("strClassTime",strClassTime[i]);
            map.put("endClassTime",endClassTime[i]);
           Classroom_course classroom_course=classroom_courseMapper.selectByClassId(map);
            int ff=Integer.parseInt(strClassTime[i]);
            int ee=Integer.parseInt(endClassTime[i]);
            if(ff-classroom_course.getFreeStart()>1){
                Classroom_course classroom_course1=new Classroom_course();
                classroom_course1.setClassId(class_id);
                classroom_course1.setWeek(Integer.parseInt(strWeek[i]));
                classroom_course1.setFreeStart(classroom_course.getFreeStart());
                classroom_course1.setFreeEnd(ff-1);
                classroom_course1.setFlag(0);
                classroom_courseMapper.insert(classroom_course1);
            }if(classroom_course.getFreeEnd()-ee>1){
                Classroom_course classroom_course2=new Classroom_course();
                classroom_course2.setClassId(class_id);
                classroom_course2.setWeek(Integer.parseInt(strWeek[i]));
                classroom_course2.setFreeStart(ee+1);
                classroom_course2.setFreeEnd(classroom_course.getFreeEnd());
                classroom_course2.setFlag(0);
                classroom_courseMapper.insert(classroom_course2);
            }
            classroom_courseMapper.delClassroomCourseById(classroom_course.getId());
        }

    }
    public List<Map<Object,Object>> getAllArrange(Integer user_id,Integer course_id){
        Map<String ,Object> map =new HashMap<String, Object>();
        map.put("user_id",user_id);
        map.put("course_id",course_id);
        return  arrangeMapper.getAllArrange(map);
    }
    public  List<Map<Object, Object>> getCourseForClass(Integer user_id){
        return arrangeMapper.getCourseForClass(user_id);
    }
}
