package com.youzou.libsys.entity;

/**
 * Created by �~�i on 2018/1/22.
 */
public class Book {
    private int bid;//��Ŀid
    private String bname;//����
    private String author;//����
    private String btype;//����
    private int rank;//���Ƽ�
    private int innum;//�ڿ�����
    private int outnum=0;//�������
    private int times=0;//�������

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", btype='" + btype + '\'' +
                ", rank=" + rank +
                ", innum=" + innum +
                ", outnum=" + outnum +
                ", times=" + times +
                '}';
    }

    public Book(String bname, String author, String btype, int rank, int innum) {
        this.bname = bname;
        this.author = author;
        this.btype = btype;
        this.rank = rank;
        this.innum = innum;
    }

    public Book(String bname, String author, String btype, int rank, int innum, int outnum, int times) {
        this.bname = bname;
        this.author = author;
        this.btype = btype;
        this.rank = rank;
        this.innum = innum;
        this.outnum = outnum;
        this.times = times;
    }

    public Book(int bid, String bname, String author, String btype, int rank, int innum, int outnum, int times) {
        this.bid = bid;
        this.bname = bname;
        this.author = author;
        this.btype = btype;
        this.rank = rank;
        this.innum = innum;
        this.outnum = outnum;
        this.times = times;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getInnum() {
        return innum;
    }

    public void setInnum(int innum) {
        this.innum = innum;
    }

    public int getOutnum() {
        return outnum;
    }

    public void setOutnum(int outnum) {
        this.outnum = outnum;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
