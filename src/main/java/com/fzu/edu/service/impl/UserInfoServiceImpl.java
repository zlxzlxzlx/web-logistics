package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.RoleMapper;
import com.fzu.edu.dao.SchoolMapper;
import com.fzu.edu.dao.UserInfoMapper;
import com.fzu.edu.model.Role;
import com.fzu.edu.model.School;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.UserInfoService;
import com.fzu.edu.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/5/2.
 */
@Service("userInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,Userinfo> implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private SchoolMapper schoolMapper;

   public  List<Map<Object, Object>> getAllUser(String username){
       Map<String ,Object> map=new HashMap<String, Object>();
       map.put("username",username);
       List<Map<Object, Object>> list =  userInfoMapper.getAllUser(map);
       for (Map map1:list){
           Integer roleId = Integer.parseInt(map1.get("mark").toString());
           Role role = roleMapper.selectById(roleId);
           map1.put("roleName",role.getName());
       }
       return list;
   }
   public int userCodeUnique(String code){
        int result = -1;
        Userinfo userinfo =  userInfoMapper.queryObjByCode(code);
        if(userinfo!=null){
            result = 1;
        }
        return result;
    }

    public Userinfo addUser(String username,Integer mark,String passwd,Integer school,Integer college,String phone,String code,Integer id)throws Exception{
        Userinfo userInfo;
        if (id!=null){
            userInfo = userInfoMapper.selectById(id);
        }else {
            userInfo = new Userinfo();
        }
        userInfo.setUserName(username);
        userInfo.setFlag(0);
        userInfo.setMark(mark);
        userInfo.setRegistDate(System.currentTimeMillis());
        userInfo.setPhone(phone);
        userInfo.setCode(code);
        String pwd="";
        pwd=MD5Util.md5Encrypt("123456");
        userInfo.setPasswd(pwd);
        userInfo.setSchool_id(school);
        userInfo.setCollege_id(college);
        if (id!=null){
            userInfoMapper.updateById(userInfo);
        }else
        {
            userInfoMapper.insert(userInfo);
        }
        Role role = roleMapper.selectById(mark);
        if (role.getName().equals("学校")){
            School school1;
            if (id!=null){
                school1 = schoolMapper.selectByAccount(code);
            } else {
                school1 = new School();
            }
            school1.setSchoolCode(code);
            school1.setSchoolName(username);
            school1.setFlag(0);
            if (id!=null){
                 schoolMapper.updateById(school1);
            }else {
                 schoolMapper.insert(school1);
            }

        }
        return userInfo;
    }
    //删除用户
    public   void delUser(Integer id){
        Userinfo userinfo=userInfoMapper.selectById(id);
        userinfo.setFlag(1);
        userInfoMapper.updateById(userinfo);

    }
    //批量删除用户
    public void delUsers(List ids){
        userInfoMapper.delUsers(ids);
    }
    //图片上传
    public void uploadImage(String url,Integer id)
    {
        Userinfo userinfo=userInfoMapper.selectById(id);
        userinfo.setImageUrl(url);
        userInfoMapper.updateById(userinfo);
    }
    public  Userinfo login(String account,String pwd,Integer mark)throws Exception{
        Map map = new HashMap();
        map.put("account",account);
        map.put("mark",mark);
        Userinfo userinfo=userInfoMapper.selectByUsername(map);
        String passwd="";
        passwd=MD5Util.md5Encrypt(pwd);
        if(userinfo!=null){
            if(passwd.equals(userinfo.getPasswd())){
                return userinfo;
            }else{
                return null;
            }
        }else{
            return null;
        }

    }
    public  List<Map<Object, Object>> getAllUerForImport(Integer school_id){

        return userInfoMapper.getAllUerForImport(school_id);
    }
}
