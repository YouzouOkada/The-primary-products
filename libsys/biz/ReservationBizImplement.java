package com.youzou.libsys.biz;

import com.youzou.libsys.dao.BookDao;
import com.youzou.libsys.dao.BookDaoImplement;
import com.youzou.libsys.dao.ReservationDao;
import com.youzou.libsys.dao.ReservationDaoImplement;
import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.Reservation;

import java.util.List;

/**
 * Created by È~Êi on 2018/1/24.
 */
public class ReservationBizImplement implements ReservationBiz {
    private ReservationDao reservationDao=new ReservationDaoImplement();

    @Override
    public boolean reserve(Reservation reservation) {
        return reservationDao.reserve(reservation);
    }

    @Override
    public boolean cancel(int resid) {
        return reservationDao.cancel(resid);
    }

    @Override
    public Reservation queryByUid(int uid) {
        return reservationDao.queryByUid(uid);
    }

    @Override
    public List<Reservation> queryByBook(String bname) {
        return reservationDao.queryByBook(bname);
    }
}
