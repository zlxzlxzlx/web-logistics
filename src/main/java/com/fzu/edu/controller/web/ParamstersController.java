package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Paramsters;
import com.fzu.edu.service.ParamstersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zlx on 2018/5/12.
 * 设置考勤参数
 */
@Controller
@RequestMapping(value = "/params",produces = {"application/json;charset=UTF-8"})
public class ParamstersController {

    @Resource
    private ParamstersService paramstersService;
    @RequestMapping(value = "/addSetParams", method = RequestMethod.POST)
    @ResponseBody
    public String addSetParams(@RequestParam Map<String, String> param
    ) {
        try {

           Paramsters paramsters=paramstersService.addSetParams(param);
            return JSON.toJSONString(paramsters, SerializerFeature.DisableCircularReferenceDetect);

        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/getAllParams", method = RequestMethod.GET)
    @ResponseBody
    public String getAllParams() {
        try {

          List<Map<Object,Object>> paramsters=paramstersService.getAllParams();
            return JSON.toJSONString(paramsters, SerializerFeature.DisableCircularReferenceDetect);

        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/delParams", method = RequestMethod.POST)
    @ResponseBody
    public String delParams(@RequestParam (value="id") Integer id) {
        try {

               paramstersService.delParams(id);
            return JSON.toJSONString(1);

        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/getAllParamsById", method = RequestMethod.GET)
    @ResponseBody
    public String getAllParamsById(@RequestParam (value="params_id") Integer params_id) {
        try {

               Paramsters paramsters=paramstersService.getAllParamsById(params_id);
            return JSON.toJSONString(paramsters, SerializerFeature.DisableCircularReferenceDetect);

        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
