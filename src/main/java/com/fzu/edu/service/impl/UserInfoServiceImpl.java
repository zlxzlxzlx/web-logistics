package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.UserInfoMapper;
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
   public  List<Map<Object, Object>> getAllUser(String username){
       Map<String ,Object> map=new HashMap<String, Object>();
       map.put("username",username);
       return userInfoMapper.getAllUser(map);
   }public int userNameUnique(String username){
        int result = -1;
        Userinfo userinfo =  userInfoMapper.queryObjByUsername(username);
        if(userinfo!=null){
            result = 1;
        }
        return result;
    }

    public Userinfo addUser(String username,Integer mark,String passwd,Integer school,Integer college)throws Exception{
        Userinfo userInfo=new Userinfo();
        userInfo.setUserName(username);
        userInfo.setFlag(0);
        userInfo.setMark(mark);
        userInfo.setRegistDate(System.currentTimeMillis());
        String pwd="";
        pwd=MD5Util.md5Encrypt(passwd);
        userInfo.setPasswd(pwd);
        userInfo.setSchool_id(school);
        userInfo.setCollege_id(college);
        userInfoMapper.insert(userInfo);
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
    public  Userinfo login(String account,String pwd)throws Exception{
        Userinfo userinfo=userInfoMapper.selectByUsername(account);
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
}
