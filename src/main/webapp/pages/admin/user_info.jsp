<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="/css/user_info.css"/>
		<link rel="stylesheet" href="/css/selectordie_theme_03.css">
		<title>善心树管理平台--用户信息</title>
		<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
	    <script src="/js/tiaozhuan.js" type="text/javascript" charset="utf-8"></script>
	    <script src="/js/selectordie.js"></script>
	    
	    <script type="text/javascript">
    	$(function(){
    		$('select').selectOrDie({
    			size: 5
    		});
    	});
	    $(function(){
	    	$("#bt").click(function(){
	    		var	g = $("#grade option:selected").val();
	    		var	b = $("#begin option:selected").val();
	    		var	e = $("#end option:selected").val();
	    		var u = $("#username").val();
	    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo=1";
	    	})
	    	$("#GO").click(function(){
	    		var	g = $("#grade option:selected").val();
	    		var	b = $("#begin option:selected").val();
	    		var	e = $("#end option:selected").val();
	    		var u = $("#username").val();
	    		var p = $("#pageNo").val();
	    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo="+p;
	    	})
	    	
	    	$("#import").click(function() {
	    		var	g = $("#grade option:selected").val();
	    		var	b = $("#begin option:selected").val();
	    		var	e = $("#end option:selected").val();
	    		var u = $("#username").val();
	    		window.location.href = "/user/importUser?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u;
			})
			$("#importNo").click(function(){
				alert("暂时没有可以导出的数据！")
			})
	 	})
	 	function syy() {
	    	var	g = $("#grade option:selected").val();
	    	var	b = $("#begin option:selected").val();
	    	var	e = $("#end option:selected").val();
	    	var u = $("#username").val();
    		var page = ($("#pageNo").val());
    		var p = page-1;
    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo="+p;
		}
    	function xyy() {
    		var	g = $("#grade option:selected").val();
    		var	b = $("#begin option:selected").val();
    		var	e = $("#end option:selected").val();
    		var u = $("#username").val();
    		var page = $("#pageNo").val();
    		var p = parseInt(page)+1;
    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo="+p;
		}
    	function dyy() {
    		var	g = $("#grade option:selected").val();
    		var	b = $("#begin option:selected").val();
    		var	e = $("#end option:selected").val();
    		var u = $("#username").val();
    		var p = 1;
    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo="+p;
		}
    	
    	function wy() {
    		var	g = $("#grade option:selected").val();
    		var	e = $("#end option:selected").val();
    		var	b = $("#begin option:selected").val();
    		var u = $("#username").val();
    		var p = $("#totalPage").html();
    		window.location.href = "/user/findByQuery?userGrade="+g+"&&taBegin="+b+"&&taEnd="+e+"&&username="+u+"&&pageNo="+p;
		}
    	
	    </script>
	</head>
	<body class="user_info">
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
				<li ><a href="/index">首页</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:user">
				<li class="current"><a href="/user">用户信息</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="order:order">
				<li ><a href="/order/trade_management">交易管理</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:system">
				<li ><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
			</ul>
		</div>
		
		<div class="wrapper">
			<div class="title clearfix">
				<a class="current" href="/user">用户信息查询</a>
			</div>
			<div class="content">
				<form action="" method="post" class="info_query clearfix">
					<p class="clear fix">
						<label>会员账号</label>
						<input type="text" name="username" id="username" value="${username }" />
					</p>
					<div class="clearfix selectMenu">
						<label>会员级别</label>
						<div class="select left">
							<select class="myselect" id="grade">
								<option value="全部">全部</option>
								<c:forEach items="${grades}" var="grade">
									<option value="${grade}" <c:if test="${userGrade == grade}">selected="selected"</c:if>>${grade}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="clearfix selectMenu">
					<label>善心树龄</label>
						<div class="select left">
							<select class="myselect" id="begin">
								<option value="0">全部</option>
								<c:forEach items="${ages }" var="age">
									<option value="${age.age }" <c:if test="${begin == age.age}">selected="selected"</c:if>>${age.ageDes }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<span class="left" style="line-height: 40px; padding: 0 10px;">至</span>
					<div class="select left">
						<select class="myselect" id="end">
								<option value="0">全部</option>
								<c:forEach items="${ages }" var="age">
									<option value="${age.age }" <c:if test="${end == age.age}">selected="selected"</c:if>>${age.ageDes }</option>
								</c:forEach>
							</select>
					</div>
					<input type="button" value="查询" id="bt"/>
					</div>
				</form>
			</div>
			<div class="content tables">
			<shiro:hasPermission name="user:import">
				<c:choose>
					<c:when test="${users==null || users.size()==0}">
						<a href="javascript:void(0);" class="export clearfix"><img src="/img/icon_export.png"/><span id="importNo">导出</span></a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" class="export clearfix"><img src="/img/icon_export.png"/><span id="import">导出</span></a>
					</c:otherwise>
				</c:choose>
			</shiro:hasPermission>
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>序号</th>
						<th>会员账号</th>
						<th>会员级别</th>
						<th>善心树龄</th>
					</tr>
					<c:forEach items="${users }" var="user">
						<tr>
							<td>${user.id }</td>
							<td>${user.username }</td>
							<td>${user.userGrade }</td>
							<td>${user.tree.age }</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pages clearfix">
					<div class="left clearfix pagebtn">
						<span class="left">第<b>
						<c:if test="${pageNo == null}">1</c:if>${pageNo }
						</b>页</span>
						<ul class=" left">
						<c:choose>
							<c:when test="${pageNo == null || pageNo == 1}">
								<li><img src="/img/arrow_left1.png" /></li>
								<li><img src="/img/arrow_left2.png" /></li>
							</c:when>
							<c:otherwise>
								<li><img src="/img/arrow_left1.png" onclick="dyy()"/></li>
								<li><img src="/img/arrow_left2.png" onclick="syy()"/></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${totalPage == 1 || pageNo == totalPage}">
								<li><img src="/img/arrow_right2.png"/></li>
								<li><img src="/img/arrow_right1.png"/></li>
							</c:when>
							<c:otherwise>
								<li><img src="/img/arrow_right2.png" onclick="xyy()"/></li>
								<li><img src="/img/arrow_right1.png" onclick="wy()"/></li>
							</c:otherwise>
						</c:choose>
						</ul>
						<span class="right">共<em id="totalPage">${totalPage }</em>页</span>
					</div>
					<p class="right">
						<span class="left">跳转到</span>
						<input class="left num_text" type="text" name="pageNo" id="pageNo" value="${pageNo }" placeholder="1" />
						<input type="button" id="GO" value="GO" />
					</p>
				</div>
			</div>
	</body>
</html>
