package com.youzou.libsys.biz;

import com.youzou.libsys.entity.User;

/**
 * Created by �~�i on 2018/1/24.
 */
public interface UserBiz {
    public User login(User user);//��½
    public boolean registe(User user);//ע��
    public boolean updateUser(User user,String upass);//�޸�����
    public boolean updateUser(User user,int point);//�ⶳ�û�
    public User queryUser(User user);//�鿴�û���Ϣ
    public User queryUser(int uid);//����
    public User minPoint(int uid);//�����÷�
}
