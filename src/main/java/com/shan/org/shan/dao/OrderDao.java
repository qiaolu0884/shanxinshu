package com.shan.org.shan.dao;

import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderQuery;
import com.shan.org.shan.pojo.OrderT;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {
    int countByExample(OrderQuery example);

    int deleteByExample(OrderQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderQuery example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selcetByDataandStatus(OrderT order);
    
    /**
     * 根据时间查询所有交易额
     * @param createTime
     * @return
     */
    List<Order> queryToDayCount(@Param("beginTime") Long beginTime ,@Param("endTime") Long endTime);
   
    OrderT sumData(OrderT order);
    
    /**
     * 查询所有交易
     * @return
     */
    List<Order> queryOrderList();
    
    /**
     * 查询所有交易的数量
     * @return
     */
    List<Order> queryOrderCount();
    
    /**
     * 组合查询
     * @param username
     * @param userGrade
     * @param status
     * @param orderNumber
     * @param beginTime
     * @param endTime
     * @param pagenumber
     * @param pageTiao
     * @return
     */
    List<Order> queryOrderzuhe(@Param("username") String username,@Param("userGrade") String userGrade,@Param("status") Long status,@Param("orderNumber") Long orderNumber,@Param("beginTime") Long beginTime,@Param("endTime") Long endTime,@Param("pagenumber") int pagenumber ,@Param("pageTiao") int pageTiao);
    
    
    
}