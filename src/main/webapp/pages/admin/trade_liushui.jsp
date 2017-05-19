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
		<script src="/js/jquery.jedate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jedate.js" type="text/javascript" charset="utf-8"></script>
<!-- 		<script type="text/javascript">
	/* 		function load() {
				var aa = document.getElementById("textfiled").value; //获取text的值aaa
			}
			/* function search(dateType) {
				$('#dateType').val(dateType);
				$.ajax({
					type : 'POST',
					url : '/liushui',
					data : $('#sf').serialize(),
					dataType : 'json',
					success : function(data) {
						console.log(data);
						$.each(data,function(idx,m){
							$("#tb").append("<tr>"+
									"<td>"+m.createTime+"</td>"+
									"<td>"+m.orderNumber+"</td>"+
									"<td>"+m.userGrade+"</td>"+
									"<td>"+m.userId+"</td>"+
									"<td>"+m.status+"</td>"+
									"<td>"+m.money+"</td>"+
							"</tr>");
						})
					}
				});
			} */
			function statusChange(sta){
				if(sta==-1){
					$('#status').val('');
				}else{
					$('#status').val(sta);
				}
			}
			function exportExcel(){
				$('#sf').prop('action','/exportExcel');
				$('#sf').submit();
			}  */
		</script> -->
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
				<li ><a href="/system/systemSettings">系统设置</a></li>
			</shiro:hasPermission>
			</ul>
		</div>
		
		
		<div class="wrapper">
			<div class="title clearfix">
				<a href="/order/trade_management">数据汇总查询</a>
				<a class="current" href="/order/trade_liushui">交易流水查询</a>
			</div>
			<div class="content">
				<form action="/order/goquery" method="post" class="info_query clearfix" id="form_11">
					<p class="clearfix">
						<label>会员账号</label>
						<input type="text" name="username" id="username_1" value="${username}" />
					</p>
					<div class="clearfix selectMenu">
						<label>会员级别</label>
						<div class="dropdownMenu" id="dropdownMenu">
							<%-- <input type="text" name="userGrade" id="userGrade_1" value="${userGrade}"/> --%>
						</div>
					</div>
					
					<div class="clearfix selectMenu">
						<label>交易状态</label>
						<div class="dropdownMenu" id="dropdownMenu1">
							<select name="status" id="status_1" style="width: 180px;height: 40px; margin-left: -21px;">
								<c:if test="${status==null}">
									<option value="">全部</option>
									<option value="2">交易关闭</option>
									<option value="1">已支付</option>
									<option value="0">未支付</option>
								</c:if>	
									<c:if test="${status==1}">
									<option value="">全部</option>
									<option value="2">交易关闭</option>
									<option value="1" selected="selected">已支付</option>
									<option value="0">未支付</option>
								</c:if>	
									<c:if test="${status==0}">
									<option value="">全部</option>
									<option value="2">交易关闭</option>
									<option value="1">已支付</option>
									<option value="0" selected="selected">未支付</option>
								</c:if>	
									<c:if test="${status==2}">
									<option>全部</option>
									<option value="2" selected="selected">交易关闭</option>
									<option value="1">已支付</option>
									<option value="0">未支付</option>
								</c:if>	
								</select>
						</div>
					</div>
					<p class="clearfix">
						<label>订单号</label>
						<input type="text" name="orderNumber" id="orderNumber_1" value="${orderNumber}" />
					</p>
					<div class="left dateselect clearfix">
						<label for="">交易日期</label>
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
						<!-- <a href="javascript:search(0)">今天</a>
						<a href="javascript:search(-1)">昨天</a>
						<a href="javascript:search(-2)">前天</a>
						<a href="javascript:search(-30)">近30天</a> -->
					</div>
					
					
					<div class="clearfix right btn">
						<input type="hidden" id="intopage" name="pagenumber" />
						<input type="submit" value="查询"/>
						<input type="button" value="重置" onclick="chongzhi();"/>
					</div>
				</form>
			</div>
		
			<div class="content tables">
				<div class="clearfix">
					<!-- <a href="javascript:exportExcel()" class="export clearfix left"><img src="img/icon_export.png"/><span>导出</span></a> -->
					<shiro:hasPermission name="order:import">
					<c:choose>
					<c:when test="${orders==null || orders.size()==0}">
						<a href="javascript:void(0);" class="export clearfix"><img src="/img/icon_export.png"/><span id="importNo">导出</span></a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" class="export clearfix"><img src="/img/icon_export.png"/><span id="import">导出</span></a>
					</c:otherwise>
					</c:choose>
					</shiro:hasPermission>
					<shiro:hasPermission name="order:importlog">
					<a href="/order/exportlog2" class="log clearfix left"><img src="/img/icon_log.png"/><span>查看日志</span></a>
					</shiro:hasPermission>
				</div>
				
				<table border="0" cellspacing="0" cellpadding="0" id="tb">
					<tr>
						<th>交易时间</th>
						<th>交易订单号 </th>
						<th>会员级别</th>
						<th>会员账号</th>
						<th>交易状态</th>
						<th>交易金额</th>
					</tr>
					<c:forEach items="${orders}" var="orders">
						<tr>
							<td><fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td>${orders.orderNumber}</td>
							<td>${orders.userGrade}</td>
							<td>${orders.username}</td>
							<td><c:if test="${orders.status==0}">未支付</c:if><c:if test="${orders.status==1}">已支付</c:if><c:if test="${orders.status==2}">交易关闭</c:if></td>
							<td>${orders.money}</td>
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
	<script type="text/javascript" src="/js/dateselect.js"></script>
	<script type="text/javascript">
		
	$(function(){
		$("#import").click(function() {
    		var	s = $("#status_1 option:selected").val();
    		alert(s);
    		var n = $("#orderNumber_1").val();
    		var g = $("#userGrade_1").val();
    		var	start = $("#inpstart").val();
    		var	e = $("#inpend").val();
    		var u = $("#username_1").val();
    		window.location.href = "/order/importOrder?userGrade="+g+"&&beginTime="+start+"&&endTime="+e+"&&username="+u+"&&orderNumber="+n+"&&status="+s;
		})
		$("#importNo").click(function(){
			alert("暂时没有可以导出的数据！")
		})
	})
	
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
	
	//重置
	function chongzhi(){
		document.getElementById("username_1").value="";
		document.getElementById("orderNumber_1").value="";
		document.getElementById("status_1").value="";
		document.getElementById("inpstart").value="";
		document.getElementById("inpend").value="";
	}
	</script>
</html>
