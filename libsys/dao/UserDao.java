package com.youzou.libsys.dao;

import com.youzou.libsys.entity.User;

/**
 * Created by �~�i on 2018/1/22.
 */
public interface UserDao {
    public boolean addUser(User user);//����û�
    public boolean updateUser(User user);//�����û���Ϣ
    public boolean delUser(int uid);//ɾ���û�
    public User queryUser(User user);//�û���ѯ
    public User queryUser(int uid);//�û���ѯ
}
