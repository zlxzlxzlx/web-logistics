package com.fzu.edu.service;
import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Role;

import java.util.List;


/**
 * Created by Administrator on 2018/5/26.
 */
public interface RoleService extends IService<Role> {
    void addRole(String roleName,Integer id,Integer flag,String menu,String remarks);

    List<Role> getAll();
}
