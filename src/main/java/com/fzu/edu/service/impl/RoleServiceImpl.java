package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.RoleMapper;
import com.fzu.edu.model.Role;
import com.fzu.edu.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/5/26.
 */
@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService{
    @Resource
    private RoleMapper roleMapper;
    public void addRole(String roleName,Integer id,Integer flag,String menu,String remarks){
        Role role = new Role();
        role.setName(roleName);
        role.setPowerId(menu);
        role.setRemarks(remarks);
        if(flag==1){
            role.setFlag(1);
        }else{
            role.setFlag(0);
        }
        if (id!=null){
            role.setId(id);
            roleMapper.updateById(role);
        }else {
            roleMapper.insert1(role);
        }

    }
    public List<Role> getAll(){
        List<Role> list = roleMapper.getAll();
        return list;
    }
}
