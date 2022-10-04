package com.hzz.service;

import com.hzz.mapper.BookMapper;
import com.hzz.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/15
 */

@Service
public class BookServiceImpl implements BookService{
    //service层调Dao层：组合Dao，通俗点就是把dao层放进来
    @Autowired
    private BookMapper bookMapper;

//    public void setBookMapper(BookMapper bookMapper) {
//        this.bookMapper = bookMapper;
//    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books selectBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    public List<Books> selectAllBook() {
        return bookMapper.selectAllBook();
    }

    public List<Books> selectBookByName(String name) {
        return bookMapper.selectBookByName(name);
    }
}
