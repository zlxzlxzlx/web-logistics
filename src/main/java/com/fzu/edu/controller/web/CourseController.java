package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Course;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.CourseService;
import com.fzu.edu.service.UserInfoService;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3.
 */
@Controller
@RequestMapping(value = "/course",produces = {"application/json;charset=UTF-8"})
public class CourseController {
    @Resource
    private CourseService courseService;

    @RequestMapping(value="/showCourse", method = RequestMethod.GET)
    @ResponseBody
    public String query(@RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "name",required = false) String name,
                        @RequestParam(value = "code",required = false) String code,
                        @RequestParam(value = "pageSize") int pageSize
    ){
        List<HashMap> courseList = courseService.getAllCourse(name,code);
        Page page = new Page(pageNo,pageSize,courseList);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    @ResponseBody
    public String addCourse(
            @RequestParam(value = "code")String code,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "class_hour")String class_hour,
            @RequestParam(value = "type")Integer type,
            @RequestParam(value = "teacher_id")Integer teacher_id,
            @RequestParam(value = "major_id")Integer major_id,
            @RequestParam(value = "school_id")Integer school_id,
            @RequestParam(value = "class_time")String class_time,
            @RequestParam(value = "start_time")String start_time,
            @RequestParam(value = "end_time")String end_time
            ){

        try{
            courseService.addCourseRow(code,name,class_hour,type,teacher_id,major_id,school_id,class_time,start_time,end_time);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    @ResponseBody
    public String updateCourse(
            @RequestParam(value = "id")Integer id,
            @RequestParam(value = "code")String code,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "class_hour")String class_hour,
            @RequestParam(value = "type")Integer type,
            @RequestParam(value = "teacher_id")Integer teacher_id,
            @RequestParam(value = "major_id")Integer major_id,
            @RequestParam(value = "school_id")Integer school_id,
            @RequestParam(value = "class_time")String class_time,
            @RequestParam(value = "start_time")String start_time,
            @RequestParam(value = "end_time")String end_time
            ){

        try{
            courseService.updateCourseRow(id,code,name,class_hour,type,teacher_id,major_id,school_id,class_time,start_time,end_time);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value="/delCourse", method = RequestMethod.POST)
    @ResponseBody
    public String delCourse(@RequestParam(value = "id") int id
    ) {
        try {
            courseService.delCourse(id);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }

    }
    @RequestMapping(value="/delCourses", method = RequestMethod.GET)
    @ResponseBody
    public String delCourses(@RequestParam(value = "ids") List ids
    ) {
        try {
            courseService.delCourses(ids);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }

    }
    @RequestMapping(value="/getAllTeacherAndMajor", method = RequestMethod.GET)
    @ResponseBody
    public List getAllTeacherAndMajor(
    ) {
        try {
            return  courseService.getAllTeacherAndMajor();
        } catch (Exception e) {
            return null;
        }

    }
}
