package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Elective;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
public interface ElectiveService extends IService<Elective> {
    int addElectiveForStudent(String course_id,Integer user_id);
    List<Map<Object,Object>> getAllElectiveByUserId(Integer user_id,String keyWord,Integer mark);
    void delSelectElective(Integer id);
    List<Map<Object,Object>>getAllStudentByCourseId(String course_id,String user_name);
    List<Map<Object,Object>>getAllStudentByCourseIdForClass(String course_id);
    void updateElectiveByClass(Integer elective_id,Integer mark,Float value);
    void updateFinalGrade(String params);
}
