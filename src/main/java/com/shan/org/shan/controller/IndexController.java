package com.shan.org.shan.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.Pay;
import com.shan.org.shan.service.PayService;
import com.shan.org.shan.utils.BatchDateUtil;

@Controller
public class IndexController {

	@Autowired
	private PayService payService;

	@RequestMapping(value = "/index/days")
	public @ResponseBody
	List<BigDecimal> countPayByDay() {
		List<BigDecimal> list = payService.countPayByDay(new Date());
		return list;
	}

	@RequestMapping(value = "/index/months")
	public @ResponseBody
	List<BigDecimal> countPayByMonth() {
		List<BigDecimal> list = payService.countPayByMonth(new Date());
		return list;
	}
	@RequestMapping(value = "/index")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping(value = "/index/getMonth")
	public @ResponseBody
	String[] getMonth(){
		return BatchDateUtil.getMonthForJSP(new Date());
	}
	
}
