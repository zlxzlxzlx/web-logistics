package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.College;
import com.fzu.edu.model.School;
import com.fzu.edu.service.CollegeService;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping(value = "/college",produces = {"application/json;charset=UTF-8"})
public class CollegeController {

    @Resource
    private CollegeService collegeService;
    @RequestMapping(value = "/showCollege", method = RequestMethod.GET)
    @ResponseBody
    public String query(@RequestParam(value = "pageNo",required = false) int pageNo,
                        @RequestParam(value = "college_code", required = false) String college_code,
                        @RequestParam(value = "colege_name", required = false) String colege_name,
                        @RequestParam(value = "pageSize",required = false) int pageSize,
                        @RequestParam(value = "school_id",required = false) Integer school_id
    ) {
        List<Map<Object, Object>> collegeList = collegeService.getAllCollege(college_code,colege_name,school_id);
        Page page = new Page(pageNo, pageSize, collegeList);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }

    //删除学院
    @RequestMapping(value = "/delCollege", method = RequestMethod.POST)
    @ResponseBody
    public String delCollege(
            @RequestParam(value = "id") Integer id
    ) {

        try {
            collegeService.delCollege(id);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //批量删除学院
    @RequestMapping(value = "/delColleges", method = RequestMethod.GET)
    @ResponseBody
    public String delColleges(
            @RequestParam(value = "ids") List ids
    ) {
        try {
            collegeService.delColleges(ids);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //编码唯一
    @RequestMapping(value = "/collegeCodeUnique", method = RequestMethod.GET)
    @ResponseBody
    public String collegeCodeUnique(
            @RequestParam(value = "parameter") String college_code
    ) {
        return JSON.toJSONString(collegeService.collegeCodeUnique(college_code), SerializerFeature.DisableCircularReferenceDetect);
    }
    //新增学院
    @RequestMapping(value = "/addCollege", method = RequestMethod.POST)
    @ResponseBody
    public String addCollege(
            @RequestParam(value = "college_code") String college_code,
            @RequestParam(value = "college_name") String college_name,
            @RequestParam(value = "college_detail",required = false) String college_detail,
            @RequestParam(value = "user_code") String user_code,
            @RequestParam(value = "school_id",required = false) String school_id
    ) {
        try {
            College college=collegeService.addCollege(college_code, college_name,college_detail,user_code,school_id);
            return JSON.toJSONString(college,SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
//查询所有学院
    @RequestMapping(value = "/getAllCollegeForSelect", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCollegeForSelect(
            @RequestParam(value = "school_id") String school_id
    ) {
        List<Map<Object, Object>> collegeList = collegeService.getAllCollegeForSelect(school_id);
        return JSON.toJSONString(collegeList, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/showAllCollege", method = RequestMethod.GET)
    @ResponseBody
    public String showAllCollege(
                        @RequestParam(value = "college_code", required = false) String college_code,
                        @RequestParam(value = "colege_name", required = false) String colege_name,
                        @RequestParam(value = "school_id",required = false) Integer school_id
    ) {
        List<Map<Object, Object>> collegeList = collegeService.getAllCollege(college_code,colege_name,school_id);
        return JSON.toJSONString(collegeList, SerializerFeature.DisableCircularReferenceDetect);
    }
}
