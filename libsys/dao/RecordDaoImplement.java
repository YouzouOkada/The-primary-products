package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by �~�i on 2018/1/23.
 */
public class RecordDaoImplement extends ActionDao implements RecordDao {
    /**
     * ��Ӽ�¼
     * @param record �¼�¼
     * @return ������true��ʧ��false
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
     * �޸ļ�¼������/���裩
     * @param record
     * @return �޸����true��ʧ��false
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
     * ����uid��ѯ��¼
     * @param uid
     * @return ��¼���ͣ�û��Ϊnull��
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
     * ��ѯĳ���¼
     * @param bid
     * @return ��¼���ͣ�û��Ϊnull��
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
     * ���ҵ�����¼
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
     * ��ѯĳ�����¼
     * @param sid
     * @return ��¼���ͣ�û��Ϊnull��
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
