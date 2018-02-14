package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public interface BookOperationBiz {
    public int addState(Book book);//新书上架
    public boolean delState(int sid);//某本书下架
    public boolean delStateByBook(Book book);//某书下架
    public boolean updateState(State state);//更新书本信息
    public boolean updateBook(Book book);//更新书目信息
    public boolean rentBook(int bid,int uid);//借书
    public boolean renew(int rid);//续借
    public boolean returnBook(int rid,int backstate,int reserved);//还书
}
