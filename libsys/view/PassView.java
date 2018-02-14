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
 * Created by �~�i on 2018/1/29.
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
     * ��ʼ��
     */
    private void init(){
        this.setSize(390,330);//���ڴ�С
        this.setResizable(false);//���ɸı��С
        this.setLocationRelativeTo(null);//�����Ļ����
        this.setTitle("�޸�����");//����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//�رմ���
        panel=new JPanel(new GridLayout(5,2,10,6));
        //�ؼ�
        lb_uname=new JLabel("��  ��  ��  ��",SwingConstants.CENTER);
        lb_uname.setFont(new Font("����",0,20));
        lb_old =new JLabel("ԭ  ��   �� ��", SwingConstants.CENTER);
        lb_old.setFont(new Font("����",0,20));
        lb_upass=new JLabel(" �� �� �� ��",SwingConstants.CENTER);
        lb_upass.setFont(new Font("����",0,20));
        lb_ensure=new JLabel("ȷ �� �� �� ��",SwingConstants.CENTER);
        lb_ensure.setFont(new Font("����",0,20));
        tf_uname=new JTextField(8);
        tf_uname.setFont(new Font("����",0,20));
        pf_old =new JPasswordField(8);
        pf_old.setFont(new Font("Dialog",0,20));
        pf_upass=new JPasswordField(8);
        pf_upass.setFont(new Font("Dialog",0,20));
        pf_ensure=new JPasswordField(8);
        pf_ensure.setFont(new Font("Dialog",0,20));
        bt_regesiter=new JButton("��  ��");
        bt_regesiter.setFont(new Font("����",0,20));
        bt_cancel=new JButton("ȡ  ��");
        bt_cancel.setFont(new Font("����",0,20));
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
        this.setVisible(true);//���ӻ�
    }
    private void registerListener(){
        bt_regesiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=tf_uname.getText().trim();
                if(uname.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"�������û���");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String oldpass=new String(pf_old.getPassword());
                if(oldpass.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"������ԭ����");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                User user=new User(uname,oldpass);
                user=userBiz.queryUser(user);
                if(user==null){
                    JOptionPane.showMessageDialog(PassView.this,"�û���Ϣ������");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String upass=new String(pf_upass.getPassword());
                if(upass.equals("")){
                    JOptionPane.showMessageDialog(PassView.this,"�����벻��Ϊ��");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                String ensure=new String(pf_ensure.getPassword());
                if(!upass.equals(ensure)){
                    JOptionPane.showMessageDialog(PassView.this,"�����������벻һ�£�����������");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                boolean flag=userBiz.updateUser(user,upass);
                if(!flag){
                    JOptionPane.showMessageDialog(PassView.this,"�޸�ʧ�ܣ�������һ��");
                    Utils.setRegisterVoid(tf_uname, pf_old,pf_upass,pf_ensure);
                    return;
                }
                JOptionPane.showMessageDialog(PassView.this,"�޸ĳɹ�");
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
