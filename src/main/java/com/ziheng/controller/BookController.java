package com.ziheng.controller;
//使用rest风格编写控制器

import com.ziheng.domain.Book;
import com.ziheng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javafx.scene.input.KeyCode.R;

@RestController
//表明将return的直接作为响应体响应  不需要解析
@ResponseBody
//rest风格统一映射路径
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //一般参数较多都是通过json对象传过来的  json参数在请求体中
    //将所有响应统一封装在Result中
    @PostMapping
    public Result save(@RequestBody Book book){
        boolean save = bookService.save(book);
        return new Result(save?Code.SAVE_OK:Code.SAVE_ERR, save);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean update = bookService.update(book);
        return new Result(update?Code.UPDATE_OK:Code.UPDATE_ERR, update);
    }

    //@PathVariable  表明变量来自路径
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean delete = bookService.delete(id);
        return new Result(delete?Code.DELETE_OK:Code.DELETE_ERR, delete);
    }

    //这里的对象会被转换为json对象返回给前端
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        //设置异常
        //抛出异常之后  交给统一的异常管理器处理  异常处理器根据不同的异常来进行异常处理
        //模拟业务异常，包装成自定义异常
        if(id == 1){
            throw new BusinessException(Code.BUSINESS_ERR,"请不要使用你的技术挑战我的耐性!");
        }
        //模拟系统异常，将可能出现的异常进行包装，转换成自定义异常 交给异常处理器统一处理
        try{
            int i = 1/0;
        }catch (Exception e){
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器访问超时，请重试!",e);
        }

        Book book = bookService.getById(id);
        //根据查询结果设置返回值
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(code,book,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> bookList = bookService.getAll();
        Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,bookList,msg);
    }
}
