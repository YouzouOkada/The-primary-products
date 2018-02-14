package com.youzou.libsys.biz;

import com.youzou.libsys.dao.*;
import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by �~�i on 2018/1/24.
 */
public class BookQueryBizImplement implements BookQueryBiz {
    private BookDao bookDao=new BookDaoImplement();
    private StateDao stateDao=new StateDaoImplememt();

    /**
     * �鿴������Ŀ
     * @return
     */
    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBook();
    }

    /**
     * �鿴�����鱾
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
     * ������Ŀid��
     * @param bid
     * @return
     */
    @Override
    public Book queryBookByBid(int bid) {
        return bookDao.queryBookByBid(bid);
    }

    /**
     * ����������
     * @param bname
     * @return
     */
    @Override
    public List<Book> queryBookByBname(String bname) {
        return bookDao.queryBookByBname(bname);
    }

    /**
     * �������߲�
     * @param author
     * @return
     */
    @Override
    public List<Book> queryBookByAuthor(String author) {
        return bookDao.queryBookByAuthor(author);
    }

    /**
     * �����ڹ�״̬����
     * @param inlib
     * @return
     */
    @Override
    public List<Book> queryByInLib(int inlib) {
        return stateDao.queryByInLib(inlib);
    }

    /**
     * �����𻵵��鱾
     * @param state
     * @return
     */
    @Override
    public List<Book> queryByState(int state) {
        return stateDao.queryByState(state);
    }
}
