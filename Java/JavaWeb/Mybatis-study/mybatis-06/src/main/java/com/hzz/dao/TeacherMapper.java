package com.hzz.dao;

import com.hzz.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Bosco
 * @date 2021/11/23
 */
public interface TeacherMapper {

    @Select("select * from teacher where id =#{tid}")
    Teacher getTeacher(@Param("tid") int id);

}
