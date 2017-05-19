package com.shan.org.shan.dao;

import com.shan.org.shan.pojo.Card;
import com.shan.org.shan.pojo.CardQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardDao {
    int countByExample(CardQuery example);

    int deleteByExample(CardQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Card record);

    int insertSelective(Card record);

    List<Card> selectByExample(CardQuery example);

    Card selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardQuery example);

    int updateByExample(@Param("record") Card record, @Param("example") CardQuery example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
}