package com.hzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.hzz.mapper")这也可以扫描dao
public class Springboot05MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05MybatisApplication.class, args);
    }

}
