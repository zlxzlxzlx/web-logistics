package com.fzu.edu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.ParamstersMapper;
import com.fzu.edu.model.Paramsters;
import com.fzu.edu.service.ParamstersService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/12.
 */
@Service("paramstersService")
@Transactional(rollbackFor = Exception.class)
public class ParamstersServiceImpl extends ServiceImpl<ParamstersMapper,Paramsters> implements ParamstersService{
    @Resource
    private ParamstersMapper paramstersMapper;
    public Paramsters addSetParams(Map<String, String> param){
        Integer len=paramstersMapper.getAllParams().size()+1;
        Paramsters paramsters=new Paramsters();
       paramsters.setLateNum(Integer.parseInt(param.get("late_num")));
       paramsters.setLateValue(Integer.parseInt(param.get("late_value")));
        paramsters.setLate(Integer.parseInt(param.get("late")));
        paramsters.setAbsenteeismNum(Integer.parseInt(param.get("absenteeism_num")));
        paramsters.setAbsenteeismValue(Integer.parseInt(param.get("absenteeism_value")));
        paramsters.setSickNum(Integer.parseInt(param.get("sick_num")));
        paramsters.setSickValue(Integer.parseInt(param.get("sick_value")));
        paramsters.setThinkNum(Integer.parseInt(param.get("think_num")));
        paramsters.setThinkValue(Integer.parseInt(param.get("think_value")));
        paramsters.setFlag(0);
        paramsters.setName("考勤参数模版"+len);
        paramstersMapper.insert(paramsters);
         return  paramsters;
    }

    public List<Map<Object,Object>> getAllParams(){

        return paramstersMapper.getAllParams();
    }
    public   void delParams(Integer id){
        Paramsters paramsters=paramstersMapper.selectById(id);
        paramsters.setFlag(1);
        paramstersMapper.updateById(paramsters);
    }
    public Paramsters getAllParamsById(Integer params_id){
        Paramsters paramsters=paramstersMapper.selectById(params_id);
        return paramsters;
    }
}
