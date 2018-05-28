package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ClassRoomMapper;
import com.fzu.edu.model.Classroom;
import com.fzu.edu.service.ClassRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/28.
 */
@Service("classRoomService")
@Transactional(rollbackFor = Exception.class)
public class ClassRoomServiceImpl extends ServiceImpl<ClassRoomMapper,Classroom> implements ClassRoomService{
    @Resource
    private ClassRoomMapper classRoomMapper;
    public Classroom addClassRoom(String class_name, Integer school_id){
        Classroom classRoom=new Classroom();
        classRoom.setClassName(class_name);
        classRoom.setSchoolId(school_id);
        classRoom.setFlag(0);
        classRoomMapper.insert(classRoom);
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
}
