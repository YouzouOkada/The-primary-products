package com.youzou.libsys.dao;

import com.youzou.libsys.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �~�i on 2018/1/23.
 */
public class UserDaoImplement extends ActionDao implements UserDao {
    /**
     * ������û�
     * @param user ���û�����
     * @return ��ӳɹ�true��ʧ��false
     */
    @Override
    public boolean addUser(User user) {
        String sql="insert into users(uname,upass,age) values(?,?,?)";
        List<Object> objects=new ArrayList<>();
        objects.add(user.getUname());
        objects.add(user.getUpass());
        objects.add(user.getAge());
        return update(sql,objects);
    }

    /**
     * �����û���Ϣ����ǰ��������������÷֣�
     * @param user ��Ҫ�޸ĵ��û�����
     * @return �޸ĳɹ�true��ʧ��false
     */
    @Override
    public boolean updateUser(User user) {
        String sql="update users set upass=?,rentnum=?,point=? where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(user.getUpass());
        objects.add(user.getRentnum());
        objects.add(user.getPoint());
        objects.add(user.getUid());
        return update(sql,objects);
    }

    /**
     * ɾ���û���Ϣ
     * @param uid ��ѯ�����uid
     * @return ɾ���ɹ�true��ʧ��false
     */
    @Override
    public boolean delUser(int uid) {
        String sql="delete from users where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(uid);
        return update(sql,objects);
    }

    /**
     * ��ѯ�û���Ϣ
     * @param user ��Ҫ��ѯ���û�
     * @return �鵽��User���󣬲�����Ϊ��
     */
    @Override
    public User queryUser(User user) {
        List<User> users=new ArrayList<>();
        String sql="select * from users where uname=? and upass=?";
        List<Object> objects=new ArrayList<>();
        objects.add(user.getUname());
        objects.add(user.getUpass());
        try {
            users=query(sql,objects,User.class);
        }  catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(users.size()>0){
            return  users.get(0);
        }
        return null;
    }

    @Override
    public User queryUser(int uid) {
        List<User> users=new ArrayList<>();
        String sql="select * from users where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(uid);
        try {
            users=query(sql,objects,User.class);
        }  catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(users.size()>0){
            return  users.get(0);
        }
        return null;
    }
}
