package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.LoginAccount;

import java.util.List;

public interface LoginAccountMapper extends BaseMapper<LoginAccount>{
       LoginAccount selectByUserInfoId(Integer id);
    void delUsers(List ids);
}