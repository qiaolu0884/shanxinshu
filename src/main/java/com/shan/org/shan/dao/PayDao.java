package com.shan.org.shan.dao;

import com.shan.org.shan.pojo.Pay;
import com.shan.org.shan.pojo.PayQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayDao {
    int countByExample(PayQuery example);

    int deleteByExample(PayQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Pay record);

    int insertSelective(Pay record);

    List<Pay> selectByExample(PayQuery example);

    Pay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pay record, @Param("example") PayQuery example);

    int updateByExample(@Param("record") Pay record, @Param("example") PayQuery example);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);

	List<Pay> selectByPayQuery(PayQuery query);
}