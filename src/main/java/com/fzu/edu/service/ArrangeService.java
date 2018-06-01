package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Arrange;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface ArrangeService extends IService<Arrange> {
    void teachingManage(Integer class_id,Integer course_id,Integer start_week,Integer end_week,String week,String classTimeStart,String classTimeEnd,Integer user_id);
    List<Map<Object,Object>> getAllArrange(Integer user_id,Integer course_id);
    List<Map<Object, Object>> getCourseForClass(Integer user_id);
}
