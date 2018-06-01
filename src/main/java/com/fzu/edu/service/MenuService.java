package com.fzu.edu.service;

import com.baomidou.mybatisplus.service.IService;
import com.fzu.edu.model.Menu;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */
public interface MenuService /*extends IService<Menu> */{
    List<HashMap> getAllTree(String menu);
    List<HashMap> getAll(String name,String code);
    void addMenu(String name,String code,String parentCode,Integer id,Integer flag);
    List<HashMap> isDelete(String code);
    List<HashMap> codeUnique(String code);
}
