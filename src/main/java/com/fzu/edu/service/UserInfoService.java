package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Userinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
public interface UserInfoService extends IService<Userinfo> {
    List<Map<Object, Object>> getAllUser(String username);
    int userNameUnique(String username);
    Userinfo addUser(String username,Integer mark)throws Exception;
    void delUser(Integer id);
    void delUsers(List ids);
    void uploadImage(String url,Integer id);
    Userinfo login(String account, String pwd)throws Exception;
}
