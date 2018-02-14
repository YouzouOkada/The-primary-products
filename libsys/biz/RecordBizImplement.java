package com.youzou.libsys.biz;

import com.youzou.libsys.dao.RecordDao;
import com.youzou.libsys.dao.RecordDaoImplement;
import com.youzou.libsys.entity.Record;

import java.util.List;

/**
 * Created by È~Êi on 2018/1/24.
 */
public class RecordBizImplement implements RecordBiz {
    private RecordDao recordDao=new RecordDaoImplement();

    @Override
    public Record queryRecordByRid(int rid) {
        return recordDao.queryRecordByRid(rid);
    }

    @Override
    public List<Record> queryRecordByUid(int uid) {
        return recordDao.queryRecordByUid(uid);
    }

    @Override
    public List<Record> queryRecordByBid(int bid) {
        return recordDao.queryRecordByBid(bid);
    }

    @Override
    public List<Record> queryRecordBySid(int sid) {
        return recordDao.queryRecordBySid(sid);
    }
}
