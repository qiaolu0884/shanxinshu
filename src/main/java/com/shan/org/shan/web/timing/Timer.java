package com.shan.org.shan.web.timing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shan.org.shan.dao.OrderDao;
import com.shan.org.shan.dao.PayDao;
import com.shan.org.shan.dao.TransactionDao;
import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderQuery;
import com.shan.org.shan.pojo.Pay;
import com.shan.org.shan.pojo.PayQuery;
import com.shan.org.shan.pojo.Transaction;
import com.shan.org.shan.utils.BatchDateUtil;

@Component
public class Timer {

	@Autowired
	private PayDao payDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private TransactionDao transactionDao;
	
	/**
	 * 前十五天交易额统计
	 * 单位：万元
	 */
	@Scheduled(cron = "0 0 0 * * ?")
//	@Scheduled(fixedDelay = 50000)
	public void countPayByDay() {
		System.out.println("meitian");
		long begin = new Date().getTime();
		Long[] days = BatchDateUtil.getDays(new Date());
		//根据时间区间去查询当天的订单
		BigDecimal bigDecimal = new BigDecimal(0);
		PayQuery query = new PayQuery();
		query.setBegin(days[1]);
		query.setEnd(days[0]);
		List<Pay> pays = payDao.selectByPayQuery(query);
		//根据当天的订单去查询每个订单的交易额
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
		transaction.setTime(BatchDateUtil.getFormatDay((BatchDateUtil.getDate(days[1]))));
		transaction.setTotalMoney(bigDecimal.longValue());
		transaction.setType(0);
		transactionDao.insertSelective(transaction);
		long end = new Date().getTime();
		System.out.println(end-begin);
	}
	
	/**
	 * 更新记录前一天的交易额
	 * 单位：万元
	 */
	@Scheduled(cron = "0 0 0 1 * ?")
//	@Scheduled(fixedDelay = 50000)
	public void countPayByMonth() {
		System.out.println("yuefen");
		long begin = new Date().getTime();
		Long[] months = BatchDateUtil.getMonths(new Date());
		//根据时间区间去查询当月的订单
		BigDecimal bigDecimal = new BigDecimal(0);
		PayQuery query = new PayQuery();
		query.setBegin(months[1]);
		query.setEnd(months[0]);
		List<Pay> pays = payDao.selectByPayQuery(query);
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
		transaction.setTime(BatchDateUtil.getFormatMonth((BatchDateUtil.getDate(months[1]))));
		transaction.setTotalMoney(bigDecimal.longValue());
		transaction.setType(1);
		transactionDao.insertSelective(transaction);
		long end = new Date().getTime();
		System.out.println(end-begin+"===");
	}
	/*@Scheduled(cron = "0 0 0 * * ?")
	public void days(){
		System.out.println("days");
	}
	@Scheduled(cron = "0 0 0 1 * ?")
	public void months(){
		System.out.println("months");
	}*/
}
