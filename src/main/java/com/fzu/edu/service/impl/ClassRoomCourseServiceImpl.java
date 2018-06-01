package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ArrangeMapper;
import com.fzu.edu.dao.ClassRoomMapper;
import com.fzu.edu.dao.Classroom_courseMapper;
import com.fzu.edu.model.Arrange;
import com.fzu.edu.model.Classroom_course;
import com.fzu.edu.service.ArrangeService;
import com.fzu.edu.service.ClassRoomCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/5/29.
 */
@Service("classRoomCourseService")
@Transactional(rollbackFor = Exception.class)
public class ClassRoomCourseServiceImpl extends ServiceImpl<Classroom_courseMapper,Classroom_course> implements ClassRoomCourseService{
}
