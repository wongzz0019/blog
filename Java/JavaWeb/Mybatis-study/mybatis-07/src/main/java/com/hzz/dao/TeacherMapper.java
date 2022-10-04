package com.hzz.dao;

import com.hzz.pojo.Student;
import com.hzz.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/11/23
 */
public interface TeacherMapper {

    //获取老师
    //List<Teacher> getTeacher();

    //获取指定老师下的所有学生及老师的信息
    Teacher getTeacher(@Param("tid") int id);

    Teacher getTeacher2(@Param("tid") int id);
}
