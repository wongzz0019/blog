package com.hzz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hzz.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author Bosco
 * @date 2021/12/10
 */

//@Controller
@RestController //用这注解的类中的所有方法都不走视图，只会返回JSON字符串
public class JacksonController {

    //输出一个对象的JSON字符串
    @RequestMapping("/j1")
    //@ResponseBody //不会走视图解析器，会直接返回一个字符串，和@Conreoller搭配使用
    public String json1() throws JsonProcessingException {

        //jackson ,ObjectMapper .要导入jackson的依赖才能用
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User(1, "黄瓜", 10);

        String str = mapper.writeValueAsString(user);

        return str;
    }


    //集合输出JSON字符串
    @RequestMapping("j2")
    public String json2() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<User>();

        User user1 = new User(1, "黄瓜1", 10);
        User user2 = new User(2, "黄瓜2", 12);
        User user3 = new User(3, "黄瓜3", 14);
        User user4 = new User(4, "黄瓜4", 15);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String str = mapper.writeValueAsString(userList);
        return str; //return new ObjectMapper().writeValueAsString(userList);
    }


    //输出时间的JSON字符串
    @RequestMapping("j3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        //自定义日期的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        // ObjectMapper,时间解析后的默认格式为：Timestamp，时间戳
        return mapper.writeValueAsString(sdf.format(date));


        //不使用时间戳的方式
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
//        return mapper.writeValueAsString(date);
    }
}
