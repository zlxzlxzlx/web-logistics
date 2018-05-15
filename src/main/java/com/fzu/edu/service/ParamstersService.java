package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Paramsters;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/12.
 */
public interface ParamstersService extends IService<Paramsters>{
    Paramsters addSetParams(Map<String, String> param);
    List<Map<Object,Object>> getAllParams();
    void delParams(Integer id);
    Paramsters getAllParamsById(Integer params_id);
}
