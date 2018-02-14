package com.youzou.libsys.entity;

/**
 * Created by �~�i on 2018/1/22.
 */
public class Record {
    private int rid;//��¼���
    private int uid;//�û�id
    private int sid;//�鱾id
    private int bid;//��Ŀid
    private String outtime;//���ʱ��
    private String backtime;//�黹ʱ��
    private int backstate=0;//�黹״̬
    private int renew =0;//�������

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
