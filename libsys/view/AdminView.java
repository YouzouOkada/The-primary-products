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
 * Created by 葉蔵 on 2018/1/25.
 */
public class AdminView extends JFrame {private JTabbedPane pane=new JTabbedPane(JTabbedPane.TOP);
    private Container container=this.getLayeredPane();
    private JPanel panel_title=new JPanel();//题头面板
    private JPanel panel_user=new JPanel(new BorderLayout());//用户面板
    private JPanel panel_user_search =new JPanel(new GridLayout(1,4,10,10));//用户管理
    private JPanel panel_book =new JPanel(new BorderLayout());//书籍面板
    private JPanel panel_book_add =new JPanel(new GridLayout(6,2,0,10));//书籍添加面板
    private JPanel panel_book_del =new JPanel(new GridLayout(4,2,10,10));//书籍删除面板
    private JPanel panel_book_mag =new JPanel(new BorderLayout());//书籍管理面板
    private JPanel panel_book_bt=new JPanel(new GridLayout(1,4,10,10));//书目操作面板
    private JTable tb_user=new JTable();//用户信息显示
    private JLabel lb_user=new JLabel("用户id");
    private JTable tb_book =new JTable();//书目信息显示
    private JLabel lb_title=new JLabel("图书馆管理系统");//题头
    private JLabel lb_bname=new JLabel("        书名：");
    private JLabel lb_author=new JLabel("        作者：");
    private JLabel lb_btype=new JLabel("        类型：");
    private JLabel lb_rank=new JLabel("        限级：");
    private JLabel lb_num=new JLabel("        数量：");
    private JLabel lb_sid=new JLabel("      书本id：");
    private JLabel lb_bid=new JLabel("      书目id：");
    private JLabel lb_basis=new JLabel("        选择查询依据");
    private JComboBox<String> cb_basis=new JComboBox<>(new String[]{"所有书目","书目id"});
    private JTextField tf_user =new JTextField(8);
    private JTextField tf_book =new JTextField(8);
    private JTextField tf_bname =new JTextField(8);
    private JTextField tf_author =new JTextField(8);
    private JTextField tf_btype =new JTextField(8);
    private JTextField tf_rank =new JTextField(8);
    private JTextField tf_num =new JTextField(8);
    private JTextField tf_sid =new JTextField(8);
    private JTextField tf_bid =new JTextField(8);
    private JButton bt_user=new JButton("查询");
    private JButton bt_user_mag=new JButton("解冻");
    private JButton bt_book_add=new JButton("添加");
    private JButton bt_book_del=new JButton("删除");
    private JButton bt_book_search=new JButton("查询");
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

        lb_title=new JLabel("图  书  馆  管  理  系  统");
        lb_title.setFont(new Font("华文隶书",0,52));
        panel_title.add(lb_title);

        //面板模型
        DefaultTableCellRenderer tcr=new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);

        //用户管理面板
        tb_user.setModel(new UserTable(user));
        tb_user.setDefaultRenderer(Object.class,tcr);
        bt_user_mag.setEnabled(false);
        panel_user_search.add(lb_user);
        panel_user_search.add(tf_user);
        panel_user_search.add(bt_user);
        panel_user_search.add(bt_user_mag);
        panel_user.add(tb_user,BorderLayout.CENTER);
        panel_user.add(panel_user_search,BorderLayout.SOUTH);
        pane.add(panel_user,"用户管理");

        //书籍面板
        //显示列表
        tb_book.setModel(new StateTable(states));
        tb_book.setDefaultRenderer(Object.class,tcr);
        panel_book.add(tb_book,BorderLayout.CENTER);
        //管理框
        //添加
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
        //删除
        panel_book_del.add(lb_sid);
        panel_book_del.add(tf_sid);
        panel_book_del.add(lb_bid);
        panel_book_del.add(tf_bid);
        panel_book_del.add(new Panel());
        panel_book_del.add(bt_book_del);
        panel_book_mag.add(panel_book_del,BorderLayout.SOUTH);
        //查询
        panel_book_bt.add(lb_basis);
        panel_book_bt.add(cb_basis);
        tf_book.setEnabled(false);
        panel_book_bt.add(tf_book);
        panel_book_bt.add(bt_book_search);
        panel_book.add(panel_book_mag, BorderLayout.WEST);
        panel_book.add(panel_book_bt,BorderLayout.SOUTH);
        pane.add(panel_book,"书籍管理");

        container.setLayout(new BorderLayout());
        container.add(panel_title,BorderLayout.NORTH);
        container.add(pane,BorderLayout.CENTER);
        this.setVisible(true);
    }


    private  void registerListener(){
        UserBiz userBiz=new UserBizImplement();
        BookOperationBiz operationBiz=new BookOperationBizImplement();
        BookQueryBiz queryBiz=new BookQueryBizImplement();
        //面板切换监听器
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
        //查看用户信息
        bt_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=tf_user.getText().trim();
                int uid=0;
                try {
                    uid=Integer.valueOf(string);
                }catch (NumberFormatException ne){
                    JOptionPane.showMessageDialog(AdminView.this,"请正确输入用户id");
                    return;
                }
                User newUser=userBiz.queryUser(uid);
                if(newUser==null){
                    JOptionPane.showMessageDialog(AdminView.this,"用户不存在");
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
        //用户解冻
        bt_user_mag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uid=Integer.valueOf(tb_user.getValueAt(1,0).toString().trim());
                User newUser=userBiz.queryUser(uid);
                boolean flag=userBiz.updateUser(newUser,5);
                if(!flag) JOptionPane.showMessageDialog(AdminView.this,"请正确输入数量");
                tb_user.setModel(new UserTable(newUser));
            }
        });
        //上架
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
                    JOptionPane.showMessageDialog(AdminView.this,"请正确输入数量");
                    return;
                }
                if(bname.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"书名不能为空");
                    return;
                }
                if(author.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"作者不能为空");
                    return;
                }
                if(btype.equals("")){
                    JOptionPane.showMessageDialog(AdminView.this,"类型不能为空");
                    return;
                }
                if(rank>3||rank<0){
                    JOptionPane.showMessageDialog(AdminView.this,"限级只能为0~3");
                    return;
                }
                Book book=new Book(bname,author,btype,rank,num);
                int i=operationBiz.addState(book);
                if(i>0) JOptionPane.showMessageDialog(AdminView.this,"成功添加"+i+"本");
                else JOptionPane.showMessageDialog(AdminView.this,"添加失败");
                states=queryBiz.queryAllStates();
                tb_book.setModel(new StateTable(states));
            }
        });
        //下架
        bt_book_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tf_sid.getText().trim().equals("")){
                    int sid;
                    try {
                        sid=Integer.valueOf(tf_sid.getText().trim());
                    }catch (NumberFormatException en){
                        JOptionPane.showMessageDialog(AdminView.this,"请正确输入id");
                        return;
                    }
                    State state=queryBiz.queryBookBySid(sid);
                    if(state==null){
                        JOptionPane.showMessageDialog(AdminView.this,"没有要删除的书");
                        return;
                    }
                    boolean flag=operationBiz.delState(sid);
                    if(!flag){
                        JOptionPane.showMessageDialog(AdminView.this,"删除失败");
                        return;
                    }else {
                        JOptionPane.showMessageDialog(AdminView.this,"删除成功");
                        return;
                    }
                }
                int bid;
                try {
                    bid=Integer.valueOf(tf_bid.getText().trim());
                }catch (NumberFormatException en){
                    JOptionPane.showMessageDialog(AdminView.this,"请正确输入id");
                    return;
                }
                Book book=queryBiz.queryBookByBid(bid);
                if(book==null){
                    JOptionPane.showMessageDialog(AdminView.this,"没有要删除的书");
                    return;
                }
                boolean flag=operationBiz.delStateByBook(book);
                if(!flag){
                    JOptionPane.showMessageDialog(AdminView.this,"删除失败");
                    return;
                }else{
                    JOptionPane.showMessageDialog(AdminView.this,"删除成功");
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
        //查询
        bt_book_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_book.setEnabled(false);
                int index=cb_basis.getSelectedIndex();
                //清除过往查询数据
                if(states.size()>0)states.clear();
                if(books.size()>0)books.clear();
                if(index==0){//所有
                    states=queryBiz.queryAllStates();
                }else if(index==1){//根据bid查
                    int bid=0;
                    try {
                        bid=Integer.valueOf(tf_book.getText().trim());
                    }catch (NumberFormatException ne){
                        JOptionPane.showMessageDialog(AdminView.this,"请输入正确bid");
                        return;
                    }
                    states=queryBiz.queryByBid(bid);
                }
                tb_book.setModel(new StateTable(states));
                if(states.size()==0)JOptionPane.showMessageDialog(AdminView.this,"查找内容不存在");
            }
        });
    }

    /**
     * 用户界面模板
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
                    return "用户id";
                case 1:
                    return "用户名";
                case 2:
                    return "用户年龄";
                case 3:
                    return "用户类型";
                case 4:
                    return "已借本书";
                case 5:
                    return "信用分";
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
                    return user.getUtype()==0?"管理员":"用户";
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
     * 书本界面模板
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
                    return "书本id";
                case 1:
                    return "书目id";
                case 2:
                    return "书  名";
                case 3:
                    return "作  者";
                case 4:
                    return "在库状态";
                case 5:
                    return "书本状态";
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
                    return state.getInlib()==0?"在库":"在外";
                case 5:
                    return state.getState()==0?"完好":"损坏";
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
