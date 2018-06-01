package com.fzu.edu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.Student_courseMapper;
import com.fzu.edu.model.Student_course;
import com.fzu.edu.service.StudentCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
@Service("studentCourseService")
@Transactional(rollbackFor = Exception.class)
public class StudentCourseServiceImpl extends ServiceImpl<Student_courseMapper,Student_course> implements StudentCourseService{

    @Resource
    private Student_courseMapper student_courseMapper;
    public   void addStudentCourse(String params){

        JSONObject jsonObject=JSONObject.parseObject(params);
        Integer teacher_id=Integer.parseInt(jsonObject.getString("teacher_id"));
        Integer course_id=Integer.parseInt(jsonObject.getString("course_id"));
        JSONArray user_id=jsonObject.getJSONArray("user_id");
        for(int i=0;i<user_id.size();i++){
            Map<String ,Object> map=new HashMap<String, Object>();
            map.put("teacher_id",teacher_id);
            map.put("course_id",course_id);
            map.put("user_id",Integer.parseInt(user_id.getString(i)));
            List<Map<Object,Object>> list=student_courseMapper.selectByCTU(map);
            if(list.size()<=0){
                Student_course student_course=new Student_course();
                student_course.setCourseId(course_id);
                student_course.setTeacherId(teacher_id);
                student_course.setFlag(0);
                student_course.setUserId(Integer.parseInt(user_id.getString(i)));
                student_course.setLate(0);
                student_course.setSick_leave(0);
                student_course.setThink_leave(0);
                student_course.setAbsenteeism(0);
                student_course.setOrdinary_grade(0);
                student_courseMapper.insert(student_course);
            }

        }

    }
    public  List<Map<Object,Object>>  getAllStudentByCourseId(Integer course_id,String user_name,Integer teacher_id){
            Map<String ,Object> map=new HashMap<String, Object>();
            map.put("course_id",course_id);
            map.put("user_name",user_name);
            map.put("teacher_id",teacher_id);
            return student_courseMapper.getAllStudentByCourseId(map);
    }
    public    List<Map<Object,Object>> getAllStudentByCourseIdForClass(Integer course_id,Integer teacher_id){
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("course_id",course_id);
        map.put("teacher_id",teacher_id);
        return student_courseMapper.getAllStudentByCourseIdForClass(map);
    }

    public void updateElectiveByClass(Integer elective_id,Integer mark,Float value){
        Student_course student_course=student_courseMapper.selectById(elective_id);
        if(mark==1){
            student_course.setAbsenteeism(student_course.getAbsenteeism()+1);

            //旷课
        }
        if(mark==2){
            student_course.setLate(student_course.getLate()+1);
            //迟到
        }
        if(mark==3){
            student_course.setSick_leave(student_course.getSick_leave()+1);
            //病假
        }
        if(mark==4){
            student_course.setThink_leave(student_course.getThink_leave()+1);
            //事假
        }
        student_course.setOrdinary_grade(student_course.getOrdinary_grade()-value);
        student_courseMapper.updateById(student_course);
    }

}
