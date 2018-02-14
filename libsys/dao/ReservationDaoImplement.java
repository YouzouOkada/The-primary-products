package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Reservation;
import com.youzou.libsys.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/23.
 */
public class ReservationDaoImplement extends ActionDao implements ReservationDao {
    /**
     * 预约已被借光的书
     * @param reservation 预约信息
     * @return 预约成功true，失败false
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
     * 取消预约或者书被还回借书
     * @param resid 预约编号
     * @return 取消成功true，失败false
     */
    @Override
    public boolean cancel(int resid) {
        String sql="delete from reservation where resid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(resid);
        return update(sql,objects);
    }

    /**
     *查询用户的预约记录(每人只能预约1本)
     * @param uid
     * @return 用户的预约记录
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
     * 根据书名查找预约记录
     * @param bname 书名
     * @return 所查的书的预约信息集合
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
