package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Student;
import com.fzu.edu.service.StudentCourseService;
import com.fzu.edu.service.StudentService;
import com.fzu.edu.utils.Page;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.jar.Pack200;

/**
 * Created by Administrator on 2018/6/5.
 */
@Controller
@RequestMapping(value = "/student",produces = {"application/json;charset=UTF-8"})
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/getAllStudentForImport", method = RequestMethod.GET)
    @ResponseBody
    public String getAllStudentForImport(
            @RequestParam(value = "school_id", required = false) Integer school_id
    ) {
        try {
            List<Map<Object, Object>> studentList = studentService.getAllStudentForImport(school_id);
            return JSON.toJSONString(studentList, SerializerFeature.DisableCircularReferenceDetect);
        }catch(Exception e){
            return  JSON.toJSONString(0);
        }
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "school_id", required = false) Integer school_id,
            @RequestParam(value = "college_id", required = false) Integer major_id,
            @RequestParam(value = "sex", required = false) Integer sex,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "flag", required = false) Integer flag
    ) {
        try {
             studentService.addStudent(code,name,phone,email,school_id,major_id,sex,id,flag);
            return JSON.toJSONString(1, SerializerFeature.DisableCircularReferenceDetect);
        }catch(Exception e){
            return  JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/showStudent", method = RequestMethod.GET)
    @ResponseBody
    public String showStudent(
                        @RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "code", required = false) String code,
                        @RequestParam(value = "pageSize") int pageSize,
                        @RequestParam(value = "college_id") Integer college_id,
                        @RequestParam(value = "school_id") Integer school_id
    ) {
        List<Map<Object, Object>> list = studentService.getAllStudent(name,code,college_id,school_id);
        Page page = new Page(pageNo, pageSize, list);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value = "/delStudents", method = RequestMethod.GET)
    @ResponseBody
    public String delStudents(
                        @RequestParam(value = "ids") List ids
    ) {
        try {
            studentService.delStudents(ids);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
    @RequestMapping(value = "/codeUnique", method = RequestMethod.GET)
    @ResponseBody
    public String codeUnique(
            @RequestParam(value = "parameter") String code
    ) {
        try {
            List<Map<Object, Object>> list = studentService.codeUnique(code);
            if (list.size()==0){
                return JSON.toJSONString(-1);
            }else {
                return JSON.toJSONString(0);
            }
        }catch (Exception e){
            return  JSON.toJSONString(0);
        }
    }
    //登录
    @RequestMapping(value = "/applogin", method = RequestMethod.POST)
    @ResponseBody
    public String applogin(@RequestParam(value = "useranme") String useranme,
                           @RequestParam(value = "passwd") String passwd
    ) {
        try {
            Student student = studentService.applogin(useranme, passwd) ;

            return JSON.toJSONString(student);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
}
