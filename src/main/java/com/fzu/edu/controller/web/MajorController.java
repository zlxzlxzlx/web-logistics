package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.College;
import com.fzu.edu.model.Major;
import com.fzu.edu.service.CollegeService;
import com.fzu.edu.service.MajorService;
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
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping(value = "/major",produces = {"application/json;charset=UTF-8"})
public class MajorController {
    @Resource
    private MajorService majorService;
    @RequestMapping(value = "/showMajor", method = RequestMethod.GET)
    @ResponseBody
    public String query(@RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "major_code", required = false) String major_code,
                        @RequestParam(value = "major_name", required = false) String major_name,
                        @RequestParam(value = "pageSize") int pageSize
    ) {
        List<Map<Object, Object>> collegeList = majorService.getAllMajor(major_code,major_name);
        Page page = new Page(pageNo, pageSize, collegeList);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }

    //删除学院
    @RequestMapping(value = "/delMajor", method = RequestMethod.POST)
    @ResponseBody
    public String delMajor(
            @RequestParam(value = "id") Integer id
    ) {

        try {
            majorService.delMajor(id);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //批量删除学院
    @RequestMapping(value = "/delMajors", method = RequestMethod.GET)
    @ResponseBody
    public String delMajors(
            @RequestParam(value = "ids") List ids
    ) {
        try {
            majorService.delMajors(ids);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
   //编码唯一
    @RequestMapping(value = "/majorCodeUnique", method = RequestMethod.GET)
    @ResponseBody
    public String majorCodeUnique(
            @RequestParam(value = "parameter") String major_code
    ) {
        return JSON.toJSONString(majorService.majorCodeUnique(major_code), SerializerFeature.DisableCircularReferenceDetect);
    }

    //新增学院
    @RequestMapping(value = "/addMajor", method = RequestMethod.POST)
    @ResponseBody
    public String addMajor(
            @RequestParam(value = "major_code") String major_code,
            @RequestParam(value = "major_name") String major_name,
            @RequestParam(value = "major_detail") String major_detail
    ) {
        try {
            Major major=majorService.addMajor(major_code, major_name,major_detail);
            return JSON.toJSONString(major,SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }
}
