package com.youzou.libsys.biz;

import com.youzou.libsys.dao.UserDao;
import com.youzou.libsys.dao.UserDaoImplement;
import com.youzou.libsys.entity.User;

/**
 * Created by �~�i on 2018/1/24.
 */
public class UserBizImplement implements UserBiz{
    private UserDao userDao=new UserDaoImplement();
    /**
     * �û���½
     * @param user �û�����
     * @return �ɹ����ض�Ӧ�û���ʧ�ܷ��ؿ�
     */
    @Override
    public User login(User user) {
        return userDao.queryUser(user);
    }
    /**
     * ע���û�
     * @param user
     * @return �ɹ�Ϊtrue��ʧ�ܣ��û����Ѵ��ڣ����Ѵ����û�Ϊfalse
     */
    @Override
    public boolean registe(User user) {
        if(userDao.queryUser(user)!=null){
            return false;
        }else{
            return userDao.addUser(user);
        }
    }

    /**
     * �û��޸�����
     * @param user �û�
     * @param upass ������
     * @return �޸ĳɹ�true��ʧ��false
     */
    @Override
    public boolean updateUser(User user, String upass) {
        user.setUpass(upass);
        return userDao.updateUser(user);
    }

    /**
     * �û����÷�
     * @param user �û�
     * @param point
     * @return �޸ĳɹ�true��ʧ��false
     */
    @Override
    public boolean updateUser(User user, int point) {
        user.setPoint(point);
        return userDao.updateUser(user);
    }

    /**
     * �鿴�û���Ϣ
     * @param user
     * @return
     */
    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public User queryUser(int uid) {
        return userDao.queryUser(uid);
    }

    /**
     * �����÷�
     * @param uid
     * @return
     */
    @Override
    public User minPoint(int uid) {
        User user=userDao.queryUser(uid);
        user.setPoint(user.getPoint()-1);
        return user;
    }
}
