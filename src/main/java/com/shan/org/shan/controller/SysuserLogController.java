package com.shan.org.shan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.BatchDateUtil;
import com.shan.org.shan.utils.IpUtil;

@Controller
public class SysuserLogController {

	@Autowired
	SysuserlogService sysuserlogService;
	
	@Autowired
	SysuserService sysuserService;
	
	/**
	 * 操作日志页面
	 * @return
	 */
	@RequestMapping("/system/systemlog")
	public String userlogIndex(Model model,HttpServletRequest rq){
		List<Sysuserlog> userlogcount=sysuserlogService.userlogcount();
		int pagesum=(userlogcount.size()-1)/10+1;
		List<Sysuserlog> userslog=sysuserlogService.userlogpage();
		for(Sysuserlog user:userslog){
			user.setCreateDate(BatchDateUtil.getDate(user.getCreateTime()));
		}
		model.addAttribute("userslog",userslog);
		model.addAttribute("pagesum",pagesum);
		model.addAttribute("pagenumber",1);
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-查看系统操作日志");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);	
		
		return "admin/system_log";
	}
	
	/**
	 * 组合查询日志
	 * @param rq
	 * @param model
	 * @return
	 */
	@RequestMapping("/system/zuhequery")
	public String zuhequery(@Param("username") String username,@Param("name") String name,@Param("beginTime") String beginTime,@Param("endTime") String endTime,Model model,HttpServletRequest rq){
		Long start_1=null;
		Long end_1=null;
		Integer pagenumber;
		int pageTiao=10;			//每页的条数
		if(beginTime!=null && endTime!=null ){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				start_1=sdf.parse(beginTime).getTime()/1000;
				end_1=sdf.parse(endTime).getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Integer count=sysuserlogService.zuhequery(username, name, start_1, end_1, 0, 0).size();//组合查询后的总条数
		//总页数
		int pagesum=(count-1)/10+1;
		String pagenumber_1 =  rq.getParameter("pagenumber");
		if(pagenumber_1 ==null||pagenumber_1==""){
			pagenumber =1;
		}else if(Integer.parseInt(pagenumber_1) > pagesum){
			pagenumber =pagesum;
		}else{
			pagenumber=Integer.parseInt(rq.getParameter("pagenumber"));//页码			
		}
		List<Sysuserlog> sysuserslog=sysuserlogService.zuhequery(username, name,start_1, end_1,(pagenumber-1)*10,pageTiao);
		for(Sysuserlog ulog:sysuserslog){
			ulog.setCreateDate(BatchDateUtil.getDate(ulog.getCreateTime()));
		}
		model.addAttribute("userslog", sysuserslog);
		rq.setAttribute("pagenumber", pagenumber);
		rq.setAttribute("pagesum", pagesum);
		rq.setAttribute("username", username);
		rq.setAttribute("name",name);
		rq.setAttribute("beginTime",beginTime);
		rq.setAttribute("endTime",endTime);
		
		return "admin/system_log"; 
	}
}
