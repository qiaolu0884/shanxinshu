package com.shan.org.shan.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.Pay;

public interface PayService {

	void insertOrder(Order order);

	void insertPay(Pay pay);

	List<BigDecimal> countPayByMonth(Date date);

	List<BigDecimal> countPayByDay(Date date);

}
