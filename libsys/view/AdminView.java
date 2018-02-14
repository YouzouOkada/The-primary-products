package com.youzou.libsys.view;

import com.youzou.libsys.biz.*;
import com.youzou.libsys.entity.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by �~�i on 2018/1/25.
 */
public class AdminView extends JFrame {private JTabbedPane pane=new JTabbedPane(JTabbedPane.TOP);
    private Container container=this.getLayeredPane();
    private JPanel panel_title=new JPanel();//��ͷ���
    private JPanel panel_user=new JPanel(new BorderLayout());//�û����
    private JPanel panel_user_search =new JPanel(new GridLayout(1,4,10,10));//�û�����
    private JPanel panel_book =new JPanel(new BorderLayout());//�鼮���
    private JPanel panel_book_add =new JPanel(new GridLayout(6,2,0,10));//�鼮������
    private JPanel panel_book_del =new JPanel(new GridLayout(4,2,10,10));//�鼮ɾ�����
    private JPanel panel_book_mag =new JPanel(new BorderLayout());//�鼮�������
    private JPanel panel_book_bt=new JPanel(new GridLayout(1,4,10,10));//��Ŀ�������
    private JTable tb_user=new JTable();//�û���Ϣ��ʾ
    private JLabel lb_user=new JLabel("�û�id");
    private JTable tb_book =new JTable();//��Ŀ��Ϣ��ʾ
    private JLabel lb_title=new JLabel("ͼ��ݹ���ϵͳ");//��ͷ
    private JLabel lb_bname=new JLabel("        ������");
    private JLabel lb_author=new JLabel("        ���ߣ�");
    private JLabel lb_btype=new JLabel("        ���ͣ�");
    private JLabel lb_rank=new JLabel("        �޼���");
    private JLabel lb_num=new JLabel("        ������");
    private JLabel lb_sid=new JLabel("      �鱾id��");
    private JLabel lb_bid=new JLabel("      ��Ŀid��");
    private JLabel lb_basis=new JLabel("        ѡ���ѯ����");
    private JComboBox<String> cb_basis=new JComboBox<>(new String[]{"������Ŀ","��Ŀid"});
    private JTextField tf_user =new JTextField(8);
    private JTextField tf_book =new JTextField(8);
    private JTextField tf_bname =new JTextField(8);
    private JTextField tf_author =new JTextField(8);
    private JTextField tf_btype =new JTextField(8);
    private JTextField tf_rank =new JTextField(8);
    private JTextField tf_num =new JTextField(8);
    private JTextField tf_sid =new JTextField(8);
    private JTextField tf_bid =new JTextField(8);
    private JButton bt_user=new JButton("��ѯ");
    private JButton bt_user_mag=new JButton("�ⶳ");
    private JButton bt_book_add=new JButton("���");
    private JButton bt_book_del=new JButton("ɾ��");
    private JButton bt_book_search=new JButton("��ѯ");
    private User user=null;
    private java.util.List<Book> books =new ArrayList<>();
    private java.util.List<State> states =new ArrayList<>();
    public AdminView(User user){
        this.user=user;
        init();
        registerListener();
    }
    private void init(){
        this.setSize(800,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lb_title=new JLabel("ͼ  ��  ��  ��  ��  ϵ  ͳ");
        lb_title.setFont(new Font("��������",0,52));
        panel_title.add(lb_title);

        //���ģ��
        DefaultTableCellRenderer tcr=new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);

        //�û��������
        tb_user.setModel(new UserTable(user));
        tb_user.setDefaultRenderer(Object.class,tcr);
        bt_user_mag.setEnabled(false);
        panel_user_search.add(lb_user);
        panel_user_search.add(tf_user);
        panel_user_search.add(bt_user);
        panel_user_search.add(bt_user_mag);
        panel_user.add(tb_user,BorderLayout.CENTER);
        panel_user.add(panel_user_search,BorderLayout.SOUTH);
        pane.add(panel_user,"�û�����");

        //�鼮���
        //��ʾ�б�
        tb_book.setModel(new StateTable(states));
        tb_book.setDefaultRenderer(Object.class,tcr);
        panel_book.add(tb_book,BorderLayout.CENTER);
        //�����
        //���
        panel_book_add.add(lb_bname);
        panel_book_add.add(tf_bname);
        panel_book_add.add(lb_author);
        panel_book_add.add(tf_author);
        panel_book_add.add(lb_btype);
        panel_book_add.add(tf_btype);
        panel_book_add.add(lb_rank);
        panel_book_add.add(tf_rank);
        panel_book_add.add(lb_num);
        panel_book_add.add(tf_num);
        panel_book_add.add(new Panel());
        panel_book_add.add(bt_book_add);
        panel_book_mag.add(panel_book_add,BorderLayout.NORTH);
        //ɾ��
        panel_book_del.add(lb_sid);
        panel_book_del.add(tf_sid);
        panel_book_del.add(lb_bid);
        panel_book_del.add(tf_bid);
        panel_book_del.add(new Panel());
        panel_book_del.add(bt_book_del);
        panel_book_mag.add(panel_book_del,BorderLayout.SOUTH);
        //��ѯ
        panel_book_bt.add(lb_basis);
        panel_book_bt.add(cb_basis);
        tf_book.setEnabled(false);
        panel_book_bt.add(tf_book);
        panel_book_bt.add(bt_book_search);
        panel_book.add(panel_book_mag, BorderLayout.WEST);
        panel_book.add(panel_book_bt,BorderLayout.SOUTH);
        pane.add(panel_book,"�鼮����");

        container.setLayout(new BorderLayout());
        container.add(panel_title,BorderLayout.NORTH);
        container.add(pane,BorderLayout.CENTER);
        this.setVisible(true);
    }


    private  void registerListener(){
        UserBiz userBiz=new UserBizImplement();
        BookOperationBiz operationBiz=new BookOperationBizImplement();
        BookQueryBiz queryBiz=new BookQueryBizImplement();
        //����л�������
        pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane=(JTabbedPane)e.getSource();
                int index=tabbedPane.getSelectedIndex();
                if(index==0){
                    user=userBiz.queryUser(user);
                    tb_user.setModel(new UserTable(user));
                }
            }
        });
        //�鿴�û���Ϣ
        bt_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=tf_user.getText().trim();
                int uid=0;
                try {
                    uid=Integer.valueOf(string);
                }catch (NumberFormatException ne){
                    JOptionPane.showMessageDialog(AdminView.this,"����ȷ�����û�id");
                    return;
                }
                User newUser=userBiz.queryUser(uid);
                if(newUser==null){
                    JOptionPane.showMessageDialog(AdminView.this,"�û�������");
                    return;
                }
                tb_user.setModel(new UserTable(newUser));
            }
        });
        tb_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row=tb_user.getSelectedRow();
                int point=Integer.valueOf(tb_user.getValueAt(row,5).toString().trim());
                if(point==0){
                    bt_user_mag.setEnabled(true);
                }else {
                    bt_user_mag.setEnabled(false);
                }
            }
        });
        //�û��ⶳ
        bt_user_mag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uid=Integer.valueOf(tb_user.getValueAt(1,0).toString().trim());
                User newUser=userBiz.queryUser(uid);
                boolean flag=userBiz.updateUser(newUser,5);
                if(!flag) JOptionPane.showMessageDialog(AdminView.this,"����ȷ��������");
                tb_user.setModel(new UserTable(newUser));
            }
        });
        //�ϼ�
        bt_book_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bname=tf_bname.getText().trim();
                String author=tf_author.getText().trim();
                String btype=tf_btype.getText().trim();
                int rank;
                int num;
                try{
                    rank=Integer.valueOf(tf_rank.getText().trim());
                    num = Integer.valueOf(tf_num.getText().trim());
                }catch (NumberFormatException en){
                    JOptionPane.showMessageDialog(AdminView.this,"����ȷ��������");
                    return;
                }
                if(bname.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"��������Ϊ��");
                    return;
                }
                if(author.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"���߲���Ϊ��");
                    return;
                }
                if(btype.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"���Ͳ���Ϊ��");
                    return;
                }
                if(rank>3||rank<0){
                    JOptionPane.showMessageDialog(AdminView.this,"�޼�ֻ��Ϊ0~3");
                    return;
                }
                Book book=new Book(bname,author,btype,rank,num);
                int i=operationBiz.addState(book);
                if(i>0) JOptionPane.showMessageDialog(AdminView.this,"�ɹ����"+i+"��");
                else JOptionPane.showMessageDialog(AdminView.this,"���ʧ��");
                states=queryBiz.queryAllStates();
                tb_book.setModel(new StateTable(states));
            }
        });
        //�¼�
        bt_book_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tf_sid.getText().trim().equals("")){
                    int sid;
                    try {
                        sid=Integer.valueOf(tf_sid.getText().trim());
                    }catch (NumberFormatException en){
                        JOptionPane.showMessageDialog(AdminView.this,"����ȷ����id");
                        return;
                    }
                    State state=queryBiz.queryBookBySid(sid);
                    if(state==null){
                        JOptionPane.showMessageDialog(AdminView.this,"û��Ҫɾ������");
                        return;
                    }
                    boolean flag=operationBiz.delState(sid);
                    if(!flag){
                        JOptionPane.showMessageDialog(AdminView.this,"ɾ��ʧ��");
                        return;
                    }else {
                        JOptionPane.showMessageDialog(AdminView.this,"ɾ���ɹ�");
                        return;
                    }
                }
                int bid;
                try {
                    bid=Integer.valueOf(tf_bid.getText().trim());
                }catch (NumberFormatException en){
                    JOptionPane.showMessageDialog(AdminView.this,"����ȷ����id");
                    return;
                }
                Book book=queryBiz.queryBookByBid(bid);
                if(book==null){
                    JOptionPane.showMessageDialog(AdminView.this,"û��Ҫɾ������");
                    return;
                }
                boolean flag=operationBiz.delStateByBook(book);
                if(!flag){
                    JOptionPane.showMessageDialog(AdminView.this,"ɾ��ʧ��");
                    return;
                }else{
                    JOptionPane.showMessageDialog(AdminView.this,"ɾ���ɹ�");
                }
                states=queryBiz.queryAllStates();
                tb_book.setModel(new StateTable(states));
            }
        });
        cb_basis.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cb_basis.getSelectedIndex()==1)tf_book.setEnabled(true);
            }
        });
        //��ѯ
        bt_book_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_book.setEnabled(false);
                int index=cb_basis.getSelectedIndex();
                //���������ѯ����
                if(states.size()>0)states.clear();
                if(books.size()>0)books.clear();
                if(index==0){//����
                    states=queryBiz.queryAllStates();
                }else if(index==1){//����bid��
                    int bid=0;
                    try {
                        bid=Integer.valueOf(tf_book.getText().trim());
                    }catch (NumberFormatException ne){
                        JOptionPane.showMessageDialog(AdminView.this,"��������ȷbid");
                        return;
                    }
                    states=queryBiz.queryByBid(bid);
                }
                tb_book.setModel(new StateTable(states));
                if(states.size()==0)JOptionPane.showMessageDialog(AdminView.this,"�������ݲ�����");
            }
        });
    }

    /**
     * �û�����ģ��
     */
    private class UserTable implements TableModel {
        private User user=null;

        public UserTable(User user) {
            this.user = user;
        }

        @Override
        public int getRowCount() {
            return user==null?1:2;
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return "�û�id";
                case 1:
                    return "�û���";
                case 2:
                    return "�û�����";
                case 3:
                    return "�û�����";
                case 4:
                    return "�ѽ豾��";
                case 5:
                    return "���÷�";
                default:
                    return null;
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(rowIndex==0){
                return getColumnName(columnIndex);
            }
            switch (columnIndex) {
                case 0:
                    return user.getUid();
                case 1:
                    return user.getUname();
                case 2:
                    return user.getAge();
                case 3:
                    return user.getUtype()==0?"����Ա":"�û�";
                case 4:
                    return user.getRentnum();
                case 5:
                    return user.getPoint();
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }

    /**
     * �鱾����ģ��
     */
    private class StateTable implements  TableModel{
        private java.util.List<State> states =null;

        public StateTable(java.util.List<State> states){
            this.states = states;
        }
        @Override
        public int getRowCount() {
            return states.size()+1;
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return "�鱾id";
                case 1:
                    return "��Ŀid";
                case 2:
                    return "��  ��";
                case 3:
                    return "��  ��";
                case 4:
                    return "�ڿ�״̬";
                case 5:
                    return "�鱾״̬";
                default:
                    return null;
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(rowIndex==0){
                return getColumnName(columnIndex);
            }
            State state=states.get(rowIndex-1);
            BookQueryBiz queryBiz=new BookQueryBizImplement();
            int bid=state.getBid();
            Book book=queryBiz.queryBookByBid(bid);
            if(book==null)return null;
            switch (columnIndex){
                case 0:
                    return state.getSid();
                case 1:
                    return state.getBid();
                case 2:
                    return book.getBname();
                case 3:
                    return book.getAuthor();
                case 4:
                    return state.getInlib()==0?"�ڿ�":"����";
                case 5:
                    return state.getState()==0?"���":"��";
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }
}
