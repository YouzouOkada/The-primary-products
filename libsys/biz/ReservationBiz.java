package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Reservation;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public interface ReservationBiz {
    public boolean reserve(Reservation reservation);//预约
    public boolean cancel(int resid);//取消预约
    public Reservation queryByUid(int uid);//按用户查找预约
    public List<Reservation> queryByBook(String bname);//按书名查找
}
