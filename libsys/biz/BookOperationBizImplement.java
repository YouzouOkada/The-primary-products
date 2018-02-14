package com.youzou.libsys.biz;

import com.youzou.libsys.dao.*;
import com.youzou.libsys.entity.Book;
import com.youzou.libsys.entity.Record;
import com.youzou.libsys.entity.State;
import com.youzou.libsys.entity.User;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public class BookOperationBizImplement implements BookOperationBiz {
    private UserBiz userBiz=new UserBizImplement();
    private UserDao userDao=new UserDaoImplement();
    private StateDao stateDao=new StateDaoImplememt();
    private BookDao bookDao=new BookDaoImplement();
    private RecordDao recordDao=new RecordDaoImplement();

    /**
     * 上架新书
     * @param book
     * @return 上架的本书
     */
    @Override
    public int addState(Book book) {
        //此时还未确定是否需要添加bid，故不能用book.getBid();
        int bid=bookDao.queryBook(book);
        boolean flag;
        //如果上架的书之前有过则直接修改数量
        if(bid>0){
            Book book1=bookDao.queryBookByBid(bid);
            book1.setInnum(book1.getInnum()+book.getInnum());
            flag=bookDao.updateBook(book1);
            if(!flag)return -1;
            bid=bookDao.queryBook(book1);//找出正确bid
            //书目表已经添加完成
            int ssize=stateDao.queryByBid(bid).size();//查看该书已经存在的本数
            book=bookDao.queryBookByBid(bid);
            int addnum=book.getInnum()+book.getOutnum()-ssize;//添加数总数减去已经存在的本数
            for(int i=0;i<addnum;i++) {
                State state = new State(bid);
                stateDao.addState(state);
            }
            return addnum;
        }else{
            flag=bookDao.addBook(book);
            if(!flag)return -1;
            bid=bookDao.queryBook(book);
            book=bookDao.queryBookByBid(bid);
            for(int i=0;i<book.getInnum();i++) {
                State state = new State(bid);
                stateDao.addState(state);
            }
            return book.getInnum();
        }
    }

    /**
     * 按书目下架
     * @param book
     * @return
     */
    @Override
    public boolean delStateByBook(Book book) {
        return bookDao.delBook(book.getBid())&&stateDao.delStateByBid(book.getBid());
    }

    /**
     * 单本下架
     * @param sid
     * @return
     */
    @Override
    public boolean delState(int sid) {
        State state=stateDao.queryBySid(sid);
        if (state.getInlib()==1) return false;//只能删除在馆的书
        Book book=bookDao.queryBookByBid(state.getBid());
        book.setInnum(book.getInnum()-1);
        return bookDao.updateBook(book)&&stateDao.delState(sid);
    }

    /**
     * 更新书本信息
     * @param state
     * @return
     */
    @Override
    public boolean updateState(State state) {
        return stateDao.updateState(state);
    }

    /**
     * 更新书目信息
     * @param book
     * @return
     */
    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    /**
     * 借书
     * @param bid
     * @param uid
     * @return
     */
    @Override
    public boolean rentBook(int bid,int uid){
        //书本表
        List<State> states=stateDao.queryByBid(bid);
        State state=null;
        //第一本没损坏的还没有借出去的书
        for(int i=0;i<states.size();i++){
            if(states.get(i).getState()==1)continue;
            if(states.get(i).getInlib()==0){
                state=states.get(i);
                break;
            }
        }
        state.setInlib(1);
        boolean f1=updateState(state);
        if(!f1)return false;
        //书目表
        Book book=bookDao.queryBookByBid(state.getBid());
        book.setInnum(book.getInnum()-1);
        book.setOutnum(book.getOutnum()+1);
        book.setTimes(book.getTimes()+1);
        boolean f2=bookDao.updateBook(book);
        if(!f2)return false;
        //记录表
        String rentdate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Record record=new Record(uid,state.getSid(),state.getBid(),rentdate);
        boolean f3=recordDao.addRecord(record);
        if(!f3)return false;
        //用户表
        User user=userDao.queryUser(uid);
        user.setRentnum(user.getRentnum()+1);
        boolean f4=userDao.updateUser(user);
        if(!f4)return false;
        return true;
    }

    /**
     * 续借
     * @param rid
     * @return
     */
    @Override
    public boolean renew(int rid) {
        Record record=recordDao.queryRecordByRid(rid);
        record.setRenew(1);
        return recordDao.updateRecord(record);
    }

    /**
     * 还书
     * @param rid
     * @param backstate
     * @return
     */
    @Override
    public boolean returnBook(int rid,int backstate,int reserved) {
        //记录表
        Record record=recordDao.queryRecordByRid(rid);
        if(record==null||record.getBacktime()!=null)return false;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date bdate=new Date();
        String backdate=sdf.format(bdate);
        record.setBacktime(backdate);
        record.setBackstate(backstate);
        boolean f1=recordDao.updateRecord(record);
        if(!f1)return false;
        //用户表
        int uid=record.getUid();
        User user=userDao.queryUser(uid);
        user.setRentnum(user.getRentnum()-1);
        userDao.updateUser(user);
        Calendar calendar=Calendar.getInstance();
        String rentdate=record.getOuttime();
        Date rdate=sdf.parse(rentdate,new ParsePosition(0));
        calendar.setTime(rdate);
        long rt=calendar.getTimeInMillis();
        calendar.setTime(bdate);
        long bt=calendar.getTimeInMillis();
        long day=24*60*60*1000;
        if(reserved==0&&(bt-rt)/day>30){
                boolean f2=userDao.updateUser(userBiz.minPoint(uid));
                if(!f2)return false;
        }else if((bt-rt)/day>60){
            boolean f2=userDao.updateUser(userBiz.minPoint(uid));
            if(!f2)return false;
        }
        if(backstate==1){
            boolean f2=userDao.updateUser(userBiz.minPoint(uid));
            if(!f2)return false;
        }
        //书本表
        int sid=record.getSid();
        State state=stateDao.queryBySid(sid);
        state.setInlib(0);
        state.setState(backstate);
        boolean f2=stateDao.updateState(state);
        if(!f2)return false;
        //书目表
        int bid=record.getBid();
        Book book=bookDao.queryBookByBid(bid);
        book.setInnum(book.getInnum()+1);
        book.setOutnum(book.getOutnum()-1);
        boolean f3=bookDao.updateBook(book);
        if(!f3) return false;
        return true;
    }
}
