package com.fzu.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fzu.edu.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    int deleteByPrimaryKey(Integer id);

    //int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    List<Map<Object, Object>> getAllStudentForImport(Integer school_id);

    List<Map<Object, Object>> selectAllStudent(Map map);
    List<Map<Object, Object>> codeUnique(Map map);
    void delStudents(List ids);
    Student selectByCodeForApp(String useranme);
}