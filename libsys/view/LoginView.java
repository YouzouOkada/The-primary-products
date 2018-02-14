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
 * Created by �~�i on 2018/1/24.
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
     * ��ʼ��
     */
    private void init(){
        this.setSize(330,300);//���ڴ�С
        this.setResizable(false);//���ɸı��С
        this.setLocationRelativeTo(null);//�����Ļ����
        this.setTitle("��½");//����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//�رմ���
        panel=new JPanel(new GridLayout(5,2,10,10));//�����������
        //�ؼ�
        lb_uname=new JLabel("��  ��  ����",SwingConstants.CENTER);
        lb_uname.setFont(new Font("����",0,20));
        lb_upass=new JLabel("��      �룺",SwingConstants.CENTER);
        lb_upass.setFont(new Font("����",0,20));
        tf_uname=new JTextField(8);
        tf_uname.setFont(new Font("����",0,20));
        pf_upass=new JPasswordField(8);
        pf_upass.setFont(new Font("Dialog",0,20));
        bt_login=new JButton("��  ½");
        bt_login.setFont(new Font("����",0,20));
        bt_regesiter=new JButton("ע  ��");
        bt_regesiter.setFont(new Font("����",0,20));
        bt_pass=new JButton("�޸�����");
        bt_pass.setFont(new Font("����",0,20));
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
        this.setVisible(true);//���ӻ�
    }
    private void registerListener(){
        bt_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=tf_uname.getText().trim();
                String upass=new String(pf_upass.getPassword());
                if(uname.equals("")){
                    JOptionPane.showMessageDialog(LoginView.this,"�û�������Ϊ��");
                    Utils.setLoginVoid(tf_uname,pf_upass);
                    return;
                }else if(upass.equals("")){
                    JOptionPane.showMessageDialog(LoginView.this,"���벻��Ϊ��");
                    Utils.setLoginVoid(tf_uname,pf_upass);
                    return;
                }
                User user=new User(uname,upass);
                user=userBiz.login(user);
                if(user==null){
                    JOptionPane.showMessageDialog(LoginView.this,"�û����������");
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
