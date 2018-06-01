package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Classroom;

import java.util.List;
import java.util.Map;

/**
 * Created by zlx on 2018/5/28.
 */
public interface ClassRoomService extends IService<Classroom> {
    Classroom addClassRoom(String class_name, Integer school_id);
    List<Map<Object,Object>> getAllClassRoom(Integer school_id,String keyWord);
    void delRow(Integer id);
    Classroom  updateClassRoom(String class_name,Integer school_id,Integer id);
    List  getAllClassRoomBSchoolId(Integer school_id,String week,String classTimeStart,String classTimeEnd);

}
