package com.youzou.libsys.entity;

/**
 * Created by �~�i on 2018/1/22.
 */
public class State {
    private int sid;//�鱾id
    private int bid;//��Ŀid
    private int inlib=0;//�ڿ�״̬
    private int state;//�鱾״̬

    public State(int bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "State{" +
                "sid=" + sid +
                ", bid=" + bid +
                ", inlib=" + inlib +
                ", state=" + state +
                '}';
    }

    public State(int bid, int inlib, int state) {
        this.bid = bid;
        this.inlib = inlib;
        this.state = state;
    }

    public State(int sid, int bid, int inlib, int state) {

        this.sid = sid;
        this.bid = bid;
        this.inlib = inlib;
        this.state = state;
    }

    public State() {

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

    public int getInlib() {
        return inlib;
    }

    public void setInlib(int inlib) {
        this.inlib = inlib;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
