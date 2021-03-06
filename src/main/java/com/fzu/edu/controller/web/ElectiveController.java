package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Elective;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.ElectiveService;
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
 * Created by Administrator on 2018/5/8.
 */
@Controller
@RequestMapping(value = "/elective",produces = {"application/json;charset=UTF-8"})
public class ElectiveController {

    @Resource
    private ElectiveService electiveService;
    @RequestMapping(value = "/addElectiveForStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addElectiveForStudent(
            @RequestParam(value = "course_id",required = false) String course_id,
            @RequestParam(value = "user_id",required = false) Integer user_id
    ) {
        try {

            return JSON.toJSONString(electiveService.addElectiveForStudent(course_id,user_id), SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/getAllElectiveByUserId", method = RequestMethod.POST)
    @ResponseBody
    public String getAllElectiveByUserId(
            @RequestParam(value = "user_id",required = false) Integer user_id,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "keyWord",required = false) String keyWord,
            @RequestParam(value = "mark",required = false) int mark
    ) {
        try {
            List<Map<Object,Object>> electiveList=electiveService.getAllElectiveByUserId(user_id,keyWord,mark);
            Page page = new Page(pageNo, pageSize, electiveList);
            return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    //根据课程id获取所有学生
    @RequestMapping(value = "/getAllStudentByCourseId", method = RequestMethod.POST)
    @ResponseBody
    public String getAllStudentByCourseId(
            @RequestParam(value = "course_id",required = false) String course_id,
            @RequestParam(value = "user_name",required = false) String user_name,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        try {
            List<Map<Object,Object>> electiveList=electiveService.getAllStudentByCourseId(course_id,user_name);
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
            @RequestParam(value = "course_id",required = false) String course_id
    ) {
        try {
            List<Map<Object,Object>> electiveList=electiveService.getAllStudentByCourseIdForClass(course_id);
            return JSON.toJSONString(electiveList, SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/delSelectElective", method = RequestMethod.GET)
    @ResponseBody
    public String delSelectElective(
            @RequestParam(value = "id",required = false) Integer id
    ) {
        try {
            electiveService.delSelectElective(id);
            return JSON.toJSONString(1);
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
            electiveService.updateElectiveByClass(elective_id,mark,value);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }

    @RequestMapping(value = "/updateFinalGrade", method = RequestMethod.POST)
    @ResponseBody
    public String updateFinalGrade(
            @RequestParam String params, HttpServletRequest request
    ) {
        try {
            electiveService.updateFinalGrade(params);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }

}
