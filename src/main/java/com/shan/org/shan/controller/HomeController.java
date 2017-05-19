package com.shan.org.shan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.SysuserQuery;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.IpUtil;
import com.shan.org.shan.utils.MD5Util;
import com.shan.org.shan.utils.ValidCodeUtil;

@Controller
public class HomeController {

	@Autowired
	private SysuserService sysuserService;
	
	@Autowired
	private SysuserlogService sysuserlogService;

	@RequestMapping(value = "/login/tologin")
		public String toLogin(){
			return "admin/login";
		}
	/**
	 * 登录功能 修改登录功能，用shiro权限验证登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public @ResponseBody
	String login(HttpServletRequest request, String username, String password, String yzm) {
		String validcode = (String) request.getSession().getAttribute("validcode");//获取验证码
		if(yzm.equals(validcode)){//验证验证码
			List<Sysuser> list = null;
			if (username != null) {//
				SysuserQuery query = new SysuserQuery();
				query.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(1l);
				list = sysuserService.selectByExample(query);
			}
			if (list != null && list.size() > 0) {
				// 使用shiro进行验证
				// 1.获取subject对象
				Subject subject = SecurityUtils.getSubject();// 为认证
				// 认证
				AuthenticationToken authenticationToken = new UsernamePasswordToken(username, MD5Util.encode(password));
				try {
					subject.login(authenticationToken);
					// 不报异常，登陆成功，将用户放到session，跳转index
					String user = (String) subject.getPrincipal();// 获取权限中的用户，并强转
					
					
					//String username_1=(String)request.getSession().getAttribute("loginUser");
					Sysuser u=sysuserService.findSysuserByName(username);
					Sysuserlog userlog = new Sysuserlog();
					userlog.setUsername(username);//用户名（账号）
					userlog.setMessage("用户登录");
					userlog.setIp(IpUtil.getIp());	//IP地址
					userlog.setName(u.getName());		//姓名	
					userlog.setUserId(u.getSysId());	//用户ID
					sysuserlogService.insertSelective(userlog);
					
					//登录成功以后修改用户的最后登录时间
					
					
					//request.getSession().setAttribute("userAndPass", user+"+"+ MD5Util.encode(password));
					request.getSession().setAttribute("loginUser", user);
					return "3";
				} catch (Exception e) {
					e.printStackTrace();
					return "2";
				}
			} else {
				return "1";
			}
		} else {
			return "0";
		}
	}

	/**
	 * 退出登录功能
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/logout")
	public String logout(HttpServletRequest request) {
		//添加日志
		String username_1=(String)request.getSession().getAttribute("loginUser");
		Sysuser u=sysuserService.findSysuserByName(username_1);
		Sysuserlog userlog = new Sysuserlog();
		userlog.setUsername(username_1);//用户名（账号）
		userlog.setMessage("用户退出");
		userlog.setIp(IpUtil.getIp());	//IP地址
		userlog.setName(u.getName());		//姓名	
		userlog.setUserId(u.getSysId());	//用户ID
		sysuserlogService.insertSelective(userlog);
		
		request.getSession().setMaxInactiveInterval(1);
		return "redirect:/login/tologin";
	}

/*	@RequestMapping(value = "/login/checkUsername")
	public @ResponseBody String checkUsername(String username) {
		List<Sysuser> list = null;
		if (username != null) {
			SysuserQuery query = new SysuserQuery();
			query.createCriteria().andUsernameEqualTo(username);
			list = sysuserService.selectByExample(query);
		}
		if (list != null && list.size() > 0) {
			// TODO发短信
			return "1";
		} else {
			return "0";
		}
	}
*/
	/**
	 * 发送验证码
	 * @Title: validCode   
	 * @param: @param req
	 * @param: @param resp
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value = "/login/validCode")
	public void validCode(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		ValidCodeUtil.getValidCode(req, resp);
	}
	
	/**
	 * 
	 * @Title: to  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/to")
	public String to() {
		return "admin/user_info";
	}
	
	/**
	 * 权限不足时默认发此请求
	 * @Title: error   
	 * @param: @param request
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/403")
	public String error(HttpServletRequest request) throws Exception{
//		request.get
		return "admin/403";
	}
}
