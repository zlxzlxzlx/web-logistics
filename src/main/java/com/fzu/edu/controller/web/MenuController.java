package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Menu;
import com.fzu.edu.service.MenuService;
import com.fzu.edu.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */
@Controller
@RequestMapping(value = "/menu",produces = {"application/json;charset=UTF-8"})
public class MenuController {
    @Resource
    private MenuService menuService;
    @RequestMapping(value="/getAllTree", method = RequestMethod.GET)
    @ResponseBody
    public String getAllTree(
            @RequestParam(value = "menu",required = false) String menu
    ){
        List<HashMap> list = menuService.getAllTree(menu);
        return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String getAll(
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) String code
    ){
        List<HashMap> list = menuService.getAll(name,code);
        Page page = new Page(pageNo,pageSize,list);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value="/addMenu", method = RequestMethod.POST)
    @ResponseBody
    public String addMenu(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "parentCode",required = false) String parentCode,
            @RequestParam(value = "id",required = false) Integer id,
            @RequestParam(value = "flag",required = false) Integer flag
    ){
        try {
            menuService.addMenu(name,code,parentCode,id,flag);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
    @RequestMapping(value="/isDelete", method = RequestMethod.GET)
    @ResponseBody
    public String isDelete(
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ){
        try {
             List<HashMap> list = menuService.isDelete(code);
            Page page = new Page(pageNo,pageSize,list);
            return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }

    @RequestMapping(value="/codeUnique", method = RequestMethod.POST)
    @ResponseBody
    public String delete(
            @RequestParam(value = "code",required = false) String code

    ){
        try {
            List<HashMap> list = menuService.codeUnique(code);
           if (list.size()>0){
               return JSON.toJSONString(1);
           }else {
               return JSON.toJSONString(0);
           }

        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
}
