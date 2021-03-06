package com.fzu.edu.dao;

import com.fzu.edu.model.purviewType;
import org.springframework.stereotype.Repository;

@Repository
public interface purviewTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(purviewType record);

    int insertSelective(purviewType record);

    purviewType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(purviewType record);

    int updateByPrimaryKey(purviewType record);
}