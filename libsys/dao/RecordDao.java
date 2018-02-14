package com.youzou.libsys.dao;

import com.youzou.libsys.entity.Record;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/22.
 */
public interface RecordDao {
    public boolean addRecord(Record record);//添加记录
    public boolean updateRecord(Record record);//修改记录
    public Record queryRecordByRid(int rid);//查找单条记录
    public List<Record> queryRecordByUid(int uid);//根据用户查找
    public List<Record> queryRecordByBid(int bid);//按书
    public List<Record> queryRecordBySid(int sid);//按书本id查(管理员)
}
