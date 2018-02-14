package com.youzou.libsys.view;

import com.youzou.libsys.biz.UserBiz;
import com.youzou.libsys.biz.UserBizImplement;
import com.youzou.libsys.entity.User;
import com.youzou.libsys.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 葉蔵 on 2018/1/29.
 */
public class PassView extends JFrame {
    private JPanel panel=null;
    private JLabel lb_uname=null;
    private JLabel lb_old =null;
    private JLabel lb_upass=null;
    private JLabel lb_ensure=null;
    private JTextField tf_uname=null;
    private JPasswordField pf_old =null;
    private JPasswordField pf_upass=null;
    private JPasswordField pf_ensure=null;
    private JButton bt_regesiter=null;
    private JButton bt_cancel=null;
    private UserBiz userBiz=new UserBizImplement();

    public PassView(){
        init();
        registerListener();
    }

    /**
     * 初始化
     */
    private void init(){
        this.setSize(390,330);//窗口大小
        this.setResizable(false);//不可改变大小
        this.setLocationRelativeTo(null);//相对屏幕居中
        this.setTitle("修改密码");//标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口
        panel=new JPanel(new GridLayout(5,2,10,6));
        //控件
        lb_uname=new JLabel("用  户  名  ：",SwingConstants.CENTER);
        lb_uname.setFont(new Font("仿宋",0,20));
        lb_old =new JLabel("原  密   码 ：", SwingConstants.CENTER);
        lb_old.setFont(new Font("仿宋",0,20));
        lb_upass=new JLabel(" 新 密 码 ：",SwingConstants.CENTER);
        lb_upass.setFont(new Font("仿宋",0,20));
        lb_ensure=new JLabel("确 认 密 码 ：",SwingConstants.CENTER);
        lb_ensure.setFont(new Font("仿宋",0,20));
        tf_uname=new JTextField(8);
        tf_uname.setFont(new Font("仿宋",0,20));
        pf_old =new JPasswordField(8);
        pf_old.setFont(new Font("Dialog",0,20));
        pf_upass=new JPasswordField(8);
        pf_upass.setFont(new Font("Dialog",0,20));
        pf_ensure=new JPasswordField(8);
        pf_ensure.setFont(new Font("Dialog",0,20));
        bt_regesiter=new JButton("修  改");
        bt_regesiter.setFont(new Font("仿宋",0,20));
        bt_cancel=new JButton("取  消");
        bt_cancel.setFont(new Font("仿宋",0,20));
        panel.add(lb_uname);
        panel.add(tf_uname);
        panel.add(lb_old);
        panel.add(pf_old);
        panel.add(lb_upass);
        panel.add(pf_upass);
        panel.add(lb_ensure);
        panel.add(pf_ensure);
        panel.add(bt_regesiter);
        panel.add(bt_cancel);
        this.getContentPane().add(panel);
        this.getRootPane().setDefaultButton(bt_regesiter);
        this.setVisible(true);//可视化
    }
    private void registerListener(){
        bt_regesiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=tf_uname.getText().trim();
                if(uname.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"请输入用户名");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String oldpass=new String(pf_old.getPassword());
                if(oldpass.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"请输入原密码");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                User user=new User(uname,oldpass);
                user=userBiz.queryUser(user);
                if(user==null){
                    JOptionPane.showMessageDialog(PassView.this,"用户信息不存在");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String upass=new String(pf_upass.getPassword());
                if(upass.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"新密码不能为空");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String ensure=new String(pf_ensure.getPassword());
                if(!upass.equals(ensure)){
                    JOptionPane.showMessageDialog(PassView.this,"两次输入密码不一致，请重新输入");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                boolean flag=userBiz.updateUser(user,upass);
                if(!flag){
                    JOptionPane.showMessageDialog(PassView.this,"修改失败，请再试一次");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                JOptionPane.showMessageDialog(PassView.this,"修改成功");
                PassView.this.dispose();
                new LoginView();
            }
        });
        bt_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassView.this.dispose();
                new LoginView();
            }
        });
    }
}
