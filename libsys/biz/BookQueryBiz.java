package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public interface BookQueryBiz {
    public List<Book> queryAllBooks();//查看所有书目
    public List<State> queryAllStates();//查看所有书本
    public List<State> queryByBid(int bid);//查看指定书
    public State queryBookBySid(int sid);//根据sid查
    public Book queryBookByBid(int bid);//根据bid查
    public List<Book> queryBookByBname(String bname);//根据书名查
    public List<Book> queryBookByAuthor(String author);//根据作者查
    public List<Book> queryByInLib(int inlib);//根据在库状态查询
    public List<Book> queryByState(int state);//查找损坏的书
}
