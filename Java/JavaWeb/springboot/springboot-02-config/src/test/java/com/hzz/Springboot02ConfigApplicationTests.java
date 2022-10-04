package com.hzz;

import com.hzz.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Person person;//将person自动注入进来

    @Test
    void contextLoads() {
        System.out.println(person);//打印person信息
    }

}
