<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="/css/jedate.css" />
<link rel="stylesheet" type="text/css" href="/css/base.css" />
<link rel="stylesheet" type="text/css" href="/css/form.css" />
<link rel="stylesheet" type="text/css" href="/css/trade_management.css" />
<title>善心树管理平台--交易管理</title>
<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/jquery.jedate.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/jedate.js" type="text/javascript" charset="utf-8"></script>
<script>
	function load() {
		var aa = document.getElementById("textfiled").value; //获取text的值aaa
	}
	function search(dateType) {
		$('#dateType').val(dateType);
		$.ajax({
			type : 'POST',
			url : '/order/trade_management',
			data : $('#searchForm').serialize(),
			dataType : 'json',
			success : function(data) {
				if(data!=null){
					var result = eval(data);
					$('#szz').text(result.szz);
					$('#sxb').text(result.sxb);
					$('#money').text(result.money);
				}else{
					$('#szz').text(0);
					$('#sxb').text(0);
					$('#money').text(0.00);
				}
			}
		});
	}
</script>
</head>

<body class="trade_management">

	<!--头部-->
	<div class="head clearfix">
		<a class="left clearfix" href="/index"> <img
			src="/img/icon_tree.png" alt="" /> <span>善心树管理平台</span>
		</a>

		<div class="right">
			<a class="right exit" href="/login/logout"><img
				src="/img/icon_exit.png" /><span>退出</span></a>
			<div class="clearfix right role">
				<img class="left" src="/img/icon_person.png" /> <span class="left">${loginUser}</span>
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
				<li ><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
		</ul>
	</div>


	<div class="wrapper">
		<div class="title clearfix">
			<a class="current" href="/order/trade_management">数据汇总查询</a>
		<shiro:hasPermission name="order:liushui">
			 <a href="/order/trade_liushui">交易流水查询</a>
		</shiro:hasPermission>
		</div>
		<div class="content">
			<form id="searchForm" action="/timequery" method="post" class="info_query clearfix">
				<!-- <div class="clearfix" style="margin-bottom: 0;">
					<p class="clearfix">
						<label>会员账号</label> <input type="text" name="" id="" value="" />
					</p>
					<div class="clearfix selectMenu">
						<label>会员级别</label>
						<div class="dropdownMenu" id="dropdownMenu">
							<div class="text">
								<span>A轮服务中心</span> <img class="down" src="img/arrow_down.png"
									alt="" /> <img class="up" src="img/arrow_up.png" alt="" />
							</div>
							<div class="mask" id="mask">
								<ul class="list">
									<li><a href="#tab1">A轮服务中心C轮服务中心A轮服务中心C轮服务中心</a></li>
									<li><a href="#tab2">B轮服务中心</a></li>
									<li><a href="#tab3">C轮服务中心</a></li>
									<li><a href="#tab4">A轮功德主</a></li>
									<li><a href="#tab5">B轮功德主</a></li>
									<li><a href="#tab6">C轮功德主</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div> -->

				<div class="left dateselect clearfix" style="margin-bottom: 0;">
					<label for="">交易日期</label>
					<!-- <p class="datep left">
						<input
							onClick="jeDate({dateCell:'#beginDate',isTime:true,format:'YYYY-MM-DD'})"
							class="datainp" id="beginDate" name="beginDate" type="text" placeholder="请选择日期">
						<a href="#">
							onClick="jeDate({dateCell:'#beginDate',isTime:true,format:'YYYY-MM-DD'})"
							class="icon-date"><img src="img/icon_calendar.png" /></a>
					</p>
					<span class="left" style="line-height: 40px; padding: 0 10px;">至</span>
					<p class="datep left">
						<input
							onClick="jeDate({dateCell:'#endDate',isTime:true,format:'YYYY-MM-DD'})"
							class="datainp" id="endDate" name="endDate" type="text" placeholder="请选择日期">
						<a href="#"
							onClick="jeDate({dateCell:'#endDate',isTime:true,format:'YYYY-MM-DD'})"
							class="icon-date"><img src="img/icon_calendar.png" /></a>
					</p> -->
					<p class="datep left">
			            	<input class="datainp wicon" id="inpstart" type="text" placeholder="请选择日期" name="beginTime" readonly="readonly" value="${beginTime}">
			           	</p >
			            <span class="left" style="line-height: 40px; padding: 0 10px;">至</span>
			        	<p class="datep left">
			            	 <input class="datainp wicon" id="inpend" type="text" placeholder="请选择日期" name="endTime" readonly="readonly" value="${endTime}">
			           	</p >
				</div>
				<div class="tags left clearfix" style="margin-bottom: 0;">
					<input type="hidden" id="dateType" name="dateType"/>
					<a href="/order/trade_management">今天</a> 
					<a href="/yesTerDay">昨天</a> 
					<a href="/qiantian">前天</a> 
					<a href="/onefiveDay">近30天</a>
				</div>

				<div class="clearfix right btn">
					<input type="submit" id="aaa" value="查询" /> <input
						type="button" value="重置" />
				</div>
			</form>
		</div>

		<div class="content ">
			<div class="clearfix detail">
				<div class="clearfix">
					<img src="/img/icon_szz.png" />
					<div>
						<p>善种子</p>
						<p>
							<span id="szz">${shanzhongzi}</span>个
						</p>
					</div>
				</div>
				<div class="clearfix">
					<img src="/img/icon_sxb.png" />
					<div>
						<p>善心币</p>
						<p>
							<span id="sxb">${shanxinbi}</span>个
						</p>
					</div>
				</div>
				<div class="clearfix">
					<img src="/img/icon_money.png" />
					<div>
						<p>交易净额</p>
						<p>
							<span id="money">${money}</span>元
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="/js/dateselect.js"></script>
</html>