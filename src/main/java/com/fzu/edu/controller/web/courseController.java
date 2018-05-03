package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/3.
 */
@Controller
@RequestMapping(value = "/course",produces = {"application/json;charset=UTF-8"})
public class courseController {
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "score")Float score){

        try{
           // accidentResultService.addRow(name,score);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
