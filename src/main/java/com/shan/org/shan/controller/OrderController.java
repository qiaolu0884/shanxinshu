package com.shan.org.shan.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shan.org.shan.pojo.Download;
import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderT;
import com.shan.org.shan.pojo.User;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.DownloadService;
import com.shan.org.shan.service.OrderService;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.BatchDateUtil;
import com.shan.org.shan.utils.ExportExcel;
import com.shan.org.shan.utils.IpUtil;


@Controller	
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired 
	private DownloadService downloadService;
	
	@Autowired
	SysuserService sysuserService;
	
	@Autowired
	SysuserlogService sysuserlogService;
	
	BatchDateUtil batchDateUtil;
	
	@RequestMapping("/order/liushui")
	@ResponseBody
	public List<Order> selcetByDataandStatus(OrderT ordert,Model model) throws Exception{
		/*ordert.setBeginTime(1494345600000l);
		ordert.setEndTime(3494345600000l);
		ordert.setStatus(1l);*/
		
		List<Order> orders= orderService.selcetByDataandStatus(ordert);
		model.addAttribute("orders",orders);
		return orders;
	}
	
	@RequestMapping("/order")
	@ResponseBody
	public OrderT sumData(OrderT order) throws ParseException {
		OrderT orderT = orderService.sumData(order);
		return orderT;
	}
	
	/**
	 * 交易管理首页查询当天的数据
	 * @param beginTime
	 * @param endTime
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/trade_management")
	public String queryToDay(@Param("begtinTime") Long beginTime,@Param("endTime") Long endTime,Model model,HttpServletRequest request){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		beginTime=BatchDateUtil.getBatch(BatchDateUtil.getDayBegin());
		//当前系统时间
		endTime=BatchDateUtil.getBatch(new Date());
		List<Order> orders = orderService.queryToDayCount(beginTime, endTime);
		int shanzhongzi=0;
		int shanxinbi=0;
		int money=0;
		int sxbmoney=0;
		int szzmoney=0;
		
		for(Order o:orders){
			if(o.getStatus()==1 && o.getCommodityId()==1){
				shanxinbi+=o.getOrderCount();
				sxbmoney=shanxinbi*100;
			}else if(o.getStatus()==1 && o.getCommodityId()==2){
				shanzhongzi+=o.getOrderCount();
				szzmoney=shanzhongzi*300;
			}else{
				shanxinbi=0;
				shanzhongzi=0;
				money=0;
			}
		}
		money=sxbmoney+szzmoney;
		model.addAttribute("shanzhongzi",shanzhongzi);
		model.addAttribute("shanxinbi",shanxinbi);
		model.addAttribute("money",money);
		
		//添加日志
		String username_1=(String)request.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username_1);//用户名（账号）
		userlog.setMessage("交易管理-查询交易汇总");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		return "admin/trade_management";
	}
	
	/**
	 * 昨天
	 * @param model
	 * @return
	 */
	@RequestMapping("yesTerDay")
	public String yesTerDay(Model model){
		Long beginTime=BatchDateUtil.getzuotian(new Date());
		Long endTime=BatchDateUtil.getBatch(BatchDateUtil.getDayBegin());
		List<Order> orders = orderService.queryToDayCount(beginTime, endTime);
		int shanzhongzi=0;
		int shanxinbi=0;
		int money=0;
		int sxbmoney=0;
		int szzmoney=0;
		
		for(Order o:orders){
			if(o.getStatus()==1 && o.getCommodityId()==1){
				shanxinbi+=o.getOrderCount();
				sxbmoney=shanxinbi*100;
			}else if(o.getStatus()==1 && o.getCommodityId()==2){
				shanzhongzi+=o.getOrderCount();
				szzmoney=shanzhongzi*300;
			}else{
				shanxinbi=0;
				shanzhongzi=0;
				money=0;
			}
		}
		money=sxbmoney+szzmoney;
		model.addAttribute("shanzhongzi",shanzhongzi);
		model.addAttribute("shanxinbi",shanxinbi);
		model.addAttribute("money",money);
		return "admin/trade_management";
	}
	
	@RequestMapping("/qiantian")
	public String getqiantian(Model model){
		Long beginTime=BatchDateUtil.getqiantian(new Date());
		Long endTime=BatchDateUtil.getzuotian(new Date());
		List<Order> orders = orderService.queryToDayCount(beginTime, endTime);
		int shanzhongzi=0;
		int shanxinbi=0;
		int money=0;
		int sxbmoney=0;
		int szzmoney=0;
		
		for(Order o:orders){
			if(o.getStatus()==1 && o.getCommodityId()==1){
				shanxinbi+=o.getOrderCount();
				sxbmoney=shanxinbi*100;
			}else if(o.getStatus()==1 && o.getCommodityId()==2){
				shanzhongzi+=o.getOrderCount();
				szzmoney=shanzhongzi*300;
			}else{
				shanxinbi=0;
				shanzhongzi=0;
				money=0;
			}
		}
		money=sxbmoney+szzmoney;
		model.addAttribute("shanzhongzi",shanzhongzi);
		model.addAttribute("shanxinbi",shanxinbi);
		model.addAttribute("money",money);
		return "admin/trade_management";
	}
	/**
	 * 自定义时间查询
	 * @param model
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("timequery")
	public String getTimequery(Model model,@Param("beginTime") String beginTime, @Param("endTime") String endTime){
		Long start=null;
		Long end=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(beginTime != null && endTime != null){
			try {
				start=sdf.parse(beginTime).getTime()/1000;
				end=sdf.parse(endTime).getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Order> orders = orderService.queryToDayCount(start,end);
		int shanzhongzi=0;
		int shanxinbi=0;
		int money=0;
		int sxbmoney=0;
		int szzmoney=0;
		
		for(Order o:orders){
			if(o.getStatus()==1 && o.getCommodityId()==1){
				shanxinbi+=o.getOrderCount();
				sxbmoney=shanxinbi*100;
			}else if(o.getStatus()==1 && o.getCommodityId()==2){
				shanzhongzi+=o.getOrderCount();
				szzmoney=shanzhongzi*300;
			}else{
				shanxinbi=0;
				shanzhongzi=0;
				money=0;
			}
		}
		money=sxbmoney+szzmoney;
		model.addAttribute("shanzhongzi",shanzhongzi);
		model.addAttribute("shanxinbi",shanxinbi);
		model.addAttribute("money",money);
		model.addAttribute("beginTime",beginTime);
		model.addAttribute("endTime",endTime);
		return "admin/trade_management";
	}
	
	/**
	 * 查询30天的交易数据汇总
	 * @param model
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/onefiveDay")
	public String queryonefiveDay(Model model){
		Long beginTime=BatchDateUtil.get30Days(new Date());	//当前时间的前30天0点
		Long endTime=BatchDateUtil.getBatch(BatchDateUtil.getDayBegin());
		List<Order> orders = orderService.queryToDayCount(beginTime, endTime);
		int shanzhongzi=0;
		int shanxinbi=0;
		int money=0;
		int sxbmoney=0;
		int szzmoney=0;
		
		for(Order o:orders){
			if(o.getStatus()==1 && o.getCommodityId()==1){
				shanxinbi+=o.getOrderCount();
				sxbmoney=shanxinbi*100;
			}else if(o.getStatus()==1 && o.getCommodityId()==2){
				shanzhongzi+=o.getOrderCount();
				szzmoney=shanzhongzi*300;
			}else{
				shanxinbi=0;
				shanzhongzi=0;
				money=0;
			}
		}
		money=sxbmoney+szzmoney;
		model.addAttribute("shanzhongzi",shanzhongzi);
		model.addAttribute("shanxinbi",shanxinbi);
		model.addAttribute("money",money);
		return "admin/trade_management";
	}
	
	/**
	 * 交易流水组合查询
	 * @param username
	 * @param userGrade
	 * @param status
	 * @param orderNumber
	 * @param BeginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/order/goquery")
	public String goquery(HttpServletRequest req,Model model,@Param("username") String username,@Param("userGrade") String userGrade,@Param("status") Long status,@Param("orderNumber") Long orderNumber,@Param("beginTime") String beginTime,@Param("endTime") String endTime){
		Long start=null;
		Long end=null;
		Integer pagenumber;			//当前页码
		int count;				//总条数
		int pagesum;			//总页数
		int pageTiao=10;			//每页的条数
		
		//如果开始日和结束日不为空，就把时间格式转为时间戳
		if(beginTime!=null && endTime!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				start=sdf.parse(beginTime).getTime()/1000;
				end=sdf.parse(endTime).getTime()/1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//得到总条数
		count=orderService.queryOrderzuhe(username, userGrade, status, orderNumber, start, end,0,0).size();
		//总页数
		pagesum=(count-1)/10+1;
		String pagenumber_1 =  req.getParameter("pagenumber");
		if(pagenumber_1 ==null||pagenumber_1==""){
			pagenumber =1;
		}else if(Integer.parseInt(pagenumber_1) > pagesum){
			pagenumber =pagesum;
		}else{
			pagenumber=Integer.parseInt(req.getParameter("pagenumber"));//页码			
		}
		
		List<Order> orders=orderService.queryOrderzuhe(username, userGrade, status, orderNumber, start, end, (pagenumber-1)*10, pageTiao);
		for(Order order:orders){
			order.setCreateDate(BatchDateUtil.getDate(order.getCreateTime()));
		}
		
		model.addAttribute("orders",orders);
		req.setAttribute("pagenumber", pagenumber);
		req.setAttribute("pagesum", pagesum);
		req.setAttribute("status", status);
		req.setAttribute("userGrade",userGrade);
		req.setAttribute("username", username);
		req.setAttribute("orderNumber",orderNumber);
		req.setAttribute("beginTime",beginTime);
		req.setAttribute("endTime",endTime);
		//添加操作日志
		String username_1=(String)req.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("交易管理-查询交易流水");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username_1);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		
		return "admin/trade_liushui";
	}
	
	@RequestMapping("/order/exportlog2")
	public String selectDownload(HttpServletRequest rq,Model model,@Param("username") String username){
		Integer pagenumber;			//当前页码
		int count;				//总条数
		int pagesum;			//总页数
		int pageTiao=10;			//每页的条数
		
		
		//得到总条数
		count = downloadService.selcetByDataandStatus(username,0,0).size();
		//总页数
		pagesum=(count-1)/10+1;
		String pagenumber_1 =  rq.getParameter("pagenumber");
		if(pagenumber_1 ==null||pagenumber_1==""){
			pagenumber =1;
		}else if(Integer.parseInt(pagenumber_1) > pagesum){
			pagenumber =pagesum;
		}else{
			pagenumber=Integer.parseInt(rq.getParameter("pagenumber"));//页码			
		}
		
		List<Download> list=downloadService.selcetByDataandStatus(username,(pagenumber-1)*10,pageTiao);
		model.addAttribute("load",list);
		rq.setAttribute("username", username);
		rq.setAttribute("pagenumber",pagenumber);
		rq.setAttribute("pagesum",pagesum);
		return "admin/trade_exportlog";
	}
	
	/**
	 * 流水页面
	 * @param model
	 * @param rq
	 * @return
	 */
	@RequestMapping("/order/trade_liushui")
	public String selcetByDataandStatus(Model model,HttpServletRequest rq){
		List<Order> order=orderService.queryOrderCount();
		int pagesum=(order.size()-1)/10 +1;	//总页数
		//获取所有的交易流水
		List<Order> orders=orderService.queryOrderList();
		for(Order o:orders){
			o.setCreateDate(BatchDateUtil.getDate(o.getCreateTime()));
		}
		model.addAttribute("orders",orders);
		model.addAttribute("pagesum",pagesum);
		model.addAttribute("pagenumber",1);
		//操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username);//用户名（账号）
		userlog.setMessage("交易管理-查询交易流水");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		return "admin/trade_liushui";
	}
	
	
	
/*	@RequestMapping("/exportExcel")
	public void exportExcel(OrderT ordert, HttpServletResponse response) throws Exception {
	
		List<Order> list= orderService.selcetByDataandStatus(ordert);
		response.setContentType("application/ms-excel");
		String sheetName_ = "交易流水查询";
        String sheetName = new String(sheetName_.getBytes(),"iso8859-1");
        response.addHeader("Content-Disposition", "attachment;filename="+ sheetName + ".xls");
        OutputStream os = response.getOutputStream();
        System.out.println("daochu++++++++++++++++++++++");
        Excel.exportExcel(list, response);
        Download download  = new Download();
        download.setCount(Long.valueOf(list.size()));
        download.setCreateTime(ordert.getBeginDate()+" - "+ordert.getEndDate());
        download.setStatus(((ordert.getStatus()==null||ordert.getStatus().equals(""))?("全部"):((ordert.getStatus()==0)?("失败"):(ordert.getStatus()==1?("成功"):("已关闭")))));
        download.setSysuserId((long) 1);
        downloadService.insert(download);
        
	}*/
	
	
	
	/**
	 * 下载流水
	 * @param username
	 * @param userGrade
	 * @param taBegin
	 * @param taEnd
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/order/importOrder")
	public void importUser(@Param("username") String username,@Param("userGrade") String userGrade,@Param("status") Long status,@Param("orderNumber") Long orderNumber,@Param("beginTime") String beginTime,@Param("endTime") String endTime,HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		String[] rowName = {"交易时间","交易订单号","会员级别","会员账号","交易状态","交易金额"};
		Long start = null;
		Long end = null;
		if(beginTime !=null && endTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				start=sdf.parse(beginTime).getTime()/1000;
				end=sdf.parse(endTime).getTime()/1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int pageTiao=1000;
		String userGrade_1=null;
		List<Order> orders=orderService.queryOrderzuhe(username, userGrade_1, status, orderNumber, start, end,0,pageTiao);
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		if(orders != null && orders.size()>0){
			for (Order order : orders) {
				Object[] o = {order.getCreateTime(),order.getOrderNumber(),order.getUserGrade(),order.getUsername(),order.getStatus(),order.getMoney()};
				list.add(o);
			}
		}
		
		//添加操作日志
		String username_1=(String)request.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username_1);//用户名（账号）
		userlog.setMessage("交易流水-导出表格");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		
		//添加导出日志
		String username_2=(String)request.getSession().getAttribute("loginUser");
		Sysuser u1=sysuserService.findSysuserByName(username_2);
		Download dl=new Download();
		dl.setSysuserId(u1.getSysId());
		if(status==null){
			dl.setMessage("全部");
		}else if(status==1){
			dl.setMessage("已支付");
		}else if(status==2){
			dl.setMessage("交易关闭");
		}else{
			dl.setMessage("未支付");
		}
		dl.setCount(1);
		dl.setCreateTime(beginTime+"-"+endTime);
		
		downloadService.insert(dl);
		
		ExportExcel excel = new ExportExcel("交易流水列表"+new Date().getTime(), rowName, list, response);
		excel.export();
	}
}
