package com.youzou.libsys.biz;

import com.youzou.libsys.dao.*;
import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public class BookQueryBizImplement implements BookQueryBiz {
    private BookDao bookDao=new BookDaoImplement();
    private StateDao stateDao=new StateDaoImplememt();

    /**
     * 查看所有书目
     * @return
     */
    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBook();
    }

    /**
     * 查看所有书本
     * @return
     */
    @Override
    public List<State> queryAllStates() {
        return stateDao.queryAllStates();
    }

    @Override
    public List<State> queryByBid(int bid) {
        return stateDao.queryByBid(bid);
    }

    @Override
    public State queryBookBySid(int sid) {
        return stateDao.queryBySid(sid);
    }

    /**
     * 根据书目id查
     * @param bid
     * @return
     */
    @Override
    public Book queryBookByBid(int bid) {
        return bookDao.queryBookByBid(bid);
    }

    /**
     * 根据书名查
     * @param bname
     * @return
     */
    @Override
    public List<Book> queryBookByBname(String bname) {
        return bookDao.queryBookByBname(bname);
    }

    /**
     * 根据作者查
     * @param author
     * @return
     */
    @Override
    public List<Book> queryBookByAuthor(String author) {
        return bookDao.queryBookByAuthor(author);
    }

    /**
     * 根据在馆状态查找
     * @param inlib
     * @return
     */
    @Override
    public List<Book> queryByInLib(int inlib) {
        return stateDao.queryByInLib(inlib);
    }

    /**
     * 查找损坏的书本
     * @param state
     * @return
     */
    @Override
    public List<Book> queryByState(int state) {
        return stateDao.queryByState(state);
    }
}
