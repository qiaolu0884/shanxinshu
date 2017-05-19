<%@page import="com.shan.org.shan.utils.BatchDateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/jedate.css"/>
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="/css/trade_management.css"/>
		<title>善心树管理平台--交易管理</title>
		<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jedate.js" type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">
		function search() {
				$.ajax({
					type : 'POST',
					url : '/order/exportlog2',
					dataType : 'json',
					success : function(data) {
						$.each(data,function(idx,m){
							$("#tb").append("<tr>"+
									"<td>"+m.id+"</td>"+
									"<td>"+m.count+"</td>"+
									"<td>"+m.status+"</td>"+
									"<td>"+m.createTime+"</td>"+
									"<td>"+m.sysuserId+"</td>"+
							"</tr>");
						})
					}
				});
			}
		</script>
	</head>
	<body class="trade_management">
		
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
				<li class="current"><a href="/order/trade_management">交易管理</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:system">
				<li class="current"><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
			</ul>
		</div>
		
		
		<div class="wrapper">
			<div class="menu">
				<span>当前位置：</span>
				<a href="/order/trade_management">交易管理</a>
				&gt;&gt;
				<a href="/order/trade_liushui">交易流水查询</a>
				&gt;&gt;
				<a href="">导出日志</a>
				
				<a class="right back" href="/order/trade_liushui">&lt;&lt;返回上一页</a>
			</div>
			<div class="content">
				<form action="/order/exportlog2" method="post" class="info_query clearfix" id="form_11">
					<p class="clearfix">
						<label>账号</label>
						<input type="text" name="username" id="username_1" value="${username}" />
					</p>
					<input type="hidden" id="intopage" name="pagenumber" />
					<input style="float: left;" type="submit" value="查询"/>
				</form>
			</div>
		
			<div class="content tables">
				<table border="0" cellspacing="0" cellpadding="0" class="table1" id="tb">
					<tr>
						<th rowspan="2">会员账号</th>
						<!-- <th rowspan="2">交易订单号 </th> -->
						<th colspan="4" style="border-bottom: 1px solid #fff;">导出内容</th>
					</tr>
					<tr>
						<th>数据条数</th>
						<th>数据内容</th>
						<th>时间区间</th>
						<th>导出次数</th>
					</tr>
					<c:forEach items="${load}" var="load">
						<tr>
							<td>${load.username}</td>
							<td>${load.count}</td>
							<td>${load.message }</td>
							<td>${load.createTime}</td>
							<td>1</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pages clearfix">
					<div class="left clearfix pagebtn">
						<span class="left">第<b id="ba">${pagenumber}</b>页</span>
						<ul class=" left">
							<li><a href="#" onclick="left_1();"><img src="/img/arrow_left1.png"/></a></li>
							<li><a href="#" onclick="left_2();"><img src="/img/arrow_left2.png"/></a></li>
							<li><a href="#" onclick="right_2()"><img src="/img/arrow_right2.png"/></a></li>
							<li><a href="#" onclick="right_1()"><img src="/img/arrow_right1.png"/></a></li>
						</ul>
						<span class="right">共<em id="em">${pagesum}</em>页</span>
					</div>
					<p class="right">
						<span class="left">跳转到</span>
						<input class="left num_text" type="text" name="pagenumber" id="intopagenumber" value="${pagenumber}" placeholder="1" />
						<input type="button" id="" value="GO" onclick="queryform()"/>
					</p>
				</div>
			</div>
			
		</div>
		
	</body>
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
