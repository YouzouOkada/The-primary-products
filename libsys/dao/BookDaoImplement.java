package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/23.
 */
public class BookDaoImplement extends ActionDao implements BookDao {
    /**
     * 添加新书
     * @param book 要添加的新书
     * @return 添加成功true，失败false
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
     * 删除书
     * @param bid 要删除的书的bid
     * @return 删除成功true，失败false
     */
    @Override
    public boolean delBook(int bid) {
        String sql="delete from books where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bid);
        return update(sql,objects);
    }

    /**
     * 更新书目信息（在馆数量，借出数量，借出次数）
     * @param book 需要修改的书的信息
     * @return 修改成功true，失败false
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
     * 查询所有书清单
     * @return 所有书的集合
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
     * 查找某书是否已经存在
     * @param book
     * @return 存在返回bid，不存在返回-1
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
     * 根据bid找书
     * @param bid
     * @return 所要找的书,没找到返回空
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
     * 根据书名找书（可重名）
     * @param bname 书名
     * @return 查找的书的集合，没有返回空
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
     * 根据作者找书
     * @param author 作者名
     * @return 查找的书的集合，没有返回空
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
