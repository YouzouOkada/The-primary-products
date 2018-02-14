package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Reservation;
import com.youzou.libsys.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �~�i on 2018/1/23.
 */
public class ReservationDaoImplement extends ActionDao implements ReservationDao {
    /**
     * ԤԼ�ѱ�������
     * @param reservation ԤԼ��Ϣ
     * @return ԤԼ�ɹ�true��ʧ��false
     */
    @Override
    public boolean reserve(Reservation reservation) {
        String sql="insert into reservation(bid,uid)values(?,?)";
        List<Object> objects=new ArrayList<>();
        objects.add(reservation.getBid());
        objects.add(reservation.getUid());
        return update(sql,objects);
    }

    /**
     * ȡ��ԤԼ�����鱻���ؽ���
     * @param resid ԤԼ���
     * @return ȡ���ɹ�true��ʧ��false
     */
    @Override
    public boolean cancel(int resid) {
        String sql="delete from reservation where resid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(resid);
        return update(sql,objects);
    }

    /**
     *��ѯ�û���ԤԼ��¼(ÿ��ֻ��ԤԼ1��)
     * @param uid
     * @return �û���ԤԼ��¼
     */
    @Override
    public Reservation queryByUid(int uid) {
        List<Reservation>reservations=new ArrayList<>();
        String sql="select * from reservation where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(uid);
        try {
            reservations=query(sql,objects,Reservation.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(reservations.size()>0)return reservations.get(0);
        return null;
    }

    /**
     * ������������ԤԼ��¼
     * @param bname ����
     * @return ��������ԤԼ��Ϣ����
     */
    @Override
    public List<Reservation> queryByBook(String bname) {
        List<Reservation>reservations=new ArrayList<>();
        String sql="select resid,r.bid,uid from reservation r,books b where r.bid=b.bid and bname=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bname);
        try {
            reservations=query(sql,objects,Reservation.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
