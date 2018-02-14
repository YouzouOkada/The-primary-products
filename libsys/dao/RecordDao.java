package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Record;

import java.util.List;

/**
 * Created by �~�i on 2018/1/22.
 */
public interface RecordDao {
    public boolean addRecord(Record record);//��Ӽ�¼
    public boolean updateRecord(Record record);//�޸ļ�¼
    public Record queryRecordByRid(int rid);//���ҵ�����¼
    public List<Record> queryRecordByUid(int uid);//�����û�����
    public List<Record> queryRecordByBid(int bid);//����
    public List<Record> queryRecordBySid(int sid);//���鱾id��(����Ա)
}
