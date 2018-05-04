package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.MajorMapper;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.School;
import com.fzu.edu.service.MajorService;
import com.fzu.edu.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2018/5/2.
 */
@Service("majorService")
@Transactional(rollbackFor = Exception.class)
public class MajorServiceImpl extends ServiceImpl<MajorMapper,Major> implements MajorService{


}
