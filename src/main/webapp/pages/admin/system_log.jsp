<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/jedate.css"/>
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="/css/system_set.css"/>
		<title>善心树管理平台--交易管理</title>
		<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jquery.jedate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jedate.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body class="system_log">
		
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
			<ul>
			<shiro:hasPermission name="index:index">
				<li ><a href="/index">首页</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:user">
				<li><a href="/user">用户信息</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="order:order">
				<li><a href="/order/trade_management">交易管理</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:system">
				<li class="current"><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
			</ul>
		</div>
		
		
		<div class="wrapper">
			<div class="title clearfix">
			<shiro:hasPermission name="system:system">
				<a href="/system/systemSettings">用户管理</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:grade">
				<a href="/system/groupList">设置用户组</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:syslog">
				<a class="current" href="/system/systemlog">操作日志</a>
			</shiro:hasPermission>
			</div>
			<div class="content">
				<form action="/system/zuhequery" method="post" class="info_query clearfix" id="form_11">
					<div class="clearfix"  style="margin-bottom:0;">
						<p class="clearfix" style="margin-bottom:0;">
							<label>账号</label>
							<input type="text" name="username" id="" value="${username}" />
						</p> 
						<p class="clearfix" style="margin-bottom:0;">
							<label>姓名</label>
							<input type="text" name="name" id="" value="${name}" />
						</p>
					</div>
					<div class="clearfix"  style="margin-bottom:0;">
						<div class="left dateselect clearfix" style="">
							<label for="">交易日期</label>
							<p class="datep left">
				            	<input class="datainp wicon" id="inpstart" type="text" placeholder="请选择日期" name="beginTime" readonly="readonly" value="${beginTime}">
				           	</p >
				            <span class="left" style="line-height: 40px; padding: 0 10px;">至</span>
				        	<p class="datep left">
				            	 <input class="datainp wicon" id="inpend" type="text" placeholder="请选择日期" name="endTime" readonly="readonly" value="${endTime}">
				           	</p >
						</div>
						<input type="hidden" id="intopage" name="pagenumber" />
						<input style="float: right;" type="submit" value="查询"/>
					</div>
					
				</form>
			</div>
		
			<div class="content tables">
				<table border="0" cellspacing="0" cellpadding="0" class="table1">
					<tr>
						<th>序号</th>
						<th>操作员账号</th>
						<th>操作员姓名</th>
						<th>操作时间 </th>
						<th>IP地址 </th>
						<th>事项</th>
					</tr>
					<c:forEach items="${userslog}" var="ulog" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${ulog.username}</td>
							<td>${ulog.name}</td>
							<td><%-- ${ulog.createTime} --%><fmt:formatDate value="${ulog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${ulog.ip}</td>
							<td>${ulog.message}</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pages clearfix">
					<div class="left clearfix pagebtn">
						<span class="left">第<b id="ba">${pagenumber}</b>页</span>
						<ul class=" left">
							<li><a href="#" onclick="left_1();"><img src="/img/arrow_left1.png"/></a></li>
							<li><a href="#" onclick="left_2();"><img src="/img/arrow_left2.png"/></a></li>
							<li><a href="#" onclick="right_2();"><img src="/img/arrow_right2.png"/></a></li>
							<li><a href="#" onclick="right_1();"><img src="/img/arrow_right1.png"/></a></li>
						</ul>
						<span class="right">共<em id="em">${pagesum}</em>页</span>
					</div>
					<p class="right">
						<span class="left">跳转到</span>
						<input class="left num_text" type="text" name="pagenumber" id="intopagenumber" value="" placeholder="${pagenumber}" />
						<input style="margin: 0;" type="button" id="" value="GO" onclick="queryform()" />
					</p>
				</div>
			
			</div>
		</div>
		
	</body>
	<script type="text/javascript" src="/js/dateselect.js"></script>
	<script type="text/javascript">
	//分页-10
	function left_1(){
		var intonumber = document.getElementById("ba").innerHTML;//获取当前页
		if(intonumber-10 >0){
			var pagenum=intonumber-10;
			document.getElementById("intopage").value=pagenum;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}else{
			document.getElementById("intopage").value=1;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}
	}
	//分页+10
	function right_1(){
		var intonumber = document.getElementById("ba").innerHTML;	//获取当前页
		var pagecount = document.getElementById("em").innerHTML;	//获取总页数
		var pagenum=Number(intonumber) + Number(10);
		if(pagenum>=pagecount){
			document.getElementById("intopage").value=pagecount;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}else{
			document.getElementById("intopage").value=pagenum;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}
	}
	//分页+1
	function right_2(){
		var intonumber = document.getElementById("ba").innerHTML;	//获取当前页
		var pagecount = document.getElementById("em").innerHTML;	//获取总页数
		var pagenum=Number(intonumber) + Number(1);
		if(pagenum >= pagecount){
			document.getElementById("intopage").value=pagecount;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}else{
			document.getElementById("intopage").value=pagenum;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}
	}
	
	//分页-1
	function left_2(){
		var intonumber = document.getElementById("ba").innerHTML;
		if(intonumber==1){
			document.getElementById("intopage").value=intonumber;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}else{
			var pagenum=intonumber-1;
			document.getElementById("intopage").value=pagenum;
			var form_11 = document.getElementById("form_11");
			form_11.submit();
		}
	}
	//点击GO跳转页面
	function queryform(){
		var intonumber = document.getElementById("intopagenumber").value;
		if(intonumber ==''||intonumber ==null){
			intonumber=1;
		}
		document.getElementById("intopage").value=intonumber;
		var form_11 = document.getElementById("form_11");
		form_11.submit();
	}
	</script>
</html>