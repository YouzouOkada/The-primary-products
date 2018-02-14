package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.State;

import java.util.List;

/**
 * Created by �~�i on 2018/1/24.
 */
public interface BookOperationBiz {
    public int addState(Book book);//�����ϼ�
    public boolean delState(int sid);//ĳ�����¼�
    public boolean delStateByBook(Book book);//ĳ���¼�
    public boolean updateState(State state);//�����鱾��Ϣ
    public boolean updateBook(Book book);//������Ŀ��Ϣ
    public boolean rentBook(int bid,int uid);//����
    public boolean renew(int rid);//����
    public boolean returnBook(int rid,int backstate,int reserved);//����
}
