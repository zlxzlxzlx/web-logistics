package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Paramsters;

import java.util.List;
import java.util.Map;

public interface ParamstersMapper extends BaseMapper<Paramsters>{
      List<Map<Object,Object>> getAllParams();
}