package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Reservation;

import java.util.List;

/**
 * Created by �~�i on 2018/1/22.
 */
public interface ReservationDao {
    public boolean reserve(Reservation reservation);//ԤԼ
    public boolean cancel(int resid);//ȡ��ԤԼ/ɾ����¼
    public Reservation queryByUid(int uid);//���û�����ԤԼ
    public List<Reservation> queryByBook(String bname);//����������

}
