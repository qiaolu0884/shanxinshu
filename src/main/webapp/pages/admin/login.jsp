<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- <meta charset="UTF-8"/> -->
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="/css/login.css"/>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<title>善心树管理平台</title>
	</head>
	<script type="text/javascript" >
	  $(function(){
		 /*  $("#username").blur(function(){
			  var u = $("#username").val();
			  if(u == null || u == ""){
				  alert("请输入用户名！")
			  } else {
				$.post("/login/checkUsername",{username:u},function(date){
				   if(date == 0){
					   alert("用户名输入错误！");
				   	}
				 })
	  		 }
		  }) */
		  $("#bt").click(function(){
			  var u = $("#username").val();
			  var p = $("#password").val();
			  var c = $("#yzm").val();
			  if (u == null || u == "") {
				  alert("请输入用户名！");
			  } else if (p == null || p == "") {
				  alert("请输入密码！");
			  } else if (c == null || c == "") {
				  alert("请输入验证码！");
			  } else {
				  $.post("/login",{username:u,password:p,yzm:c},function(date){
					  if (date == 0) { 
						  alert("验证码输入错误！");
					  } else if (date == 1) {
						  alert("用户名不存在！");
					  } else if (date == 2) {
					   	  alert("密码输入错误！")
					  } else {
						  window.location.href = "/index";
					  }
		  		 })
			  }
		  })
	  })
	</script>
	<body class="login">
		<img class="bg" src="/img/loginbg.jpg"/>
		
		<form action="" method="post">
			<h1 class="title clearfix">
				<img class="left" src="/img/icon_tree.png"/>
				<span class="left">善心树管理平台</span>
			</h1>
			<div class="clearfix">
				<label for="">账&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
				<input type="text" name="username" id="username" value="" placeholder="请输入账号" />
			</div>
			<div class="clearfix">
				<label for="">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<input type="password" name="password" id="password" value="" placeholder="请输入登录密码" />
			</div>
			<div class="clearfix">
				<label class="yzm" for="">验证码：</label>
				<div class="clearfix code">
					<input type="text" name="yzm" id="yzm" value="" placeholder="请输入验证码" />
					<span><img alt="" src="/login/validCode" width="133px" height="43px"/></span>
				</div>
			</div>
			<input id="bt" type="button" value="登录"/>
		</form>
		
	</body>
</html>
