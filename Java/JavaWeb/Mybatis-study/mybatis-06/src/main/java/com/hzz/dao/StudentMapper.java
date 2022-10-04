package com.hzz.dao;

import com.hzz.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/11/23
 */
public interface StudentMapper {

    //查询所有学生信息，以及对应的老师信息
    public List<Student> getStudent();

    public List<Student> getStudent2();

}
