package com.youzou.libsys.entity;

/**
 * Created by �~�i on 2018/1/22.
 */
public class Reservation {
    private int resid;//ԤԼid
    private int bid;//��Ŀid
    private int uid;//�û�id

    public Reservation() {
    }

    public Reservation(int bid, int uid) {
        this.bid = bid;
        this.uid = uid;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Reservation(int resid, int bid, int uid) {
        this.resid = resid;
        this.bid = bid;
        this.uid = uid;
    }
}
