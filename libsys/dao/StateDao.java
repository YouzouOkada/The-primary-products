package com.youzou.libsys.dao;


import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by �~�i on 2018/1/22.
 */
public interface StateDao {
    public boolean addState(State state);//�����ϼ�
    public boolean delState(int sid);//ɾ��ĳһ��
    public boolean delStateByBid(int bid);//ɾ������ͬһ����������
    public boolean updateState(State state);//�޸��鱾״̬
    public List<State> queryAllStates();//�鿴�����鱾
    public State queryBySid(int sid);//����sid����
    public List<State> queryByBid(int bid);//����bid����
    public List<Book> queryByInLib(int inlib);//���ڿ�״̬����
    public List<Book> queryByState(int state);//�����𻵵��飨����Ա��
}
