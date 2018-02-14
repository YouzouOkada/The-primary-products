package com.youzou.libsys.biz;

import com.youzou.libsys.dao.UserDao;
import com.youzou.libsys.dao.UserDaoImplement;
import com.youzou.libsys.entity.User;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public class UserBizImplement implements UserBiz{
    private UserDao userDao=new UserDaoImplement();
    /**
     * 用户登陆
     * @param user 用户对象
     * @return 成功返回对应用户，失败返回空
     */
    @Override
    public User login(User user) {
        return userDao.queryUser(user);
    }
    /**
     * 注册用户
     * @param user
     * @return 成功为true，失败（用户名已存在）或已存在用户为false
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
     * 用户修改密码
     * @param user 用户
     * @param upass 新密码
     * @return 修改成功true，失败false
     */
    @Override
    public boolean updateUser(User user, String upass) {
        user.setUpass(upass);
        return userDao.updateUser(user);
    }

    /**
     * 用户信用分
     * @param user 用户
     * @param point
     * @return 修改成功true，失败false
     */
    @Override
    public boolean updateUser(User user, int point) {
        user.setPoint(point);
        return userDao.updateUser(user);
    }

    /**
     * 查看用户信息
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
     * 扣信用分
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
