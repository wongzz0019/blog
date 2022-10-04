package com.hzz.controller;

import com.hzz.pojo.Books;
import com.hzz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/15
 */

@Controller
public class BookController {
    //controller层 调用 service层
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //查询全部书籍，并且返回到一个书籍展示页面
    @RequestMapping("/book/allBook")
    public String list(Model model){
        List<Books> list = bookService.selectAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }

    //跳转到增加书籍的页面
    @RequestMapping("/book/toAddPage")
    public String toAddPage(){
        return "addBook";
    }

    //添加书籍的请求
    //前端的表单会传来一个实体Book，注意前端一定要有name属性，不然就传不来数据
    @RequestMapping("/book/addBook")
    public String addBook(Books book){
        System.out.println("book==>"+book);
        bookService.addBook(book);
        //重定向到我们的@RequestMapping("/book/allBook")请求；
        return "redirect:/book/allBook";
    }

    //跳到修改页面
    @RequestMapping("/book/toUpdatePage/{bookID}")
    public String toUpdatePage(@PathVariable int bookID,Model model){
        Books book = bookService.selectBookById(bookID);
        model.addAttribute("selBook",book);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/book/updateBook")
    public String updateBook(Books books){
        System.out.println("books==>"+books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    //删除书籍
    @RequestMapping("/book/deleteBook")
    public String deleteBook(int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询框查询书籍
    @RequestMapping("/book/selBookName")
    public String selBookName(String bookName,Model model){

        List<Books> books = bookService.selectBookByName(bookName);
        if (books.isEmpty()){
            model.addAttribute("error","未查到");
            return "redirect:/book/allBook";
        }
        model.addAttribute("books",books);
        return "selectBook";
    }
}
