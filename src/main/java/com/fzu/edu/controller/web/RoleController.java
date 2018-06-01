package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Role;
import com.fzu.edu.service.RoleService;
import com.fzu.edu.service.UserInfoService;
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
 * Created by Administrator on 2018/5/26.
 */
@Controller
@RequestMapping(value = "/role",produces = {"application/json;charset=UTF-8"})
public class RoleController {
    @Resource
    private RoleService roleService;
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(
            @RequestParam(value = "roleName",required = false) String roleName,
            @RequestParam(value = "id",required = false) Integer id,
            @RequestParam(value = "flag",required = false) Integer flag,
            @RequestParam(value = "menu",required = false) String menu,
            @RequestParam(value = "remarks",required = false) String remarks
    ) {
        try {
            roleService.addRole(roleName,id,flag,menu,remarks);
            return JSON.toJSONString(1);
        }catch (    Exception e){
            return JSON.toJSONString(0);
        }

    }
        @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        try {
            List<Role> list = roleService.getAll();
            return JSON.toJSONString(list);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }

    }
}
