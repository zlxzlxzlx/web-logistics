package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface MenuMapper extends BaseMapper<Menu>{
    int deleteByPrimaryKey(Integer id);

    void insert1(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<HashMap> selectAll(Map map);

    List<HashMap> isDelete(String code);
    List<HashMap> codeUnique(String code);
}