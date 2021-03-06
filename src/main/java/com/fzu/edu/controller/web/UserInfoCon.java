package com.fzu.edu.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzu.edu.model.Userinfo;
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
import java.util.HashMap;
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
 @RequestMapping(value = "/getAllUerForImport", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUerForImport(
                        @RequestParam(value = "school_id", required = false) Integer school_id
    ) {
     try {
         List<Map<Object, Object>> userList = userInfoService.getAllUerForImport(school_id);
         return JSON.toJSONString(userList, SerializerFeature.DisableCircularReferenceDetect);
     }catch(Exception e){
         return  JSON.toJSONString(0);
     }
    }

    @RequestMapping(value = "/userCodeUnique", method = RequestMethod.GET)
    @ResponseBody
    public String userCodeUnique(
            @RequestParam(value = "parameter") String code
    ) {
        return JSON.toJSONString(userInfoService.userCodeUnique(code), SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(
            @RequestParam(value = "username",required = false) String  username,
            @RequestParam(value = "mark",required = false) Integer mark,
            @RequestParam(value = "passwd",required = false) String passwd,
            @RequestParam(value = "school",required = false) Integer school,
            @RequestParam(value = "college",required = false) Integer college,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "id",required = false) Integer id
    ) {
        try {
            Userinfo userinfo=userInfoService.addUser(username, mark,passwd,school,college,phone,code,id);
            return JSON.toJSONString(userinfo,SerializerFeature.DisableCircularReferenceDetect);
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

    //图片上传
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public String  upload(HttpSession session, @RequestParam MultipartFile file , @RequestParam(value = "id") Integer id)
            throws IllegalStateException ,IOException {

        if(!file.isEmpty())
        {
            String location=session.getServletContext().getRealPath("logistics/upload/userImage");
            String originalFileName = file.getOriginalFilename();//获得当前文件的名称
            int i = originalFileName.lastIndexOf('.');
            String suffix = originalFileName.substring(i);
            String fileName = System.currentTimeMillis()+suffix;
            String url=location+"/"+fileName;
            String url1="upload/userImage"+"/"+fileName;
            file.transferTo(new File(url));
            userInfoService.uploadImage(url1,id);
        }
        return null;
    }
    //登录检查
    @RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
    @ResponseBody
    public String loginCheck(@RequestParam(value = "account",required = false) String account,
                             HttpServletRequest request) {
        String user = null;
        user = account + "";
        String sessionID = request.getRequestedSessionId();
        if (!MemoryData.getSessionIDMap().containsKey(user)) {
            return JSON.toJSONString(0);//不存在，首次登陆
        }else {
            return JSON.toJSONString(1);//非首次登陆，并且不是本次登陆
        }
    }
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam(value = "account") String account,
                        @RequestParam(value = "pwd") String pwd,
                        @RequestParam(value = "mark",required = false) Integer mark,
                        HttpServletRequest request) {
        try {
            Userinfo userinfo = userInfoService.login(account, pwd,mark) ;
            if (userinfo == null) {
                return JSON.toJSONString(-1);
            } else {
                request.getSession().setAttribute("loginAccount", userinfo);
                String sessionID = request.getSession().getId();//request.getRequestedSessionId();
                String user = userinfo.getCode() + "";
                if (!MemoryData.getSessionIDMap().containsKey(user)) { //不存在，首次登陆，放入Map
                        MemoryData.getSessionIDMap().put(user, sessionID);
                } else{
                    MemoryData.getSessionIDMap().remove(user);
                    MemoryData.getSessionIDMap().put(user, sessionID);
                }
                return JSON.toJSONString(userinfo);
            }
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    @ResponseBody
    public Object loginout(HttpServletRequest request) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("loginAccount");
        if(userinfo!=null){
            request.getSession().setAttribute("loginAccount", null);
            String user = userinfo.getCode()+"";
            MemoryData.getSessionIDMap().remove(user);
        }
        return JSON.toJSONString(1);
    }
    //登录
    @RequestMapping(value = "/applogin", method = RequestMethod.POST)
    @ResponseBody
    public String applogin(@RequestParam(value = "useranme") String useranme,
                        @RequestParam(value = "passwd") String passwd
                       ) {
        try {
            Userinfo userinfo = userInfoService.applogin(useranme, passwd) ;
            return JSON.toJSONString(userinfo);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }

    @RequestMapping(value = "/getOneRow", method = RequestMethod.POST)
    @ResponseBody
    public String getOneRow(@RequestParam(value = "id") Integer id
    ) {
        try {
            HashMap userinfo = userInfoService.getOneRow(id) ;
            return JSON.toJSONString(userinfo);
        } catch (Exception e) {
            return JSON.toJSONString(0);
        }
    }
}
