package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper extends BaseMapper<Userinfo>{

    List<Map<Object, Object>> getAllUser(Map<String,  Object> params);
    Userinfo queryObjByCode(String code);
    void delUsers(List ids);
    Userinfo selectByUsername(Map map);
    List<Map<Object, Object>> getAllUerForImport(Integer school_id);
    Userinfo selectByCode(String user_code);
    Userinfo selectByUsernameForApp(String useranme);
    HashMap selectByUId(Integer id);
}