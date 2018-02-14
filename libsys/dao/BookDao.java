package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public interface BookDao {
    public boolean addBook(Book book);//上架新书
    public boolean delBook(int bid);//删除书目
    public boolean updateBook(Book book);//修改书的信息
    public List<Book> queryAllBook();//查询所有书目
    public int queryBook(Book book);//查找是否存在
    public Book queryBookByBid(int bid);//根据id查找
    public List<Book> queryBookByBname(String bname);//按书名查找
    public List<Book> queryBookByAuthor(String author);//按作者查找
}
