package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.LoginAccountMapper;
import com.fzu.edu.dao.UserInfoMapper;
import com.fzu.edu.model.LoginAccount;
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
    private LoginAccountMapper loginAccountMapper;
   public  List<Map<Object, Object>> getAllUser(String username){
       Map<String ,Object> map=new HashMap<String, Object>();
       map.put("username",username);
       return userInfoMapper.getAllUser(map);
   }
    public int userNameUnique(String username){
        int result = -1;
        Userinfo userinfo =  userInfoMapper.queryObjByUsername(username);
        if(userinfo!=null){
            result = 1;
        }
        return result;
    }
    public void addUser(String username,Integer mark)throws Exception{
        Userinfo userInfo=new Userinfo();
        userInfo.setUserName(username);
        userInfo.setFlag(0);
        userInfo.setMark(mark);
        userInfo.setRegistDate(System.currentTimeMillis());
        userInfoMapper.insert(userInfo);
       LoginAccount loginAccount=new LoginAccount();
        loginAccount.setFlag(0);
        loginAccount.setUserInfoId(userInfo.getId());
        loginAccount.setUserName(username);
        String passwd="";
        passwd= MD5Util.md5Encrypt("123456");
        loginAccount.setPasswd(passwd);
        loginAccountMapper.insert(loginAccount);
    }
    //删除用户
    public   void delUser(Integer id){
        Userinfo userinfo=userInfoMapper.selectById(id);
        userinfo.setFlag(1);
        userInfoMapper.updateById(userinfo);
        LoginAccount loginAccount=loginAccountMapper.selectByUserInfoId(id);
        loginAccount.setFlag(1);
        loginAccountMapper.updateById(loginAccount);

    }
    //批量删除用户
    public void delUsers(List ids){
        userInfoMapper.delUsers(ids);
        loginAccountMapper.delUsers(ids);
    }
}
