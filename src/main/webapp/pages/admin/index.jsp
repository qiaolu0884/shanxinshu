<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<title>善心树管理平台</title>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<script type="text/javascript" src="/js/highcharts.js"></script>
		<script type="text/javascript" src="/js/exporting.js"></script>
		<script type="text/javascript" src="/js/chart.js">
		</script>
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
					<span class="left">${loginUser}</span>
				</div>
			</div>
		</div>
		
		<!--导航-->
		<div class="nav">
			<ul class="clearfix">
			<shiro:hasPermission name="index:index">
				<li class="current"><a href="/index">首页</a></li>
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
		<shiro:hasPermission name="index:index">
		<div class="wrapper">
			<div class="content" style="padding: 20px 0; margin-top: 20px;">
				<div id="container" style="height: 400px; margin: 20px 30px 40px;"></div>
				<div id="container1" style="height: 400px; margin: 30px 30px; "></div>
			</div>
		</div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="index:index">
			<script type="text/javascript">
				window.location.href = "/403"
			</script>
		</shiro:lacksPermission>
	</body>
</html>
