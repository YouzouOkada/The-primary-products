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
 * Created by 葉蔵 on 2018/1/24.
 */
public class UserView extends JFrame {
    private JTabbedPane pane=new JTabbedPane(JTabbedPane.TOP);
    private Container container=this.getLayeredPane();
    private JPanel panel_title=new JPanel();
    private JPanel panel_user=new JPanel(new BorderLayout());
    private JPanel panel_book =new JPanel(new BorderLayout());
    private JPanel panel_book_bt=new JPanel(new GridLayout(2,5,10,20));
    private JPanel panel_record=new JPanel(new BorderLayout());
    private JPanel panel_record_return=new JPanel(new GridLayout(1,4));
    private JPanel panel_reservation_cancel=new JPanel();
    private JPanel panel_reservation=new JPanel(new BorderLayout());
    private JTable tb_user=new JTable();
    private JTable tb_book =new JTable();
    private JTable tb_record=new JTable();
    private JTable tb_reservation=new JTable();
    private JLabel lb_title=new JLabel("图书馆管理系统");
    private JLabel lb_basis=new JLabel("        选择查询依据");
    private JLabel lb_backstate=new JLabel("        书本状态是：");
    private JComboBox<String> cb_basis=new JComboBox<>(new String[]{"所有书目","在馆书籍","书    名","作    者"});
    private JComboBox<String> cb_backstate=new JComboBox<>(new String[]{"完好","损坏"});
    private JTextField tf_field=new JTextField(8);
    private JButton bt_book_search=new JButton("查询");
    private JButton bt_book_rent=new JButton("借阅");
    private JButton bt_book_reserve =new JButton("预约");
    private JButton bt_record_return =new JButton("还书");
    private JButton bt_record_renew =new JButton("续借");
    private JButton bt_cancel=new JButton("取消预约");
    private User user=null;
    private List<Book> books =new ArrayList<>();
    private List<Record> records=new ArrayList<>();
    private Reservation reservation=null;

    public UserView(User user){
        this.user=user;
        init();
        registerListener();
    }

    private void init(){
        this.setSize(800,600);//窗口大小
        this.setLocationRelativeTo(null);//相对屏幕居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口


        lb_title=new JLabel("图  书  馆  管  理  系  统");
        lb_title.setFont(new Font("华文隶书",0,52));
        panel_title.add(lb_title);

        DefaultTableCellRenderer tcr=new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);

        //用户信息面板
        tb_user.setModel(new UserTable(user));
        tb_user.setDefaultRenderer(Object.class,tcr);
        panel_user.add(tb_user,BorderLayout.CENTER);
        pane.add(panel_user,"用户信息");

        //书籍借阅面板
        //显示列表
        tb_book.setModel(new BookTable(books));
        tb_book.setDefaultRenderer(Object.class,tcr);
        panel_book.add(tb_book,BorderLayout.CENTER);
        //查询框
        panel_book_bt.add(lb_basis);
        panel_book_bt.add(cb_basis);
        bt_book_reserve.setEnabled(false);
        bt_book_rent.setEnabled(false);
        tf_field.setEnabled(false);
        panel_book_bt.add(tf_field);
        panel_book_bt.add(bt_book_search);
        panel_book_bt.add(bt_book_rent);
        panel_book_bt.add(bt_book_reserve);
        panel_book.add(panel_book_bt,BorderLayout.SOUTH);
        pane.add(panel_book,"书籍借阅");

        //记录查询面板
        tb_record.setModel(new RecordTable(records));
        tb_record.setDefaultRenderer(Object.class,tcr);
        bt_record_return.setEnabled(false);
        bt_record_renew.setEnabled(false);
        panel_record_return.add(lb_backstate);
        panel_record_return.add(cb_backstate);
        panel_record_return.add(bt_record_return);
        panel_record_return.add(bt_record_renew);
        panel_record.add(panel_record_return,BorderLayout.SOUTH);
        panel_record.add(tb_record,BorderLayout.CENTER);
        pane.add(panel_record,"记录查询");

        //预约信息面板
        tb_reservation.setModel(new ReservationTable(reservation));
        tb_reservation.setDefaultRenderer(Object.class,tcr);
        panel_reservation_cancel.add(bt_cancel);
        panel_reservation.add(panel_reservation_cancel,BorderLayout.SOUTH);
        panel_reservation.add(tb_reservation,BorderLayout.CENTER);
        pane.add(panel_reservation,"预约信息");


        container.setLayout(new BorderLayout());
        container.add(panel_title,BorderLayout.NORTH);
        container.add(pane,BorderLayout.CENTER);
        this.setResizable(false);
        this.setVisible(true);
        if(user.getPoint()==0){
            JOptionPane.showMessageDialog(UserView.this,"信用过低，账户已被冻结，请联系管理员");
            pane.setEnabled(false);
        }
    }


    private  void registerListener(){
        UserBiz userBiz=new UserBizImplement();
        BookOperationBiz operationBiz=new BookOperationBizImplement();
        BookQueryBiz queryBiz=new BookQueryBizImplement();
        RecordBiz recordBiz=new RecordBizImplement();
        ReservationBiz reservationBiz=new ReservationBizImplement();
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
                if(index==1){
                    books.clear();
                    tb_book.setModel(new BookTable(books));
                }
                if(index==2){
                    int uid=user.getUid();
                    records=recordBiz.queryRecordByUid(uid);
                    tb_record.setModel(new RecordTable(records));
                }
                if(index==3){
                    int uid=user.getUid();
                    reservation=reservationBiz.queryByUid(uid);
                    tb_reservation.setModel(new ReservationTable(reservation));
                    if(reservation!=null){
                        int bid=reservation.getBid();
                        if(queryBiz.queryBookByBid(bid).getInnum()>0){
                            JOptionPane.showMessageDialog(UserView.this,"你预约的书已经可以借阅");
                        }
                    }
                }
            }
        });
        //选中项目可借书
        tb_book.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tb_book.getSelectedRow()>0){
                    bt_book_rent.setEnabled(true);
                    bt_book_reserve.setEnabled(true);
                }
            }
        });
        //查询框
        cb_basis.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=e.getItem().toString();
                tf_field.setText("");
                tf_field.setEnabled(false);
                if(item.equals("书    名")||item.equals("作    者"))tf_field.setEnabled(true);
            }
        });
        //查询按钮
        bt_book_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=cb_basis.getSelectedIndex();
                String text=tf_field.getText().trim();
                if(index>1&&text.equals("")){
                    JOptionPane.showMessageDialog(UserView.this,"请输入查询内容");
                    return;
                }
                if(books.size()>0)books.clear();//清除过往查询数据
                if(index==0){
                    books=queryBiz.queryAllBooks();
                }else if(index==1){
                    books=queryBiz.queryByInLib(0);
                }else if(index==2){
                    books=queryBiz.queryBookByBname(text);
                }else if(index==3){
                    books=queryBiz.queryBookByAuthor(text);
                }
                tb_book.setModel(new BookTable(books));
                if(books.size()==0)JOptionPane.showMessageDialog(UserView.this,"查找内容不存在");
            }
        });
        //借书
        bt_book_rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_book_reserve.setEnabled(false);
                bt_book_rent.setEnabled(false);
                tf_field.setEnabled(false);
                if(user.getRentnum()==5){
                    JOptionPane.showMessageDialog(UserView.this,"你借的太多了，先还上几本吧");
                    return;
                }if(user.getPoint()==1){
                    JOptionPane.showMessageDialog(UserView.this,"信用过低，无法借书,请联系管理员");
                    return;
                }
                int row=tb_book.getSelectedRow();
                String string=tb_book.getValueAt(row,4).toString();
                if(!string.equals("无限制")){
                    int rank=Integer.valueOf(string);
                    if(rank==18&&user.getAge()<18||rank==15&&user.getAge()<15||rank==12&&user.getAge()<12){
                        JOptionPane.showMessageDialog(UserView.this,"现在还不适合你看，过几年再来吧");
                        return;
                    }
                }
                int innum=Integer.valueOf(tb_book.getValueAt(row,5).toString());
                if(innum==0){
                    JOptionPane.showMessageDialog(UserView.this,"书都借出去了，试试预约吧");
                    return;
                }
                int bid=Integer.valueOf(tb_book.getValueAt(row,0).toString());
                //查看是否正在借这本书
                records=recordBiz.queryRecordByUid(user.getUid());
                //去掉已还记录
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBacktime()==null)){
                        records.remove(i);
                        i--;
                    }
                }
                //查找是否已借
                for(int i=0;i<records.size();i++){
                    if(records.get(i).getBid()==bid){
                        JOptionPane.showMessageDialog(UserView.this,"你已经有一本了，给别人一点机会");
                        return;
                    }
                }

                boolean flag=operationBiz.rentBook(bid,user.getUid());
                books.clear();
                tb_book.setModel(new BookTable(books));
                if(flag){
                    //如果有预约则取消预约信息
                    reservation=reservationBiz.queryByUid(user.getUid());
                    if(reservation!=null&&reservation.getBid()==bid) reservationBiz.cancel(reservation.getResid());
                    JOptionPane.showMessageDialog(UserView.this,"借书成功");
                }else{
                    JOptionPane.showMessageDialog(UserView.this,"借书失败");
                }
                user=userBiz.queryUser(user.getUid());
            }
        });
        //预约
        bt_book_reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=tb_book.getSelectedRow();
                int innum=Integer.valueOf(tb_book.getValueAt(row,5).toString());
                if(reservation!=null){
                    JOptionPane.showMessageDialog(UserView.this,"每人只能预约一本，请取消之前的预约");
                    return;
                }
                String string=tb_book.getValueAt(row,4).toString();
                if(!string.equals("无限制")){
                    int rank=Integer.valueOf(string);
                    if(rank==18&&user.getAge()<18||rank==15&&user.getAge()<15||rank==12&&user.getAge()<12){
                        JOptionPane.showMessageDialog(UserView.this,"年龄太小，过几年再来吧");
                        return;
                    }
                }
                if(innum>0){
                    JOptionPane.showMessageDialog(UserView.this,"还有库存，请直接借阅");
                    return;
                }
                int bid=Integer.valueOf(tb_book.getValueAt(row,0).toString());
                records=recordBiz.queryRecordByUid(user.getUid());
                //去掉已还记录
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBacktime()==null)){
                        records.remove(i);
                        i--;
                    }
                }
                //查找是否已借
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBid()==bid)){
                        JOptionPane.showMessageDialog(UserView.this,"这本书你已经有一本了，给别人一点机会");
                        return;
                    }
                }
                reservation=new Reservation(bid,user.getUid());
                ReservationBiz reservationBiz=new ReservationBizImplement();
                boolean flag=reservationBiz.reserve(reservation);
                if(!flag){
                    reservation=null;//清除预约信息
                    JOptionPane.showMessageDialog(UserView.this,"预约失败");
                }else {
                    JOptionPane.showMessageDialog(UserView.this,"预约成功");
                }
            }
        });
        //选中项目可还书或续借
        tb_record.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tb_record.getSelectedRow()>0){
                    bt_record_return.setEnabled(true);
                    bt_record_renew.setEnabled(true);
                }
            }
        });
        //续借
        bt_record_renew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_record_return.setEnabled(false);
                bt_record_renew.setEnabled(false);
                int row=tb_record.getSelectedRow();
                if(tb_record.getValueAt(row,3)!=null){
                    JOptionPane.showMessageDialog(UserView.this,"书已经还了，无法续借");
                    return;
                }
                String string=tb_record.getValueAt(row,5).toString();
                int renew=string.equals("未续借")?0:1;
                if(renew==1){
                    JOptionPane.showMessageDialog(UserView.this,"只能续借一次");
                    return;
                }
                int rid=Integer.valueOf(tb_record.getValueAt(row,6).toString());
                int bid=recordBiz.queryRecordByRid(rid).getBid();
                Book book=queryBiz.queryBookByBid(bid);
                int size=reservationBiz.queryByBook(book.getBname()).size();
                if(size>book.getInnum()){
                    JOptionPane.showMessageDialog(UserView.this,"有人预约了这本书，暂时不能续借");
                    return;
                }
                boolean flag=operationBiz.renew(rid);
                if(flag) JOptionPane.showMessageDialog(UserView.this,"续借成功");
                else JOptionPane.showMessageDialog(UserView.this,"续借失败");
                tb_record.setModel(new RecordTable(records));
            }
        });

        //还书
        bt_record_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_record_return.setEnabled(false);
                bt_record_renew.setEnabled(false);
                int row=tb_record.getSelectedRow();
                if(tb_record.getValueAt(row,3)!=null){
                    JOptionPane.showMessageDialog(UserView.this,"已经还过了，请不要重复操作");
                    return;
                }
                int rid=Integer.valueOf(tb_record.getValueAt(row,6).toString());
                int backstate=cb_backstate.getSelectedIndex();
                int renewed=0;
                String string=tb_record.getValueAt(row,5).toString();
                renewed=string.equals("可续借")?0:1;
                boolean flag=operationBiz.returnBook(rid,backstate,renewed);
                if(flag)JOptionPane.showMessageDialog(UserView.this,"还书完成");
                else JOptionPane.showMessageDialog(UserView.this,"还书失败");
                records=recordBiz.queryRecordByUid(user.getUid());
                tb_record.setModel(new RecordTable(records));
            }
        });
        /**
         * 取消预约
         */
        bt_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservation=reservationBiz.queryByUid(user.getUid());
                if(reservation==null){
                    JOptionPane.showMessageDialog(UserView.this,"没有预约记录");
                    return;
                }
                int resid=reservation.getResid();
                reservationBiz.cancel(resid);
            }
        });
    }

    /**
     * 用户界面模板
     */
    private class UserTable implements TableModel{
        private User user=null;
        public UserTable(User user){
            this.user=user;
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
     * 借书界面模板
     */
    private class BookTable implements  TableModel{
        private List<Book> books =null;

        public BookTable(List<Book> books){
            this.books = books;
        }
        @Override
        public int getRowCount() {
            return books.size()+1;
        }

        @Override
        public int getColumnCount() {
            return 8;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return "书目id";
                case 1:
                    return "书  名";
                case 2:
                    return "作  者";
                case 3:
                    return "类  型";
                case 4:
                    return "年龄限制";
                case 5:
                    return "在库数量";
                case 6:
                    return "借出数量";
                case 7:
                    return "借出次数";
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
            Book book= books.get(rowIndex-1);
            switch (columnIndex){
                case 0:
                    return book.getBid();
                case 1:
                    return book.getBname();
                case 2:
                    return book.getAuthor();
                case 3:
                    return book.getBtype();
                case 4:
                    switch (book.getRank()){
                        case 0:
                            return "无限制";
                        case 1:
                            return "18";
                        case 2:
                            return "15";
                        case 3:
                            return "12";
                    };
                case 5:
                    return book.getInnum();
                case 6:
                    return book.getOutnum();
                case 7:
                    return book.getTimes();
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
     * 记录界面模板
     */
    private class RecordTable implements TableModel{
        private List<Record> records=null;
        public RecordTable(List<Record> records){
            this.records=records;
        }
        @Override
        public int getRowCount() {
            return records.size()+1;
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return "用户id";
                case 1:
                    return "书目id";
                case 2:
                    return "借出时间";
                case 3:
                    return "归还时间";
                case 4:
                    return "归还状态";
                case 5:
                    return "是否续借";
                case 6:
                    return "记录id";
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
            Record record=records.get(rowIndex-1);
            switch (columnIndex){
                case 0:
                    return record.getUid();
                case 1:
                    return record.getBid();
                case 2:
                    return record.getOuttime();
                case 3:
                    return record.getBacktime();
                case 4:
                    return record.getBackstate()==0?"正常":"损坏";
                case 5:
                    return record.getRenew()==0?"未续借":"已续借";
                case 6:
                    return record.getRid();
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
     * 预约界面模板
     */
    private class ReservationTable implements TableModel{
        private Reservation reservation=null;
        public ReservationTable(Reservation reservation){
            this.reservation=reservation;
        }
        @Override
        public int getRowCount() {
            return reservation==null?1:2;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return "用户id";
                case 1:
                    return "书目id";
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
            return columnIndex==0?reservation.getUid():reservation.getBid();
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
