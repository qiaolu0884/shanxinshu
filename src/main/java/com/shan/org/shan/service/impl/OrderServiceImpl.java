package com.shan.org.shan.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.OrderDao;
import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderT;
import com.shan.org.shan.service.OrderService;
import com.shan.org.shan.utils.DateUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Override
	public List<Order> selcetByDataandStatus(OrderT order) throws Exception {
		// TODO Auto-generated method stub
		if(order==null){
			return null;
		}
		if(order.getDateType()!=null) {
			if(Integer.valueOf(order.getDateType())==-30){
				order.setBeginTime(DateUtil.getBeginDateLong(Integer.valueOf(order.getDateType())));
				order.setEndTime(DateUtil.getEndDateLong(0));
			}else{
				order.setBeginTime(DateUtil.getBeginDateLong(Integer.valueOf(order.getDateType())));
				order.setEndTime(DateUtil.getEndDateLong(Integer.valueOf(order.getDateType())));
			}
		}else{
			order.setBeginTime(DateUtil.getDateLong(order.getBeginDate()));
			order.setEndTime(DateUtil.getDateLong(order.getEndDate()));
		}
		order.setBeginDate(DateUtil.getDateTimeStr(order.getBeginTime()));
		order.setEndDate(DateUtil.getDateTimeStr(order.getEndTime()));
		List<Order> list = orderDao.selcetByDataandStatus(order);
		return list;
	}
	@Override
	public OrderT sumData(OrderT order) throws ParseException {
		if(order==null){
			return null;
		}
		if(order.getDateType()!=null){
			if(Integer.valueOf(order.getDateType())==-30){
				order.setBeginTime(DateUtil.getBeginDateLong(Integer.valueOf(order.getDateType())));
				order.setEndTime(DateUtil.getEndDateLong(0));
			}else{
				order.setBeginTime(DateUtil.getBeginDateLong(Integer.valueOf(order.getDateType())));
				order.setEndTime(DateUtil.getEndDateLong(Integer.valueOf(order.getDateType())));
			}
		}else{
//			order.setBeginTime(DateUtil.getDateLong(order.getBeginDate()));
//			order.setEndTime(DateUtil.getDateLong(order.getEndDate()));
			//TODO
		}
		return orderDao.sumData(order);
	}
	/**
	 * 查询所有交易
	 */
	@Override
	public List<Order> queryOrderList() {
		// TODO Auto-generated method stub
		return orderDao.queryOrderList();
	}
	/**
	 * 查询所有交易数量
	 */
	@Override
	public List<Order> queryOrderCount() {
		// TODO Auto-generated method stub
		return orderDao.queryOrderCount();
	}
	@Override
	public List<Order> queryOrderzuhe(String username, String userGrade, Long status, Long orderNumber,
			Long beginTime, Long endTime, int pagenumber, int pageTiao) {
		// TODO Auto-generated method stub
		return orderDao.queryOrderzuhe(username, userGrade, status, orderNumber, beginTime, endTime, pagenumber, pageTiao);
	}
	@Override
	public List<Order> queryToDayCount(Long beginTime, Long endTime) {
		// TODO Auto-generated method stub
		return orderDao.queryToDayCount(beginTime, endTime);
	}

}
