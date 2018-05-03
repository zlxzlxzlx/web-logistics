package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper extends BaseMapper<Userinfo>{

    List<Map<Object, Object>> getAllUser(Map<String,  Object> params);
    Userinfo queryObjByUsername(String username);
    void delUsers(List ids);
}