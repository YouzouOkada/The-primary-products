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
 * Created by 葉蔵 on 2018/1/24.
 */
public class LoginView extends JFrame{
    private JPanel panel=null;
    private JLabel lb_uname=null;
    private JLabel lb_upass=null;
    private JTextField tf_uname=null;
    private JPasswordField pf_upass=null;
    private JButton bt_login=null;
    private JButton bt_regesiter=null;
    private JButton bt_pass=null;
    private UserBiz userBiz=new UserBizImplement();

    public LoginView(){
        init();
        registerListener();
    }

    /**
     * 初始化
     */
    private void init(){
        this.setSize(330,300);//窗口大小
        this.setResizable(false);//不可改变大小
        this.setLocationRelativeTo(null);//相对屏幕居中
        this.setTitle("登陆");//标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口
        panel=new JPanel(new GridLayout(5,2,10,10));//五行两列面板
        //控件
        lb_uname=new JLabel("用  户  名：",SwingConstants.CENTER);
        lb_uname.setFont(new Font("仿宋",0,20));
        lb_upass=new JLabel("密      码：",SwingConstants.CENTER);
        lb_upass.setFont(new Font("仿宋",0,20));
        tf_uname=new JTextField(8);
        tf_uname.setFont(new Font("仿宋",0,20));
        pf_upass=new JPasswordField(8);
        pf_upass.setFont(new Font("Dialog",0,20));
        bt_login=new JButton("登  陆");
        bt_login.setFont(new Font("仿宋",0,20));
        bt_regesiter=new JButton("注  册");
        bt_regesiter.setFont(new Font("仿宋",0,20));
        bt_pass=new JButton("修改密码");
        bt_pass.setFont(new Font("仿宋",0,20));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(lb_uname);
        panel.add(tf_uname);
        panel.add(lb_upass);
        panel.add(pf_upass);
        panel.add(bt_login);
        panel.add(bt_regesiter);
        panel.add(new JLabel());
        panel.add(bt_pass);
        this.getContentPane().add(panel);
        this.setVisible(true);//可视化
    }
    private void registerListener(){
        bt_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=tf_uname.getText().trim();
                String upass=new String(pf_upass.getPassword());
                if(uname.equals("")){
                    JOptionPane.showMessageDialog(LoginView.this,"用户名不能为空");
                    Utils.setLoginVoid(tf_uname,pf_upass);
                    return;
                }else if(upass.equals("")){
                    JOptionPane.showMessageDialog(LoginView.this,"密码不能为空");
                    Utils.setLoginVoid(tf_uname,pf_upass);
                    return;
                }
                User user=new User(uname,upass);
                user=userBiz.login(user);
                if(user==null){
                    JOptionPane.showMessageDialog(LoginView.this,"用户名密码错误");
                    Utils.setLoginVoid(tf_uname,pf_upass);
                    return;
                }
                LoginView.this.dispose();
                if(user.getUtype()==1)new UserView(user);
                else new AdminView(user);
            }
        });
        bt_regesiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView.this.dispose();
                new RegisterView();
            }
        });
        bt_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView.this.dispose();
                new PassView();
            }
        });
    }
}
