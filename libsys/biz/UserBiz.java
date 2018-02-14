package com.youzou.libsys.biz;

import com.youzou.libsys.entity.User;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public interface UserBiz {
    public User login(User user);//登陆
    public boolean registe(User user);//注册
    public boolean updateUser(User user,String upass);//修改密码
    public boolean updateUser(User user,int point);//解冻用户
    public User queryUser(User user);//查看用户信息
    public User queryUser(int uid);//查找
    public User minPoint(int uid);//扣信用分
}
