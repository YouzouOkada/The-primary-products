package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Reservation;

import java.util.List;

/**
 * Created by �~�i on 2018/1/24.
 */
public interface ReservationBiz {
    public boolean reserve(Reservation reservation);//ԤԼ
    public boolean cancel(int resid);//ȡ��ԤԼ
    public Reservation queryByUid(int uid);//���û�����ԤԼ
    public List<Reservation> queryByBook(String bname);//����������
}
