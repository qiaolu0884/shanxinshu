package com.shan.org.shan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.org.shan.pojo.sys.Sysgroup;
import com.shan.org.shan.pojo.sys.Sysprivilege;
import com.shan.org.shan.pojo.sys.SysprivilegeQuery;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysgroupService;
import com.shan.org.shan.service.SysprivilegeService;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.IpUtil;

@Controller
public class SysgroupController {

	@Autowired
	private SysgroupService sysgroupService;
	
	@Autowired
	private SysuserService sysuserService;
	
	@Autowired
	private SysuserlogService sysuserlogService;
	
	@Autowired
	private SysprivilegeService sysprivilegeService;
	
	IpUtil ipUtil;
	
	/**
	 * 添加用户群组
	 * @param group
	 * @return
	 */
	@RequestMapping("/system/addGroup")
	public String addGroup(Long[] sysp,Sysgroup group,HttpServletRequest rq){
		sysgroupService.insert(group,sysp);
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-添加新用户组");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/groupList";
	}
	/**
	 * 查询用户组
	 * @return
	 */
	@RequestMapping("/system/groupList")
	public String groupList(Model model,HttpServletRequest rq){
		List<Sysgroup> groups=sysgroupService.groupNumber();//查询总条数
		int pagesum = (groups.size()-1)/10 +1;
		List<Sysgroup> groupspage = sysgroupService.groupPage();//limit0,10
		model.addAttribute("groups", groupspage);
		model.addAttribute("pagesum",pagesum);
		model.addAttribute("pagenumber",1);
		//为了回显~~~
		//动态查询权限
		SysprivilegeQuery q1 = new SysprivilegeQuery();
		q1.createCriteria().andPidEqualTo(1l);
		List<Sysprivilege> list1 = sysprivilegeService.selectByExample(q1);
		SysprivilegeQuery q2 = new SysprivilegeQuery();
		q2.createCriteria().andPidEqualTo(2l);
		List<Sysprivilege> list2 = sysprivilegeService.selectByExample(q2);
		SysprivilegeQuery q3 = new SysprivilegeQuery();
		q3.createCriteria().andPidEqualTo(3l);
		List<Sysprivilege> list3 = sysprivilegeService.selectByExample(q3);
		SysprivilegeQuery q4 = new SysprivilegeQuery();
		q4.createCriteria().andPidEqualTo(4l);
		List<Sysprivilege> list4 = sysprivilegeService.selectByExample(q4);
		
		model.addAttribute("sysp1", list1);
		model.addAttribute("sysp2", list2);
		model.addAttribute("sysp3", list3);
		model.addAttribute("sysp4", list4);
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-查询用户组");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "admin/set_user";
	}
	
/*	*//**
	 * 根据群组ID查询该群组的用户并统计
	 * @param groupId
	 * @param model
	 * @return
	 *//*
	@RequestMapping("sysUserByGroupId/{groupId}")
	public String sysUserByGroupId(@PathVariable Long groupId , Model model){
		List<Sysuser> sysusers=sysuserService.findSysUsersByGroupId(groupId);
		int count=sysusers.size();
		model.addAttribute("sysusers",sysusers);
		model.addAttribute("count",count);
		return "group";
	}*/
	
	/**
	 * UPDATE修改群组
	 * @param group
	 * @return
	 */
	@RequestMapping("/system/updateGroup")
	public String updateGroup(Sysgroup group,HttpServletRequest rq,Long[] sysp2){
		sysgroupService.updateByPrimaryKeySelective(group,sysp2);
		
		//添加操作日志
		String username=(String)rq.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户组信息");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
		return "redirect:/system/groupList";
	}
	
	/**
	 * 启用
	 * @param status
	 * @param uid
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/changegroupQY")
	public void changeQY(Long status,Long gid,HttpServletRequest re,HttpServletResponse res) throws IOException{
//		PrintWriter pw= res.getWriter();
		Sysgroup user_1=new Sysgroup();
		user_1.setStatus(status);
		user_1.setGid(gid);
		
		sysgroupService.updateByPrimaryKeySelective(user_1);
		//添加操作日志
		String username=(String)re.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户组状态为启用");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
	}
	
	/**
	 * 停用
	 * @param status
	 * @param uid
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/changegroupTY")
	public void changeTY(Long status,Long gid,HttpServletRequest re,HttpServletResponse res) throws IOException{
//		PrintWriter pw= res.getWriter();
		Sysgroup user_1=new Sysgroup();
		user_1.setStatus(status);
		user_1.setGid(gid);
		
		sysgroupService.updateByPrimaryKeySelective(user_1);
		//添加操作日志
		String username=(String)re.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setMessage("系统设置-修改用户组状态为停用");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		userlog.setUsername(username);//用户名（账号）
		sysuserlogService.insertSelective(userlog);
	}
	
	/**
	 * AJAX验证
	 * @param groupName
	 * @param re
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/system/jquerygroupName")
	public void jquery(String groupName,HttpServletRequest re,HttpServletResponse res) throws IOException{
		PrintWriter pw= res.getWriter();
		Sysgroup group=sysgroupService.queryByName(groupName);
		//System.out.println("query:"+groupName);
		if(group == null){
			pw.write("yes");
		}else{
			pw.write("no");
		}
	}
}
