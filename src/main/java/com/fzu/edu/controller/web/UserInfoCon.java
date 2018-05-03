package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UserInfoCon {

    @Resource
    private UserInfoService userInfoService;
    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    @ResponseBody
    public String query(@RequestParam(value = "pageNo") int pageNo,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "pageSize") int pageSize
    ) {
        List<Map<Object, Object>> userList = userInfoService.getAllUser(username);
        Page page = new Page(pageNo, pageSize, userList);
        return JSON.toJSONString(page, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/userNameUnique", method = RequestMethod.GET)
    @ResponseBody
    public String userNameUnique(
            @RequestParam(value = "parameter") String username
    ) {
        return JSON.toJSONString(userInfoService.userNameUnique(username), SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "mark") Integer mark
    ) {
        try {
            userInfoService.addUser(username, mark);
            return JSON.toJSONString(1);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
    }

    //删除用户
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    @ResponseBody
    public String delUser(
            @RequestParam(value = "id") Integer id
    ) {

        try {
            userInfoService.delUser(id);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    //批量删除用户
    @RequestMapping(value = "/delUsers", method = RequestMethod.GET)
    @ResponseBody
    public String delUsers(
            @RequestParam(value = "ids") List ids
    ) {
        try {
            userInfoService.delUsers(ids);
            return JSON.toJSONString(1);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
}
