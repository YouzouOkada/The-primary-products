package com.youzou.libsys.biz;

import com.youzou.libsys.entity.Record;

import java.util.List;

/**
 * Created by �~�i on 2018/1/24.
 */
public interface RecordBiz {
    public Record queryRecordByRid(int rid);//���ҵ�����¼
    public List<Record> queryRecordByUid(int uid);//�����û�����
    public List<Record> queryRecordByBid(int bid);//����
    public List<Record> queryRecordBySid(int sid);//���鱾id��(����Ա)
}
