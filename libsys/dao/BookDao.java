package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by �~�i on 2018/1/22.
 */
public interface BookDao {
    public boolean addBook(Book book);//�ϼ�����
    public boolean delBook(int bid);//ɾ����Ŀ
    public boolean updateBook(Book book);//�޸������Ϣ
    public List<Book> queryAllBook();//��ѯ������Ŀ
    public int queryBook(Book book);//�����Ƿ����
    public Book queryBookByBid(int bid);//����id����
    public List<Book> queryBookByBname(String bname);//����������
    public List<Book> queryBookByAuthor(String author);//�����߲���
}
