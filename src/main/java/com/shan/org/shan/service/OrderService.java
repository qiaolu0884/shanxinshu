package com.shan.org.shan.service;



import java.text.ParseException;
import java.util.List;

import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderT;

public interface OrderService {

	List<Order> selcetByDataandStatus(OrderT order) throws Exception;
	
    OrderT sumData(OrderT order) throws ParseException;
    
    /**
     * 根据时间查询所有交易额
     * @param createTime
     * @return
     */
    List<Order> queryToDayCount(Long beginTime , Long endTime);
    
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
    
    List<Order> queryOrderzuhe(String username,String userGrade,Long status,Long orderNumber,Long start,Long end,int pagenumber ,int pageTiao);
}
