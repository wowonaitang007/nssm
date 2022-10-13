package com.ziheng.service;

import com.ziheng.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//将事务配置在业务层接口上  表明业务层所有的方法都开启了事务  同时成功同时失败
//@Transactional 还可以配置在类上 或者类的方法上
@Transactional
public interface BookService {
    /**
     * 保存
     * @param book
     * @return
     */
    public boolean save(Book book);

    /**
     * 修改
     * @param book
     * @return
     */
    public boolean update(Book book);

    /**
     * 按id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 查询全部
     * @return
     */
    public List<Book> getAll();
}
