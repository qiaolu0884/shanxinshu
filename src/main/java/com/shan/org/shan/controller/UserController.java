package com.shan.org.shan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shan.org.shan.pojo.TreeAge;
import com.shan.org.shan.pojo.User;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.service.UserService;
import com.shan.org.shan.utils.BatchDateUtil;
import com.shan.org.shan.utils.ExportExcel;
import com.shan.org.shan.utils.IPAddrUtils;
import com.shan.org.shan.utils.IpUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	SysuserService sysuserService;
	
	@Autowired
	SysuserlogService sysuserlogService;
	
	/**
	 * 根据前台传过来的条件查询用户列表
	 * @Title: findByUsername   
	 * @param: @param model
	 * @param: @param username
	 * @param: @param userGrade
	 * @param: @param taBegin
	 * @param: @param taEnd
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/user/findByQuery")
	public String findByQuery(Model model,String username,String userGrade,
			String taBegin,String taEnd,Integer pageNo){
		Integer totalPage = userService.totalPage(username,userGrade,taBegin,taEnd,pageNo);
		if(pageNo == null){
			pageNo=1;
		} else if(pageNo > totalPage){
			pageNo = totalPage;
		}
		List<User> users = userService.findByQuery(username,userGrade,taBegin,taEnd,pageNo);
		List<Object> grades = userService.findGradesAll();
		List<TreeAge> ages = userService.findAgesAll();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("grades", grades);
		model.addAttribute("users", users);
		model.addAttribute("ages", ages);
		model.addAttribute("begin",taBegin);
		model.addAttribute("end", taEnd);
		model.addAttribute("username", username);
		model.addAttribute("userGrade", userGrade);
		return "admin/user_info";
	}
	
	/**
	 * 跳转到用户列表页
	 * @Title: toUser   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/user")
	public String toUser(Model model,String username,String userGrade,
			String taBegin,String taEnd,Integer pageNo,
			HttpServletRequest request){
		List<User> users = userService.findByQuery(username,userGrade,taBegin,taEnd,pageNo);
		Integer totalPage = userService.totalPage(username,userGrade,taBegin,taEnd,pageNo);
		List<Object> grades = userService.findGradesAll();
		List<TreeAge> ages = userService.findAgesAll();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("users", users);
		model.addAttribute("pageNo", 1);
		model.addAttribute("grades", grades);
		model.addAttribute("ages", ages);
		
		//添加日志
		String username_1=(String)request.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username_1);//用户名（账号）
		userlog.setMessage("用户信息-查看");
		userlog.setIp(IpUtil.getIp());//IP地址
		userlog.setName(u.getName());//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		
		return "admin/user_info";
	}
	
	@RequestMapping(value = "/user/importUser")
	public void importUser(String username,String userGrade,
			String taBegin,String taEnd,HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		String[] rowName = {"序号","会员账号","会员级别","善心树龄"};
		List<User> users = userService.importUser(username,userGrade,taBegin,taEnd);
		List<Object[]> list = new ArrayList<Object[]>();
		if(users != null && users.size()>0){
			int i=1;
			for (User user : users) {
				Object[] o = {i++,user.getUsername(),user.getUserGrade(),user.getTree().getAge()};
				list.add(o);
			}
		} 
		//添加日志
		String username_1=(String)request.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username_1);//用户名（账号）
		userlog.setMessage("用户信息-导出用户");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		
		ExportExcel excel = new ExportExcel("用户信息列表"+new Date().getTime(), rowName, list, response);
		excel.export();
	}
}
