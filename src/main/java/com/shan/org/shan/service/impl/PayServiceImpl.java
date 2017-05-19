package com.shan.org.shan.service.impl;


import static org.junit.Assert.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shan.org.shan.dao.OrderDao;
import com.shan.org.shan.dao.PayDao;
import com.shan.org.shan.dao.TransactionDao;
import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderQuery;
import com.shan.org.shan.pojo.Pay;
import com.shan.org.shan.pojo.PayQuery;
import com.shan.org.shan.pojo.Transaction;
import com.shan.org.shan.pojo.TransactionQuery;
import com.shan.org.shan.service.PayService;
import com.shan.org.shan.utils.BatchDateUtil;


@Service
@Transactional
public class PayServiceImpl implements PayService{

	@Autowired
	private PayDao payDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public void insertOrder(Order order) {
		orderDao.insertSelective(order);
	}

	@Override
	public void insertPay(Pay pay) {
		payDao.insertSelective(pay);
	}

	
	/**
	 * 前十二个月交易额统计
	 * 单位：万元
	 */
	@Override
	public List<BigDecimal> countPayByDay(Date date) {
		long begin = date.getTime();
		//返回数据格式为集合
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		Long[] days = BatchDateUtil.getDays(new Date());
		//根据时间区间去查询当天的订单
		for (int i = 1; i < days.length ; i++) {
			BigDecimal bigDecimal = new BigDecimal(0);
			TransactionQuery query = new TransactionQuery();
			Date date2 = BatchDateUtil.getDate(days[i]);
			Long formatDay = BatchDateUtil.getFormatDay(date2);
			query.createCriteria().andTimeEqualTo(formatDay);
			List<Transaction> trans = transactionDao.selectByExample(query);
			//如果记录表没有，就去联查，然后存入记录表
			if(trans == null || trans.size() == 0){
				PayQuery pquery = new PayQuery();
				pquery.setBegin(days[i]);
				pquery.setEnd(days[i-1]);
				List<Pay> pays = payDao.selectByPayQuery(pquery);
				//根据当月的订单去查询每个订单的交易额
				for (Pay pay : pays) {
					OrderQuery oquery = new OrderQuery();
					oquery.createCriteria().andIdEqualTo(pay.getOrderId());
					List<Order> olist = orderDao.selectByExample(oquery);
					for (Order order : olist) {
						bigDecimal = bigDecimal.add(order.getMoney());
					}
				}
				Transaction transaction = new Transaction();
				transaction.setCreateTime(BatchDateUtil.getBatch(new Date()));
				transaction.setTime(BatchDateUtil.getFormatDay((BatchDateUtil.getDate(days[i]))));
				transaction.setTotalMoney(bigDecimal.longValue());
				transaction.setType(0);
				transactionDao.insertSelective(transaction);
				//插入之后再去查找，是完整的数据
				trans = transactionDao.selectByExample(query);
			}
			bigDecimal = bigDecimal.add(new BigDecimal(trans.get(0).getTotalMoney()));
			list.add(bigDecimal.divide(new BigDecimal(10000)));
		}
		long end = new Date().getTime();
		System.out.println(end-begin);
		List<BigDecimal> dList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			dList.add(list.get(list.size()-i-1));
		}
		return dList;
	}

	/**
	 * 更新前一个月的交易额
	 * 单位：万元
	 */
	@Override
	public List<BigDecimal> countPayByMonth(Date date) {
		//返回数据格式为集合
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		Long[] days = BatchDateUtil.getMonths(new Date());//13个日期集合，从现在往前数
		//根据时间区间去查询当月的订单
		for (int i = 1; i < days.length; i++) {
			BigDecimal bigDecimal = new BigDecimal(0);
			TransactionQuery query = new TransactionQuery();
			Date date2 = BatchDateUtil.getDate(days[i]);
			Long formatDay = BatchDateUtil.getFormatMonth(date2);
			query.createCriteria().andTimeEqualTo(formatDay);
			List<Transaction> trans = transactionDao.selectByExample(query);
			if(trans == null || trans.size() == 0){
				PayQuery pquery = new PayQuery();
				pquery.setBegin(days[i]);
				pquery.setEnd(days[i-1]);
				List<Pay> pays = payDao.selectByPayQuery(pquery);
				//根据当月的订单去查询每个订单的交易额
				for (Pay pay : pays) {
					OrderQuery oquery = new OrderQuery();
					oquery.createCriteria().andIdEqualTo(pay.getOrderId());
					List<Order> olist = orderDao.selectByExample(oquery);
					for (Order order : olist) {
						bigDecimal = bigDecimal.add(order.getMoney());
					}
				}
				Transaction transaction = new Transaction();
				transaction.setCreateTime(BatchDateUtil.getBatch(new Date()));
				transaction.setTime(BatchDateUtil.getFormatMonth((BatchDateUtil.getDate(days[i]))));
				transaction.setTotalMoney(bigDecimal.longValue());
				transaction.setType(1);
				transactionDao.insertSelective(transaction);
				//插入之后再去查找，是完整的数据
				trans = transactionDao.selectByExample(query);
			}
			bigDecimal = bigDecimal.add(new BigDecimal(trans.get(0).getTotalMoney()));
			list.add(bigDecimal.divide(new BigDecimal(10000)));
		}
		List<BigDecimal> dList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			dList.add(list.get(list.size()-i-1));
		}
		return dList;
	}
	
}
