package com.youzou.libsys.entity;

/**
 * Created by �~�i on 2018/1/22.
 */
public class User {
    private int uid;//�û�id�����ɱ�
    private String uname;//�û�����
    private String upass;//����
    private int age;//����
    private int utype=1;//�û����ͣ�0������Ա 1���û���
    private int rentnum=0;//�ѽ�������
    private int point=5;//���÷�

    public User() {
    }

    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    public User(String uname, String upass, int age) {
        this.uname = uname;
        this.upass = upass;
        this.age = age;
    }

    public User(int uid, String uname, String upass, int age, int utype, int rentnum, int point) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.age = age;
        this.utype = utype;
        this.rentnum = rentnum;
        this.point = point;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public int getRentnum() {
        return rentnum;
    }

    public void setRentnum(int rentnum) {
        this.rentnum = rentnum;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
