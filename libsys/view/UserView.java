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
 * Created by �~�i on 2018/1/24.
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
    private JLabel lb_title=new JLabel("ͼ��ݹ���ϵͳ");
    private JLabel lb_basis=new JLabel("        ѡ���ѯ����");
    private JLabel lb_backstate=new JLabel("        �鱾״̬�ǣ�");
    private JComboBox<String> cb_basis=new JComboBox<>(new String[]{"������Ŀ","�ڹ��鼮","��    ��","��    ��"});
    private JComboBox<String> cb_backstate=new JComboBox<>(new String[]{"���","��"});
    private JTextField tf_field=new JTextField(8);
    private JButton bt_book_search=new JButton("��ѯ");
    private JButton bt_book_rent=new JButton("����");
    private JButton bt_book_reserve =new JButton("ԤԼ");
    private JButton bt_record_return =new JButton("����");
    private JButton bt_record_renew =new JButton("����");
    private JButton bt_cancel=new JButton("ȡ��ԤԼ");
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
        this.setSize(800,600);//���ڴ�С
        this.setLocationRelativeTo(null);//�����Ļ����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//�رմ���


        lb_title=new JLabel("ͼ  ��  ��  ��  ��  ϵ  ͳ");
        lb_title.setFont(new Font("��������",0,52));
        panel_title.add(lb_title);

        DefaultTableCellRenderer tcr=new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);

        //�û���Ϣ���
        tb_user.setModel(new UserTable(user));
        tb_user.setDefaultRenderer(Object.class,tcr);
        panel_user.add(tb_user,BorderLayout.CENTER);
        pane.add(panel_user,"�û���Ϣ");

        //�鼮�������
        //��ʾ�б�
        tb_book.setModel(new BookTable(books));
        tb_book.setDefaultRenderer(Object.class,tcr);
        panel_book.add(tb_book,BorderLayout.CENTER);
        //��ѯ��
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
        pane.add(panel_book,"�鼮����");

        //��¼��ѯ���
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
        pane.add(panel_record,"��¼��ѯ");

        //ԤԼ��Ϣ���
        tb_reservation.setModel(new ReservationTable(reservation));
        tb_reservation.setDefaultRenderer(Object.class,tcr);
        panel_reservation_cancel.add(bt_cancel);
        panel_reservation.add(panel_reservation_cancel,BorderLayout.SOUTH);
        panel_reservation.add(tb_reservation,BorderLayout.CENTER);
        pane.add(panel_reservation,"ԤԼ��Ϣ");


        container.setLayout(new BorderLayout());
        container.add(panel_title,BorderLayout.NORTH);
        container.add(pane,BorderLayout.CENTER);
        this.setResizable(false);
        this.setVisible(true);
        if(user.getPoint()==0){
            JOptionPane.showMessageDialog(UserView.this,"���ù��ͣ��˻��ѱ����ᣬ����ϵ����Ա");
            pane.setEnabled(false);
        }
    }


    private  void registerListener(){
        UserBiz userBiz=new UserBizImplement();
        BookOperationBiz operationBiz=new BookOperationBizImplement();
        BookQueryBiz queryBiz=new BookQueryBizImplement();
        RecordBiz recordBiz=new RecordBizImplement();
        ReservationBiz reservationBiz=new ReservationBizImplement();
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
                            JOptionPane.showMessageDialog(UserView.this,"��ԤԼ�����Ѿ����Խ���");
                        }
                    }
                }
            }
        });
        //ѡ����Ŀ�ɽ���
        tb_book.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tb_book.getSelectedRow()>0){
                    bt_book_rent.setEnabled(true);
                    bt_book_reserve.setEnabled(true);
                }
            }
        });
        //��ѯ��
        cb_basis.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item=e.getItem().toString();
                tf_field.setText("");
                tf_field.setEnabled(false);
                if(item.equals("��    ��")||item.equals("��    ��"))tf_field.setEnabled(true);
            }
        });
        //��ѯ��ť
        bt_book_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=cb_basis.getSelectedIndex();
                String text=tf_field.getText().trim();
                if(index>1&&text.equals("")){
                    JOptionPane.showMessageDialog(UserView.this,"�������ѯ����");
                    return;
                }
                if(books.size()>0)books.clear();//���������ѯ����
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
                if(books.size()==0)JOptionPane.showMessageDialog(UserView.this,"�������ݲ�����");
            }
        });
        //����
        bt_book_rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_book_reserve.setEnabled(false);
                bt_book_rent.setEnabled(false);
                tf_field.setEnabled(false);
                if(user.getRentnum()==5){
                    JOptionPane.showMessageDialog(UserView.this,"����̫���ˣ��Ȼ��ϼ�����");
                    return;
                }if(user.getPoint()==1){
                    JOptionPane.showMessageDialog(UserView.this,"���ù��ͣ��޷�����,����ϵ����Ա");
                    return;
                }
                int row=tb_book.getSelectedRow();
                String string=tb_book.getValueAt(row,4).toString();
                if(!string.equals("������")){
                    int rank=Integer.valueOf(string);
                    if(rank==18&&user.getAge()<18||rank==15&&user.getAge()<15||rank==12&&user.getAge()<12){
                        JOptionPane.showMessageDialog(UserView.this,"���ڻ����ʺ��㿴��������������");
                        return;
                    }
                }
                int innum=Integer.valueOf(tb_book.getValueAt(row,5).toString());
                if(innum==0){
                    JOptionPane.showMessageDialog(UserView.this,"�鶼���ȥ�ˣ�����ԤԼ��");
                    return;
                }
                int bid=Integer.valueOf(tb_book.getValueAt(row,0).toString());
                //�鿴�Ƿ����ڽ��Ȿ��
                records=recordBiz.queryRecordByUid(user.getUid());
                //ȥ���ѻ���¼
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBacktime()==null)){
                        records.remove(i);
                        i--;
                    }
                }
                //�����Ƿ��ѽ�
                for(int i=0;i<records.size();i++){
                    if(records.get(i).getBid()==bid){
                        JOptionPane.showMessageDialog(UserView.this,"���Ѿ���һ���ˣ�������һ�����");
                        return;
                    }
                }

                boolean flag=operationBiz.rentBook(bid,user.getUid());
                books.clear();
                tb_book.setModel(new BookTable(books));
                if(flag){
                    //�����ԤԼ��ȡ��ԤԼ��Ϣ
                    reservation=reservationBiz.queryByUid(user.getUid());
                    if(reservation!=null&&reservation.getBid()==bid) reservationBiz.cancel(reservation.getResid());
                    JOptionPane.showMessageDialog(UserView.this,"����ɹ�");
                }else{
                    JOptionPane.showMessageDialog(UserView.this,"����ʧ��");
                }
                user=userBiz.queryUser(user.getUid());
            }
        });
        //ԤԼ
        bt_book_reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=tb_book.getSelectedRow();
                int innum=Integer.valueOf(tb_book.getValueAt(row,5).toString());
                if(reservation!=null){
                    JOptionPane.showMessageDialog(UserView.this,"ÿ��ֻ��ԤԼһ������ȡ��֮ǰ��ԤԼ");
                    return;
                }
                String string=tb_book.getValueAt(row,4).toString();
                if(!string.equals("������")){
                    int rank=Integer.valueOf(string);
                    if(rank==18&&user.getAge()<18||rank==15&&user.getAge()<15||rank==12&&user.getAge()<12){
                        JOptionPane.showMessageDialog(UserView.this,"����̫С��������������");
                        return;
                    }
                }
                if(innum>0){
                    JOptionPane.showMessageDialog(UserView.this,"���п�棬��ֱ�ӽ���");
                    return;
                }
                int bid=Integer.valueOf(tb_book.getValueAt(row,0).toString());
                records=recordBiz.queryRecordByUid(user.getUid());
                //ȥ���ѻ���¼
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBacktime()==null)){
                        records.remove(i);
                        i--;
                    }
                }
                //�����Ƿ��ѽ�
                for(int i=0;i<records.size();i++){
                    if(!(records.get(i).getBid()==bid)){
                        JOptionPane.showMessageDialog(UserView.this,"�Ȿ�����Ѿ���һ���ˣ�������һ�����");
                        return;
                    }
                }
                reservation=new Reservation(bid,user.getUid());
                ReservationBiz reservationBiz=new ReservationBizImplement();
                boolean flag=reservationBiz.reserve(reservation);
                if(!flag){
                    reservation=null;//���ԤԼ��Ϣ
                    JOptionPane.showMessageDialog(UserView.this,"ԤԼʧ��");
                }else {
                    JOptionPane.showMessageDialog(UserView.this,"ԤԼ�ɹ�");
                }
            }
        });
        //ѡ����Ŀ�ɻ��������
        tb_record.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tb_record.getSelectedRow()>0){
                    bt_record_return.setEnabled(true);
                    bt_record_renew.setEnabled(true);
                }
            }
        });
        //����
        bt_record_renew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_record_return.setEnabled(false);
                bt_record_renew.setEnabled(false);
                int row=tb_record.getSelectedRow();
                if(tb_record.getValueAt(row,3)!=null){
                    JOptionPane.showMessageDialog(UserView.this,"���Ѿ����ˣ��޷�����");
                    return;
                }
                String string=tb_record.getValueAt(row,5).toString();
                int renew=string.equals("δ����")?0:1;
                if(renew==1){
                    JOptionPane.showMessageDialog(UserView.this,"ֻ������һ��");
                    return;
                }
                int rid=Integer.valueOf(tb_record.getValueAt(row,6).toString());
                int bid=recordBiz.queryRecordByRid(rid).getBid();
                Book book=queryBiz.queryBookByBid(bid);
                int size=reservationBiz.queryByBook(book.getBname()).size();
                if(size>book.getInnum()){
                    JOptionPane.showMessageDialog(UserView.this,"����ԤԼ���Ȿ�飬��ʱ��������");
                    return;
                }
                boolean flag=operationBiz.renew(rid);
                if(flag) JOptionPane.showMessageDialog(UserView.this,"����ɹ�");
                else JOptionPane.showMessageDialog(UserView.this,"����ʧ��");
                tb_record.setModel(new RecordTable(records));
            }
        });

        //����
        bt_record_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt_record_return.setEnabled(false);
                bt_record_renew.setEnabled(false);
                int row=tb_record.getSelectedRow();
                if(tb_record.getValueAt(row,3)!=null){
                    JOptionPane.showMessageDialog(UserView.this,"�Ѿ������ˣ��벻Ҫ�ظ�����");
                    return;
                }
                int rid=Integer.valueOf(tb_record.getValueAt(row,6).toString());
                int backstate=cb_backstate.getSelectedIndex();
                int renewed=0;
                String string=tb_record.getValueAt(row,5).toString();
                renewed=string.equals("������")?0:1;
                boolean flag=operationBiz.returnBook(rid,backstate,renewed);
                if(flag)JOptionPane.showMessageDialog(UserView.this,"�������");
                else JOptionPane.showMessageDialog(UserView.this,"����ʧ��");
                records=recordBiz.queryRecordByUid(user.getUid());
                tb_record.setModel(new RecordTable(records));
            }
        });
        /**
         * ȡ��ԤԼ
         */
        bt_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservation=reservationBiz.queryByUid(user.getUid());
                if(reservation==null){
                    JOptionPane.showMessageDialog(UserView.this,"û��ԤԼ��¼");
                    return;
                }
                int resid=reservation.getResid();
                reservationBiz.cancel(resid);
            }
        });
    }

    /**
     * �û�����ģ��
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
     * �������ģ��
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
                    return "��Ŀid";
                case 1:
                    return "��  ��";
                case 2:
                    return "��  ��";
                case 3:
                    return "��  ��";
                case 4:
                    return "��������";
                case 5:
                    return "�ڿ�����";
                case 6:
                    return "�������";
                case 7:
                    return "�������";
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
                            return "������";
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
     * ��¼����ģ��
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
                    return "�û�id";
                case 1:
                    return "��Ŀid";
                case 2:
                    return "���ʱ��";
                case 3:
                    return "�黹ʱ��";
                case 4:
                    return "�黹״̬";
                case 5:
                    return "�Ƿ�����";
                case 6:
                    return "��¼id";
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
                    return record.getBackstate()==0?"����":"��";
                case 5:
                    return record.getRenew()==0?"δ����":"������";
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
     * ԤԼ����ģ��
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
                    return "�û�id";
                case 1:
                    return "��Ŀid";
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
