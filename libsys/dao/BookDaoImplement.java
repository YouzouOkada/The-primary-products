package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �~�i on 2018/1/23.
 */
public class BookDaoImplement extends ActionDao implements BookDao {
    /**
     * �������
     * @param book Ҫ��ӵ�����
     * @return ��ӳɹ�true��ʧ��false
     */
    @Override
    public boolean addBook(Book book) {
        String sql="insert into books(bname,author,btype,rank,innum) values(?,?,?,?,?)";
        List<Object> objects=new ArrayList<>();
        objects.add(book.getBname());
        objects.add(book.getAuthor());
        objects.add(book.getBtype());
        objects.add(book.getRank());
        objects.add(book.getInnum());
        return update(sql,objects);
    }

    /**
     * ɾ����
     * @param bid Ҫɾ�������bid
     * @return ɾ���ɹ�true��ʧ��false
     */
    @Override
    public boolean delBook(int bid) {
        String sql="delete from books where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bid);
        return update(sql,objects);
    }

    /**
     * ������Ŀ��Ϣ���ڹ�������������������������
     * @param book ��Ҫ�޸ĵ������Ϣ
     * @return �޸ĳɹ�true��ʧ��false
     */
    @Override
    public boolean updateBook(Book book) {
        String sql="update books set innum=?,outnum=?,times=? where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(book.getInnum());
        objects.add(book.getOutnum());
        objects.add(book.getTimes());
        objects.add(book.getBid());
        return update(sql,objects);
    }

    /**
     * ��ѯ�������嵥
     * @return ������ļ���
     */
    @Override
    public List<Book> queryAllBook() {
        List<Book> books=new ArrayList<>();
        String sql="select * from books";
        try {
            books=query(sql,null,Book.class);
        }  catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * ����ĳ���Ƿ��Ѿ�����
     * @param book
     * @return ���ڷ���bid�������ڷ���-1
     */
    @Override
    public int queryBook(Book book) {
        List<Book> books=new ArrayList<>();
        String sql="select bid from books where bname=? and author=? and btype=? and rank=?";
        List<Object> objects=new ArrayList<>();
        objects.add(book.getBname());
        objects.add(book.getAuthor());
        objects.add(book.getBtype());
        objects.add(book.getRank());
        try {
            books=query(sql,objects,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(books.size()>0){
            return books.get(0).getBid();
        }
        return -1;
    }

    /**
     * ����bid����
     * @param bid
     * @return ��Ҫ�ҵ���,û�ҵ����ؿ�
     */
    @Override
    public Book queryBookByBid(int bid) {
        List<Book> books=new ArrayList<>();
        String sql="select * from books where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bid);
        try {
            books=query(sql,objects,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(books.size()>0){
            return books.get(0);
        }
        return null;
    }

    /**
     * �����������飨��������
     * @param bname ����
     * @return ���ҵ���ļ��ϣ�û�з��ؿ�
     */
    @Override
    public List<Book> queryBookByBname(String bname) {
        List<Book> books=new ArrayList<>();
        String sql="select * from books where bname=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bname);
        try {
            books=query(sql,objects,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(books.size()>0){
            return books;
        }
        return null;
    }

    /**
     * ������������
     * @param author ������
     * @return ���ҵ���ļ��ϣ�û�з��ؿ�
     */
    @Override
    public List<Book> queryBookByAuthor(String author) {
        List<Book> books=new ArrayList<>();
        String sql="select * from books where author=?";
        List<Object> objects=new ArrayList<>();
        objects.add(author);
        try {
            books=query(sql,objects,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(books.size()>0){
            return books;
        }
        return null;
    }
}
