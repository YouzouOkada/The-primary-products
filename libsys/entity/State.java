package com.youzou.libsys.entity;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public class State {
    private int sid;//书本id
    private int bid;//书目id
    private int inlib=0;//在库状态
    private int state;//书本状态

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
