package com.youzou.libsys.entity;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public class Record {
    private int rid;//记录编号
    private int uid;//用户id
    private int sid;//书本id
    private int bid;//书目id
    private String outtime;//借出时间
    private String backtime;//归还时间
    private int backstate=0;//归还状态
    private int renew =0;//续借次数

    public Record(int rid, int uid, int sid, int bid, String outtime) {
        this.rid = rid;
        this.uid = uid;
        this.sid = sid;
        this.bid = bid;
        this.outtime = outtime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", sid=" + sid +
                ", bid=" + bid +
                ", outtime='" + outtime + '\'' +
                ", backtime='" + backtime + '\'' +
                ", backstate=" + backstate +
                ", renew=" + renew +
                '}';
    }

    public Record(int uid, int sid, int bid, String outtime) {

        this.uid = uid;
        this.sid = sid;
        this.bid = bid;
        this.outtime = outtime;
    }

    public Record() {

    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    public int getBackstate() {
        return backstate;
    }

    public void setBackstate(int backstate) {
        this.backstate = backstate;
    }

    public int getRenew() {
        return renew;
    }

    public void setRenew(int renew) {
        this.renew = renew;
    }
}
