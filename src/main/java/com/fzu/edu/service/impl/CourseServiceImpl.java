package com.fzu.edu.service.impl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.CourseMapper;
import com.fzu.edu.dao.ElectiveMapper;
import com.fzu.edu.model.Course;
import com.fzu.edu.model.Major;
import com.fzu.edu.model.Userinfo;
import com.fzu.edu.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3.
 */
@Service("courseService")
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl extends ServiceImpl<CourseMapper,Course> implements CourseService{
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private ElectiveMapper electiveMapper;
    public void addCourseRow(String code,String name,String class_hour,Integer type,Integer teacher_id,Integer major_id,Integer school_id,String class_time,String start_time,String end_time){
           Course course = new Course();
           course.setCode(code);
           course.setName(name);
           course.setClassHour(class_hour);
           course.setType(type);
           course.setTeacherId(teacher_id);
           course.setCollegeId(major_id);
           course.setSchoolId(school_id);
           course.setClassTime(class_time);
           course.setStartTime(start_time);
           course.setEndTime(end_time);
           course.setFlag(0);
           courseMapper.insert1(course);
    }
    public List<HashMap> getAllCourse(String name, String code){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("name",name);
        List<HashMap> list = courseMapper.getAllCourse(map);
        return list;
    }
    public void updateCourseRow(Integer id,String code,String name,String class_hour,Integer type,Integer teacher_id,Integer major_id,Integer school_id,String class_time,String start_time,String end_time){
        Course course = new Course();
        course.setId(id);
        course.setCode(code);
        course.setName(name);
        course.setClassHour(class_hour);
        course.setType(type);
        course.setTeacherId(teacher_id);
        course.setCollegeId(major_id);
        course.setSchoolId(school_id);
        course.setClassTime(class_time);
        course.setStartTime(start_time);
        course.setEndTime(end_time);
        course.setFlag(0);
        courseMapper.updateByPrimaryKeySelective(course);
    }
    public void delCourse(Integer id){
        Course course = new Course();
        course.setId(id);
        course.setFlag(1);
        courseMapper.updateByPrimaryKeySelective(course);
    }
    public void delCourses(List ids){
        courseMapper.delCourses(ids);
    }
    public List getAllTeacherAndMajor(){
        List<HashMap> list =  courseMapper.getAllTeacher();
        List<HashMap> list1 = courseMapper.getAllCollege();
        List<HashMap> list2 = courseMapper.getAllSchool();
        List list11 = new ArrayList();
        list11.add(list);
        list11.add(list1);
        list11.add(list2);
        return list11;
    }
    public List<Map<Object, Object>> getAllCourseBySchoolId(Integer school_id,Integer college,String keyWord,Integer mark){
        Map<String ,Object> map =new HashMap<String, Object>();
        map.put("school_id",school_id);
        map.put("college",college);
        map.put("keyWord",keyWord);
        map.put("mark",mark);
        return courseMapper.getAllCourseBySchoolId(map);
    }
    public  List<Map<Object, Object>> getAllCourseForStudent(Integer school_id,Integer college_id,Integer user_id,String keyWord,Integer mark){
        List list=new ArrayList();
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("school_id",school_id);
        map.put("college_id",college_id);
        map.put("keyWord",keyWord);
        map.put("mark",mark);
        List<Map<Object, Object>> list1=courseMapper.getAllCourseForStudentFromCourse(map);
        list.add(list1);
        Map<String ,Object> map1=new HashMap<String, Object>();
        map1.put("user_id",user_id);
        map1.put("keyWord",keyWord);
        map1.put("mark",mark);
        List<Map<Object, Object>> list2=electiveMapper.getAllCourseForStudentFromElective(map1);
        list.add(list2);
        return list;
    }
   public List<Map<Object, Object>> getCourseForClass(Integer user_id){
       return courseMapper.getCourseForClass(user_id);
   }
}
