package com.shan.org.shan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shan.org.shan.pojo.sys.Sysgroup;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysgroupService;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.BatchDateUtil;
import com.shan.org.shan.utils.IpUtil;

@Controller
public class SysuserController {

	private Long id=null;
	
	@Autowired
	private SysuserService sysuserService;
	
	@Autowired
	SysgroupService sysgroupService;
	
	@Autowired
	SysuserlogService sysuserlogService;
	
	IpUtil ipUtil;
	
	BatchDateUtil batchDateUtil;
	
	/**
	 * 系统设置页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/system/systemSettings")
	public String sysUserList(Model model,HttpServletRequest rq){
		//用户
		List<Sysuser> sysusers=sysuserService.findSysuserList();
		//总页数
		int pagesum = (sysusers.size()-1)/10 +1;
		List<Sysuser> sysuserpage=sysuserService.querySysuserPage();
		for(Sysuser user:sysuserpage){
			user.setCreateDate(BatchDateUtil.getDate(user.getCreateTime()));
		}
		
		model.addAttribute("sysusers", sysuserpage);
		//用户群组
		List<Sysgroup> groups=sysgroupService.groupList();
		model.addAttribute("groups", groups);
		model.addAttribute("pagenumber","1");
		model.addAttribute("pagesum",pagesum);
		
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username);//用户名（账号）
		userlog.setMessage("系统设置-查看用户");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		return "admin/system_set";
	}
	
	/**
	 * AJAX验证
	 * @param username
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/jquery")
	public void jquery(String username,HttpServletRequest re,HttpServletResponse res) throws IOException{
		PrintWriter pw= res.getWriter();
		Sysuser user=sysuserService.findSysuserByName(username);
		System.out.println("query:"+username);
		if(user == null){
			pw.write("yes");
		}else{
			pw.write("no");
		}
	}
	
	/**
	 * 启用
	 * @param status
	 * @param uid
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/changeQY")
	public String changeQY(HttpServletRequest rq,Long status,Long uid,HttpServletRequest re,HttpServletResponse res) throws IOException{
		//PrintWriter pw= res.getWriter();
		Sysuser user_1=new Sysuser();
		user_1.setStatus(1l);
		user_1.setSysId(uid);
		
		sysuserService.updateByPrimaryKeySelective(user_1);
		
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户状态为启用");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/systemSettings";
	}
	
	/**
	 * 停用
	 * @param status
	 * @param uid
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/changeTY")
	public String changeTY(HttpServletRequest rq,Long status,Long uid,HttpServletRequest re,HttpServletResponse res) throws IOException{
		//PrintWriter pw= res.getWriter();
		Sysuser user_1=new Sysuser();
		user_1.setStatus(0l);
		user_1.setSysId(uid);
		
		sysuserService.updateByPrimaryKeySelective(user_1);
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户状态为停用");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/systemSettings";
	}
	
	/**
	 * 添加后台用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/system/addSysuser")
	public String addSysuser(Sysuser user,HttpServletRequest rq){
		//添加用户
		user.setCreater((String)rq.getSession().getAttribute("loginUser"));
		sysuserService.addSysuser(user);
		
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-添加新用户");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/systemSettings";
	}
	

	/**
	 * 根据ID查询用户(修改之前先查询出该用户信息)
	 * @param sysId
	 * @return
	 */
/*	@RequestMapping("updateBefore/{sysId}")
	public String updateBefore(@PathVariable Long sysId,Model model){
		Sysuser user=sysuserService.selectByPrimaryKey(sysId);
		model.addAttribute("user",user);
		id=user.getSysgroupId().getGid();
		return "admin/system_set";
	}*/
	
	/**
	 * 修改信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/system/updatemessage")
	public String updatemessage(Sysuser user,HttpServletRequest rq){
		sysuserService.updateByPrimaryKeySelective(user);
		
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户信息");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/systemSettings";
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	@RequestMapping("/system/update")
	public String updateUser(Sysuser user,HttpServletRequest rq){

		sysuserService.updateByPrimaryKeySelective(user);
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户密码");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/systemSettings";

	}
	
	/**
	 * 组合查询
	 * @param username
	 * @param name
	 * @param status
	 * @param beginTime
	 * @param endTime
	 * @param group
	 * @param model
	 * @return
	 */
	@RequestMapping("/system/combinedQuery")
	public String combinedQuery(@Param("username") String username,@Param("name") String name,@Param("status") Long status,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("group") Long group,Model model,HttpServletRequest req){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//总条数
		count=sysuserService.combinedQuery(username, name,status, start, end,group,0,0).size();
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
		System.out.println("zongyeshu:"+count +"  ye:"+ pagenumber+"  group:"+group);
		//用户
		List<Sysuser> sysusers=sysuserService.combinedQuery(username, name,status, start, end,group,(pagenumber-1)*10,pageTiao);
		for(Sysuser user:sysusers){
			user.setCreateDate(BatchDateUtil.getDate(user.getCreateTime()));
		}
		model.addAttribute("sysusers", sysusers);
		req.setAttribute("pagenumber", pagenumber);
		req.setAttribute("pagesum", pagesum);
		req.setAttribute("status", status);
		req.setAttribute("group_num",group);
		req.setAttribute("username", username);
		req.setAttribute("name",name);
		req.setAttribute("beginTime",beginTime);
		req.setAttribute("endTime",endTime);
		//用户群组
		List<Sysgroup> groups=sysgroupService.groupList();
		model.addAttribute("groups", groups);
		
		//添加操作日志
		String username_1=(String)req.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-查询用户");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username_1);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "admin/system_set";
	}
	
	@RequestMapping(value = "/system/findGroupById")
	public @ResponseBody
	String findGroupById(Long id,Model model){
		Sysgroup sysgroup = sysgroupService.selectByPrimaryKey(id);
		model.addAttribute("currentGroup", sysgroup);
		return null;
	}
	
}
