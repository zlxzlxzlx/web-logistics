package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.School;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.SchoolService;
import com.fzu.edu.service.UserInfoService;
import com.fzu.edu.utils.MemoryData;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping(value = "/school",produces = {"application/json;charset=UTF-8"})
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    @RequestMapping(value = "/showSchool", method = RequestMethod.GET)
    @ResponseBody
    public String query(@RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "school_code", required = false) String school_code,
                        @RequestParam(value = "school_name", required = false) String school_name,
                        @RequestParam(value = "pageSize") int pageSize
    ) {
        List<Map<Object, Object>> schoolList = schoolService.getAllSchool(school_code,school_name);
        Page page = new Page(pageNo, pageSize, schoolList);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }
   //删除学校
    @RequestMapping(value = "/delSchool", method = RequestMethod.POST)
    @ResponseBody
    public String delSchool(
            @RequestParam(value = "id") Integer id
    ) {

        try {
            schoolService.delSchool(id);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //批量删除学校
    @RequestMapping(value = "/delSchools", method = RequestMethod.GET)
    @ResponseBody
    public String delSchools(
            @RequestParam(value = "ids") List ids
    ) {
        try {
            schoolService.delSchools(ids);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //编码唯一
    @RequestMapping(value = "/schoolCodeUnique", method = RequestMethod.GET)
    @ResponseBody
    public String schoolCodeUnique(
            @RequestParam(value = "parameter") String school_code
    ) {
        return JSON.toJSONString(schoolService.schoolCodeUnique(school_code), SerializerFeature.DisableCircularReferenceDetect);
    }
    //新增学校
    @RequestMapping(value = "/addSchool", method = RequestMethod.POST)
    @ResponseBody
    public String addSchool(
            @RequestParam(value = "school_code") String school_code,
            @RequestParam(value = "school_name") String school_name,
            @RequestParam(value = "school_detail") String school_detail
    ) {
        try {
            School school=schoolService.addSchool(school_code, school_name,school_detail);
            return JSON.toJSONString(school,SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
