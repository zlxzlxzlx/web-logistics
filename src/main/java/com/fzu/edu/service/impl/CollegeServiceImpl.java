package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.CollegeMapper;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.model.College;
import com.fzu.edu.model.School;
import com.fzu.edu.service.CollegeService;
import com.fzu.edu.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2018/5/2.
 */
@Service("collegeService")
@Transactional(rollbackFor = Exception.class)
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper,College> implements CollegeService{


}
