package com.youzou.libsys.dao;

import com.youzou.libsys.entity.User;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public interface UserDao {
    public boolean addUser(User user);//添加用户
    public boolean updateUser(User user);//更改用户信息
    public boolean delUser(int uid);//删除用户
    public User queryUser(User user);//用户查询
    public User queryUser(int uid);//用户查询
}
