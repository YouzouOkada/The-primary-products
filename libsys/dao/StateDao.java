package com.youzou.libsys.dao;


import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public interface StateDao {
    public boolean addState(State state);//新书上架
    public boolean delState(int sid);//删除某一本
    public boolean delStateByBid(int bid);//删除所有同一个书名的书
    public boolean updateState(State state);//修改书本状态
    public List<State> queryAllStates();//查看所有书本
    public State queryBySid(int sid);//根据sid查找
    public List<State> queryByBid(int bid);//根据bid查找
    public List<Book> queryByInLib(int inlib);//按在库状态查找
    public List<Book> queryByState(int state);//查找损坏的书（管理员）
}
