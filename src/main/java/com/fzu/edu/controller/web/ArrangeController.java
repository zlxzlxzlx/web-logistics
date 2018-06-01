package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.service.ArrangeService;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/29.
 */
@Controller
@RequestMapping(value = "/arrange",produces = {"application/json;charset=UTF-8"})
public class ArrangeController {
    @Resource
    private ArrangeService arrangeService;
    @RequestMapping(value = "/teachingManage", method = RequestMethod.POST)
    @ResponseBody
    public String teachingManage(@RequestParam(value = "class_id",required = false) Integer class_id,
                        @RequestParam(value = "course_id", required = false) Integer course_id,
                        @RequestParam(value = "start_week", required = false) Integer start_week,
                        @RequestParam(value = "end_week", required = false) Integer end_week,
                        @RequestParam(value = "week", required = false) String  week,
                        @RequestParam(value = "classTimeStart", required = false) String  classTimeStart,
                        @RequestParam(value = "classTimeEnd", required = false) String  classTimeEnd,
                        @RequestParam(value = "user_id", required = false) Integer  user_id
    ) {
        try{
            arrangeService.teachingManage(class_id,course_id,start_week,end_week,week,classTimeStart,classTimeEnd,user_id);
            return  JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
    @RequestMapping(value = "/getAllArrange", method = RequestMethod.POST)
    @ResponseBody
    public String getAllArrange(@RequestParam(value = "user_id",required = false) Integer user_id,
                        @RequestParam(value = "course_id", required = false) Integer course_id
    ) {
        try{
            List<Map<Object,Object>> list=arrangeService.getAllArrange(user_id,course_id);
            return  JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
    //老师获取班级（所教的课程）
    @RequestMapping(value = "/getCourseForClass", method = RequestMethod.GET)
    @ResponseBody
    public String getCourseForClass(
            @RequestParam(value = "user_id") Integer user_id
    ) {
        List<Map<Object, Object>> courseList = arrangeService.getCourseForClass(user_id);
        return JSON.toJSONString(courseList, SerializerFeature.DisableCircularReferenceDetect);
    }

}
