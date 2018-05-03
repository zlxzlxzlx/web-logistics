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
public class CourseController {
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(
            @RequestParam(value = "code")String code,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "class_hour")String class_hour,
            @RequestParam(value = "type")Integer type,
            @RequestParam(value = "teacher_name")String teacher_name,
            @RequestParam(value = "class_time")String class_time,
            @RequestParam(value = "start_time")String start_time,
            @RequestParam(value = "end_time")String end_time
            ){

        try{
           // accidentResultService.addRow(name,score);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
