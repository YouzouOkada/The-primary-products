package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.*;

/**
 * Created by �~�i on 2018/1/23.
 */
public class StateDaoImplememt extends ActionDao implements StateDao{
    /**
     * �������
     * @param state ����״̬
     * @return ��ӳɹ�true��ʧ��false
     */
    @Override
    public boolean addState(State state) {
        String sql="insert into state(bid,state) values(?,?)";
        List<Object> objects=new ArrayList<>();
        objects.add(state.getBid());
        objects.add(state.getState());
        return update(sql,objects);
    }

    /**
     * ɾ��ĳ����
     * @param sid
     * @return ɾ���ɹ�true��ʧ��false
     */
    @Override
    public boolean delState(int sid) {
        String sql="delete from state where sid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(sid);
        return update(sql,objects);
    }

    /**
     * ɾ��ͬһ��������
     * @param bid
     * @return
     */
    @Override
    public boolean delStateByBid(int bid) {
        String sql="delete from state where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bid);
        return update(sql,objects);
    }

    /**
     * �����鱾��Ϣ
     * @param state
     * @return �޸ĳɹ�true��ʧ��false
     */
    @Override
    public boolean updateState(State state) {
        String sql="update state set bid=?,inlib=?,state=? where sid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(state.getBid());
        objects.add(state.getInlib());
        objects.add(state.getState());
        objects.add(state.getSid());
        return update(sql,objects);
    }

    @Override
    public List<State> queryAllStates() {
        String sql="select * from state ";
        List<State> states=new ArrayList<>();
        try {
            states=query(sql,null,State.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return states;
    }

    /**
     * ����sid�����鱾
     * @param sid
     * @return
     */
    @Override
    public State queryBySid(int sid) {
        String sql="select * from state where sid=?";
        List<State> states=new ArrayList<>();
        List<Object>objects=new ArrayList<>();
        objects.add(sid);
        try {
            states=query(sql,objects,State.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(states.size()>0){
            return states.get(0);
        }
        return null;
    }



    /**
     * ������Ŀid����
     * @param bid
     * @return ÿ����������Ϣ
     */
    @Override
    public List<State> queryByBid(int bid) {
        String sql="select * from state where bid=?";
        List<State> states=new ArrayList<>();
        List<Object>objects=new ArrayList<>();
        objects.add(bid);
        try {
            states=query(sql,objects,State.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return states;
    }

    /**
     * �����ڹݻ��ڹݵ���
     * @param inlib �ڹ�״̬
     * @return �ڹݻ��ڹݵ���ļ���
     */
    @Override
    public List<Book> queryByInLib(int inlib) {
        String sql="select distinct b.bid,bname,author,btype,rank,innum,outnum,times from books b,state s where b.bid=s.bid and s.inlib=?";
        List<Book> books=new ArrayList<>();
        List<Object>objects=new ArrayList<>();
        objects.add(inlib);
        try {
            books=query(sql,objects,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * �����𻵵���
     * @param state ״̬��Ϣ
     * @return �𻵵���ļ���
     */
    @Override
    public List<Book> queryByState(int state) {
        List<Book> books=new ArrayList<>();
        String sql="select b.bid,bname,author,btype,rank,innum,outum,times from books where b.bid=s.bid and s.state=1";
        try {
            books=query(sql,null,Book.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return books;
    }
}
