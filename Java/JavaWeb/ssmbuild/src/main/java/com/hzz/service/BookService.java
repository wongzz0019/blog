package com.hzz.service;
import com.hzz.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//业务层是和dao层对应的，本质上没啥区别，只存在细微的差别
public interface BookService {
    //增加一本书
    public int addBook(Books books);

    //删除一本书
    public int deleteBookById(int id);

    //更新一本书
    public int updateBook(Books books);

    //查询一本书
    public Books selectBookById(int id);

    //查询全部的书
    public List<Books> selectAllBook();

    //通过书名查询
    List<Books> selectBookByName(String name);
}
