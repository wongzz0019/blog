package com.hzz.mapper;

import com.hzz.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/15
 */
public interface BookMapper<list> {

    //增加一本书
    public int addBook(Books books);

    //删除一本书
    public int deleteBookById(@Param("id")int id);

    //更新一本书
    public int updateBook(Books books);

    //查询一本书
    public Books selectBookById(@Param("id")int id);

    //查询全部的书
    public List<Books> selectAllBook();

    //通过书名查询
    List<Books> selectBookByName(@Param("name") String name);
}
