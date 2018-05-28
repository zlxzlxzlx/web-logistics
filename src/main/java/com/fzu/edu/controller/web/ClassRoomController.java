package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Classroom;
import com.fzu.edu.service.ClassRoomService;
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
 * Created by zlx on 2018/5/28.
 */
@Controller
@RequestMapping(value = "/classroom",produces = {"application/json;charset=UTF-8"})
public class ClassRoomController {

    @Resource
    private ClassRoomService classRoomService;
    @RequestMapping(value = "/addClassRoom", method = RequestMethod.POST)
    @ResponseBody
    public String query(
                        @RequestParam(value = "class_name") String class_name,
                        @RequestParam(value = "school_id") Integer school_id
    ) {
        Classroom classRoom=classRoomService.addClassRoom(class_name,school_id);
        try {
            return JSON.toJSONString(classRoom, SerializerFeature.DisableCircularReferenceDetect);
        }catch(Exception e){
            return  JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/getAllClassRoom", method = RequestMethod.POST)
    @ResponseBody
    public String getAllClassRoom(
                        @RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "pageSize") int pageSize,
                        @RequestParam(value = "school_id",required = false) Integer school_id,
                        @RequestParam(value = "keyWord",required = false) String  keyWord
    ) {
      List<Map<Object,Object>> list=classRoomService.getAllClassRoom(school_id,keyWord);
        Page page = new Page(pageNo, pageSize, list);
        try {
            return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
        }catch(Exception e){
            return  JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/delRow", method = RequestMethod.GET)
    @ResponseBody
    public String delRow(
                        @RequestParam(value = "id",required = false) Integer  id
    ) {

        try {
            classRoomService.delRow(id);
            return JSON.toJSONString(1);
        }catch(Exception e){
            return  JSON.toJSONString(0);
        }
    }
}
