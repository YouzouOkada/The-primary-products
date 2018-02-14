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
public class RegisterView extends JFrame{
    private JPanel panel=null;
    private JLabel lb_uname=null;
    private JLabel lb_age=null;
    private JLabel lb_upass=null;
    private JLabel lb_ensure=null;
    private JTextField tf_uname=null;
    private JTextField tf_age=null;
    private JPasswordField pf_upass=null;
    private JPasswordField pf_ensure=null;
    private JButton bt_regesiter=null;
    private JButton bt_cancel=null;
    private UserBiz userBiz=new UserBizImplement();

    public RegisterView(){
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
        this.setTitle("注册");//标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口
        panel=new JPanel(new GridLayout(5,2,10,6));
        //控件
        lb_uname=new JLabel("用  户  名  ：",SwingConstants.CENTER);
        lb_uname.setFont(new Font("仿宋",0,20));
        lb_age=new JLabel("  年   龄   ：", SwingConstants.CENTER);
        lb_age.setFont(new Font("仿宋",0,20));
        lb_upass=new JLabel("注 册 密 码 ：",SwingConstants.CENTER);
        lb_upass.setFont(new Font("仿宋",0,20));
        lb_ensure=new JLabel("确 认 密 码 ：",SwingConstants.CENTER);
        lb_ensure.setFont(new Font("仿宋",0,20));
        tf_uname=new JTextField(8);
        tf_uname.setFont(new Font("仿宋",0,20));
        tf_age=new JTextField(8);
        tf_age.setFont(new Font("仿宋",0,20));
        pf_upass=new JPasswordField(8);
        pf_upass.setFont(new Font("Dialog",0,20));
        pf_ensure=new JPasswordField(8);
        pf_ensure.setFont(new Font("Dialog",0,20));
        bt_regesiter=new JButton("注  册");
        bt_regesiter.setFont(new Font("仿宋",0,20));
        bt_cancel=new JButton("取  消");
        bt_cancel.setFont(new Font("仿宋",0,20));
        panel.add(lb_uname);
        panel.add(tf_uname);
        panel.add(lb_age);
        panel.add(tf_age);
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
                    JOptionPane.showMessageDialog(RegisterView.this,"用户名不能为空");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                int age;
                try{
                    age = Integer.valueOf(tf_age.getText().trim());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(RegisterView.this,"请输入正确的年龄");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                if(age<0||age>100){
                    JOptionPane.showMessageDialog(RegisterView.this,"请输入正确的年龄");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                String upass=new String(pf_upass.getPassword());
                if(upass.equals("")){
                    JOptionPane.showMessageDialog(RegisterView.this,"密码不能为空");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                String ensure=new String(pf_ensure.getPassword());
                if(!upass.equals(ensure)){
                    JOptionPane.showMessageDialog(RegisterView.this,"两次输入密码不一致，请重新输入");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                User user=new User(uname,upass,age);
                boolean flag=userBiz.registe(user);
                if(!flag){
                    JOptionPane.showMessageDialog(RegisterView.this,"注册失败,信息已被注册");
                    Utils.setRegisterVoid(tf_uname,tf_age,pf_upass,pf_ensure);
                    return;
                }
                JOptionPane.showMessageDialog(RegisterView.this,"注册成功");
                RegisterView.this.dispose();
                new LoginView();
            }
        });
        bt_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterView.this.dispose();
                new LoginView();
            }
        });
    }
}
