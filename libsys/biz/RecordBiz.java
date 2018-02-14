package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Record;

import java.util.List;

/**
 * Created by 葉蔵 on 2018/1/24.
 */
public interface RecordBiz {
    public Record queryRecordByRid(int rid);//查找单条记录
    public List<Record> queryRecordByUid(int uid);//根据用户查找
    public List<Record> queryRecordByBid(int bid);//按书
    public List<Record> queryRecordBySid(int sid);//按书本id查(管理员)
}
