package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/23.
 */
public class RecordDaoImplement extends ActionDao implements RecordDao {
    /**
     * 添加记录
     * @param record 新记录
     * @return 添加完成true，失败false
     */
    @Override
    public boolean addRecord(Record record) {
        String sql="insert into record(uid,bid,sid,outtime) values(?,?,?,?)";
        List<Object> objects=new ArrayList<>();
        objects.add(record.getUid());
        objects.add(record.getBid());
        objects.add(record.getSid());
        objects.add(record.getOuttime());
        return update(sql,objects);
    }

    /**
     * 修改记录（还书/续借）
     * @param record
     * @return 修改完成true，失败false
     */
    @Override
    public boolean updateRecord(Record record) {
        String sql="update record set backtime=?,backstate=?,renew=? where rid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(record.getBacktime());
        objects.add(record.getBackstate());
        objects.add(record.getRenew());
        objects.add(record.getRid());
        return update(sql,objects);
    }

    /**
     * 根据uid查询记录
     * @param uid
     * @return 记录集和，没有为null；
     */
    @Override
    public List<Record> queryRecordByUid(int uid) {
        List<Record> records=new ArrayList<>();
        String sql="select * from record where uid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(uid);
        try {
            records=query(sql,objects,Record.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 查询某书记录
     * @param bid
     * @return 记录集和，没有为null；
     */
    @Override
    public List<Record> queryRecordByBid(int bid) {
        List<Record> records=new ArrayList<>();
        String sql="select * from record where bid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(bid);
        try {
            records=query(sql,objects,Record.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 查找单条记录
     * @param rid
     * @return
     */
    @Override
    public Record queryRecordByRid(int rid) {
        List<Record> records=new ArrayList<>();
        String sql="select * from record where rid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(rid);
        try {
            records=query(sql,objects,Record.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        if(records.size()>0){
            return records.get(0);
        }
        return null;
    }

    /**
     * 查询某本书记录
     * @param sid
     * @return 记录集和，没有为null；
     */
    @Override
    public List<Record> queryRecordBySid(int sid) {
        List<Record> records=new ArrayList<>();
        String sql="select uid,bid,outtime,backtime,backstate from record where sid=?";
        List<Object> objects=new ArrayList<>();
        objects.add(sid);
        try {
            records=query(sql,objects,Record.class);
        } catch (IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
        return records;
    }
}
