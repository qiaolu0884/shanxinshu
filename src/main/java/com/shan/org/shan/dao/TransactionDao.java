package com.shan.org.shan.dao;

import com.shan.org.shan.pojo.Transaction;
import com.shan.org.shan.pojo.TransactionQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransactionDao {
    int countByExample(TransactionQuery example);

    int deleteByExample(TransactionQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    List<Transaction> selectByExample(TransactionQuery example);

    Transaction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Transaction record, @Param("example") TransactionQuery example);

    int updateByExample(@Param("record") Transaction record, @Param("example") TransactionQuery example);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
}