package com.youzou.libsys.dao;

import com.youzou.libsys.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/23.
 */
public class UserDaoImplement extends ActionDao implements UserDao {
    /**
     * 添加新用户
     * @param user 新用户对象
     * @return 添加成功true，失败false
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
     * 更新用户信息（当前借的书数量、信用分）
     * @param user 需要修改的用户对象
     * @return 修改成功true，失败false
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
     * 删除用户信息
     * @param uid 查询对象的uid
     * @return 删除成功true，失败false
     */
    @Override
    public boolean delUser(int uid) {
        String sql="delete from users where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(uid);
        return update(sql,objects);
    }

    /**
     * 查询用户信息
     * @param user 需要查询的用户
     * @return 查到的User对象，不存在为空
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
