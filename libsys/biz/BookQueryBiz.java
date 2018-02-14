package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by �~�i on 2018/1/24.
 */
public interface BookQueryBiz {
    public List<Book> queryAllBooks();//�鿴������Ŀ
    public List<State> queryAllStates();//�鿴�����鱾
    public List<State> queryByBid(int bid);//�鿴ָ����
    public State queryBookBySid(int sid);//����sid��
    public Book queryBookByBid(int bid);//����bid��
    public List<Book> queryBookByBname(String bname);//����������
    public List<Book> queryBookByAuthor(String author);//�������߲�
    public List<Book> queryByInLib(int inlib);//�����ڿ�״̬��ѯ
    public List<Book> queryByState(int state);//�����𻵵���
}
