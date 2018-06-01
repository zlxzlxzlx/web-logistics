package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ClassRoomMapper;
import com.fzu.edu.dao.Classroom_courseMapper;
import com.fzu.edu.model.Classroom;
import com.fzu.edu.model.Classroom_course;
import com.fzu.edu.service.ClassRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2018/5/28.
 */
@Service("classRoomService")
@Transactional(rollbackFor = Exception.class)
public class ClassRoomServiceImpl extends ServiceImpl<ClassRoomMapper,Classroom> implements ClassRoomService{
    @Resource
    private ClassRoomMapper classRoomMapper;
    @Resource
    private Classroom_courseMapper classroom_courseMapper;
    public Classroom addClassRoom(String class_name, Integer school_id){
        Classroom classRoom=new Classroom();
        classRoom.setClassName(class_name);
        classRoom.setSchoolId(school_id);
        classRoom.setFlag(0);
        classRoomMapper.insert(classRoom);
        for(int i=1;i<8;i++){
            Classroom_course classroom_course=new Classroom_course();
            classroom_course.setClassId(classRoom.getId());
            classroom_course.setWeek(i);
            classroom_course.setFreeStart(1);
            classroom_course.setFreeEnd(11);
            classroom_course.setFlag(0);
            classroom_courseMapper.insert(classroom_course);
        }
        return classRoom;
    }
    public List<Map<Object,Object>> getAllClassRoom(Integer school_id, String keyWord){
    Map<String ,Object> map =new HashMap<String, Object>();
        map.put("school_id",school_id);
        map.put("keyWord",keyWord);
       return classRoomMapper.getAllClassRoom(map);
    }
    public  void delRow(Integer id){
        Classroom classroom=classRoomMapper.selectById(id);
        classroom.setFlag(1);
        classRoomMapper.updateById(classroom);
    }
    public  Classroom  updateClassRoom(String class_name,Integer school_id,Integer id){

        Classroom classroom=classRoomMapper.selectById(id);
        classroom.setSchoolId(school_id);
        classroom.setClassName(class_name);
        classRoomMapper.updateById(classroom);
        return  classroom;
    }
    public  List getAllClassRoomBSchoolId(Integer school_id,String week,String classTimeStart,String classTimeEnd){
        String[]  strWeek = new String[20];
        String[]  strClassTime = new String[20];
        String[]  endClassTime = new String[20];
        strWeek=week.split("/");
        strClassTime=classTimeStart.split("/");
        endClassTime=classTimeEnd.split("/");
            Map<String ,Object> map =new HashMap<String, Object>();
            map.put("school_id",school_id);
            map.put("week",strWeek[0]);
            map.put("strClassTime",strClassTime[0]);
            map.put("endClassTime",endClassTime[0]);
            List<Map<String ,Object>> list1=classRoomMapper.getAllClassRoomBSchoolId(map);
            for(int i=1;i<strWeek.length;i++){
                Map<String ,Object> map2 =new HashMap<String, Object>();
                map2.put("school_id",school_id);
                map2.put("week",strWeek[i]);
                map2.put("strClassTime",strClassTime[i]);
                map2.put("endClassTime",endClassTime[i]);
                List<Map<String ,Object>> list=classRoomMapper.getAllClassRoomBSchoolId(map2);
                for(int j=0;j<list1.size();j++){
                    int count=0;
                    for(int k=0;k<list.size();k++){
                        if(!(list1.get(j).get("class_id").equals(list.get(k).get("class_id")))){
                            count++;
                        }
                    }
                    if(count==list.size()){
                        list1.remove(j);
                    }
                }
        }
        return  list1;


    }
}
