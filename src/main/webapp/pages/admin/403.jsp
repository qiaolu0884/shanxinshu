<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<title>善心树管理平台</title>
	</head>
	<body>
		
		<!--头部-->
		<div class="head clearfix">
			<a class="left clearfix" href="/index">
				<img src="/img/icon_tree.png" alt=""/>
				<span>善心树管理平台</span>
			</a>
			
			<div class="right">
				<a class="right exit" href="/login/logout"><img src="/img/icon_exit.png"/><span>退出</span></a>
				<div class="clearfix right role">
					<img class="left" src="/img/icon_person.png"/>
					<span class="left">${loginUser }</span>
				</div>
			</div>
		</div>
		
		<!--导航-->
		<div class="nav">
			<ul>
			<shiro:hasPermission name="index:index">
				<li  ><a href="/index">首页</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:user">
				<li><a href="/user">用户信息</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="order:order">
				<li><a href="/order/trade_management">交易管理</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:system">
				<li><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
			</ul>
		</div>
		
		<div class="wrapper">
			<div class="content" style="margin: 150px auto; width: 400px; height: 200px; text-align: center; border: 1px solid #6aa173">
				<h2 style="height: 50px; line-height: 50px; background: #6aa173; font-size: 18px; color: #fff;">提示</h2>
				<span style="line-height: 150px; ">你没有权限，请联系管理员！</span>
			</div>
		</div>
		
	</body>
</html>
