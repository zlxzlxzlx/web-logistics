package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.service.StudentCourseService;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
@Controller
@RequestMapping(value = "/studentCourse",produces = {"application/json;charset=UTF-8"})
public class StudentCourseControl {
    @Resource
    private StudentCourseService studentCourseService;
    @RequestMapping(value = "/addStudentCourse", method = RequestMethod.POST)
    @ResponseBody
    public String addStudentCourse(
            @RequestParam String params, HttpServletRequest request
    ) {
        try {
            studentCourseService.addStudentCourse(params);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //根据课程id获取所有学生
    @RequestMapping(value = "/getAllStudentByCourseId", method = RequestMethod.POST)
    @ResponseBody
    public String getAllStudentByCourseId(
            @RequestParam(value = "course_id",required = false) Integer course_id,
            @RequestParam(value = "user_name",required = false) String user_name,
            @RequestParam(value = "teacher_id",required = false) Integer teacher_id,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        try {
            List<Map<Object,Object>> electiveList=studentCourseService.getAllStudentByCourseId(course_id,user_name,teacher_id);
            Page page = new Page(pageNo, pageSize, electiveList);
            return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    //根据课程id获取所有学生(课堂点名名单)
    @RequestMapping(value = "/getAllStudentByCourseIdForClass", method = RequestMethod.POST)
    @ResponseBody
    public String getAllStudentByCourseIdForClass(
            @RequestParam(value = "course_id",required = false) Integer course_id,
            @RequestParam(value = "teacher_id",required = false) Integer teacher_id
    ) {
        try {
            List<Map<Object,Object>> electiveList=studentCourseService.getAllStudentByCourseIdForClass(course_id,teacher_id);
            return JSON.toJSONString(electiveList, SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/updateElectiveByClass", method = RequestMethod.POST)
    @ResponseBody
    public String updateElectiveByClass(
            @RequestParam(value = "elective_id",required = false) Integer elective_id,
            @RequestParam(value = "mark",required = false) Integer mark,
            @RequestParam(value = "value",required = false) Float value
    ) {
        try {
            studentCourseService.updateElectiveByClass(elective_id,mark,value);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
