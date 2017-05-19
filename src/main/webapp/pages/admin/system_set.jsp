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
		<link rel="stylesheet" type="text/css" href="/css/system_set.css"/>
		<title>善心树管理平台--系统设置</title>
		<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jquery.jedate.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="/js/system_set.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="/js/popup.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body class="system_set">
		
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
				<a class="current" href="/system/systemSettings">用户管理</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:grade">
				<a href="/system/groupList">设置用户组</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:syslog">
				<a href="/system/systemlog">操作日志</a>
			</shiro:hasPermission>
			</div>
			
			<div class="content">
				<form action="/system/combinedQuery" method="post" class="info_query clearfix" id="form_11">
					<div class="clearfix" style="">
						<p class="clearfix">
							<label>账号</label>
							<input type="text" name="username" id="username_1" value="${username}" />
						</p>
						<p class="clearfix">
							<label>姓名</label>
							<input type="text" name="name" id="name_1" value="${name}" />
						</p>
						<div class="clearfix selectMenu">
							<label>用户组</label>
								<select name="group" id="group_1" style="width: 160px;height: 40px;">
									<option value="">全部</option>
									<c:forEach items="${groups}" var="groups"><c:choose>  
  
										   <c:when test="${group_num eq groups.gid}"> 
										   		<option value="${groups.gid}" selected="selected"><c:out value="${groups.groupName}"></c:out></option>
										   </c:when>  
										     
										   <c:otherwise> 
												<option value="${groups.gid}"><c:out value="${groups.groupName}"></c:out></option>
										   </c:otherwise>  
										</c:choose>  
										
									</c:forEach>
								</select>
						</div>
						<div class="clearfix selectMenu">
							<label>用户状态</label>
							<select name="status" id="status_1" style="width: 160px;height: 40px;">
								<c:if test="${status==null}">
									<option value="">全部</option>
									<option value="1">启用</option>
									<option value="0">停用</option>
								</c:if>	
									<c:if test="${status==1}">
									<option value="">全部</option>
									<option value="1" selected="selected">启用</option>
									<option value="0">停用</option>
								</c:if>	
									<c:if test="${status==0}">
									<option value="">全部</option>
									<option value="1">启用</option>
									<option value="0" selected="selected">停用</option>
								</c:if>	
								</select>
						</div>
					</div>
					
					<div class="left dateselect clearfix" style="margin-bottom: 0">
						<label for="">交易日期</label>
						<p class="datep left">
			            	<input class="datainp wicon" id="inpstart" type="text" placeholder="请选择日期" name="beginTime" readonly="readonly" value="${beginTime}">
			           	</p >
			            <span class="left" style="line-height: 40px; padding: 0 10px;">至</span>
			        	<p class="datep left">
			            	 <input class="datainp wicon" id="inpend" type="text" placeholder="请选择日期" name="endTime" readonly="readonly" value="${endTime}">
			           	</p >
					</div>
					
					<div class="clearfix right btn">
						<input type="hidden" id="intopage" name="pagenumber" />
						<input type="submit" value="查询"/>
						<input type="button" value="重置" onclick="chongzhi();"/>
					</div>
				</form>
			</div>
			
			<div class="content tables">
			<shiro:hasPermission name="system:adduser">
				<div class="clearfix">
					<a href="javascript:void(0);" class="add clearfix left"><img src="/img/icon_add.png"/><span>新增用户</span></a>
				</div>
			</shiro:hasPermission>	
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>序号</th>
						<th>账号 </th>
						<th>姓名</th>
						<th>用户组</th>
						<th>状态</th>
						<th>创建人</th>
						<th>创建时间</th>
						<shiro:hasPermission name="system:edituser">
						<th>操作</th>
						</shiro:hasPermission>
					</tr>
					<c:forEach items="${sysusers}" var="sysusers" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${sysusers.username}</td>
							<td>${sysusers.name}</td>
							<td>${sysusers.sysgroupId.groupName}</td>
							<c:if test="${sysusers.status==1}">
								<td class="state"><a href="javascript:void(0);" class="qy current" onclick="return change_qy('${sysusers.sysId}','${sysusers.status}');">启用</a><a href="javascript:void(0);" class="ty" onclick="return change_ty('${sysusers.sysId}','${sysusers.status}');">停用</a></td>
							</c:if>
							<c:if test="${sysusers.status==0}">
								<td class="state"><a href="javascript:void(0);" class="qy" onclick="return change_qy('${sysusers.sysId}','${sysusers.status}');">启用</a><a href="javascript:void(0);" class="ty current" onclick="return change_ty('${sysusers.sysId}','${sysusers.status}');">停用</a></td>
							</c:if>
							<!-- <td class="state"><a href="javascript:void(0);" class="qy current">启用</a><a href="javascript:void(0);" class="ty">停用</a></td> -->
							<td>${sysusers.creater}</td>
							<td><%-- ${sysusers.createTime}  --%><fmt:formatDate value="${sysusers.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<shiro:hasPermission name="system:edituser">
							<td><span class="edit" onclick="return modifyxx('${sysusers.sysId}','${sysusers.username}','${sysusers.name}','${sysusers.sysgroupId.groupName}','${sysusers.status}','${sysusers.phone}','${sysusers.remarks}','${sysusers.sysgroupId.groupName}')">修改</span>|<span class="modify" onclick="return modifypw('${sysusers.sysId}','${sysusers.username}')">修改密码</span></td>
							</shiro:hasPermission>
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
		
		
		<div class="popup popup_add">
			<div class=" popup_cont">
				<h2 class="title">新增用户</h2>
				<form action="/system/addSysuser" method="post" id="form_1" style="position: relative">
				<span id="span1" style="position: absolute ; left:120px; top:0; line-height:30px;"></span>
					<div class="clearfix">
						<label for="">设置账号：</label>
						<input type="text" id="username" name="username" value="" maxlength="20" onblur=" return checkUsername();"/>
					</div>
					<div class="clearfix">
						<label for="">姓名：</label>
						<input type="text" id="uname" name="name" value="" onblur=" return checkname();"/>
					</div>
					<div class="clearfix">
						<label for="">手机号码：</label>
						<input type="text" id="uphone" name="phone" value="" onblur=" return checkphone();" />
					</div>
					<div class="clearfix selectMenu">
						<label for="">是否激活：</label>
						<div class="dropdownMenu" id="dropdownMenu3">
							<select name="status" style="width: 190px; height: 36px; margin-left: -21px;">
								<option value="1">是</option>
								<option value="2">否</option>
							</select>
							<!-- <div class="text">
								<input type="text" name="" id="" value="是" readonly="readonly" />
								<img class="down" src="img/arrow_down.png" alt="" />
								<img class="up" src="img/arrow_up.png" alt="" />
							</div>
							<div class="mask" id="mask3">
								<ul class="list">
									<li>是</li>
									<li>否</li>
								</ul>
							</div> -->
						</div>
					</div>
					<div class="clearfix selectMenu">
						<label>用户组：</label>
						<div class="dropdownMenu" id="dropdownMenu4">
						<select id="hxSelect" name="sysgroupId.gid" style="width: 190px; height: 36px; margin-left: -21px;">
							<c:forEach items="${groups}" var="groups">
								<option value="${groups.gid}" >${groups.groupName}</option>
							</c:forEach>
						</select>
							<!-- <div class="text">
								<input type="text" name="" id="" value="全部" readonly="readonly" />
								<img class="down" src="img/arrow_down.png" alt="" />
								<img class="up" src="img/arrow_up.png" alt="" />
							</div>
							<div class="mask" id="mask4">
								<ul class="list">
									<li>全部</li>
									<li>超级管理员</li>
									<li>普通管理员</li>
									<li>普通客服</li>
									<li>临时组</li>
								</ul>
							</div> -->
						</div>
					</div>
					<div class="clearfix">
						<label for="">备注：</label>
						<textarea name="remarks" id="uremarks" rows="" cols="" onblur=" return checkremarks();"></textarea>
					</div>
					<input class="confirm left" type="button" value="确认" onclick="return submitform()"/>
					<input class="cancle right " type="button" value="取消"/>
				</form>
				<a href="javascript:void(0)"class="close"><img src="/img/icon_close.png"/></a>
			</div>
			<div class="bg"></div>
		</div>
		
		<div class="popup popup_edit">
			<div class=" popup_cont">
				<h2 class="title">修改</h2>
				<span id="span_3" style="margin-left: 130px; height:14px;"></span>
				<form action="/system/updatemessage" method="post" id="form_3">
					<div class="clearfix">
						<input type="hidden" value="" id="uuid_2" name="sysId">
						<label for="">账号：</label>
						<span id="showusername"></span>
					</div>
					<div class="clearfix">
						<label for="">姓名：</label>
						<span id="showname"></span>
					</div>
					<div class="clearfix">
						<label for="">手机号码：</label>
						<input type="text" id="uphone_1" value="" name="phone" onblur="return checkuphone_1();"/>
					</div>
					<div class="clearfix selectMenu">
						<label for="">是否激活：</label>
						<div class="dropdownMenu" id="dropdownMenu5">
							<select name="status" style="width: 190px; height: 36px; margin-left: -21px;">
								<option value="1" id="op1">启用</option>
								<option value="0" id="op0">停用</option>
							</select>
						</div>
					</div>
					<div class="clearfix selectMenu">
						<label>用户组：</label>
						<div class="dropdownMenu" id="dropdownMenu6">
							<select name="sysgroupId.gid" id="group_id" style="width: 190px; height: 36px; margin-left: -21px;">
							<c:forEach items="${groups}" var="groups">
								<option value="${groups.gid}">${groups.groupName}</option>
							</c:forEach>
						</select>
						</div>
					</div>
					<div class="clearfix">
						<label for="">备注：</label>
						<textarea name="remarks" rows="" cols="" id="remarks_1" onblur="return checkremarks_1();"></textarea>
					</div>
					<a href="javascript:void(0)"class="close"><img src="/img/icon_close.png"/></a>
					<input class="confirm left" type="button" value="确认" onclick="return submitupdate();"/>
					<input class="cancle right " type="button" value="取消"/>
				</form>
			</div>
			<div class="bg"></div>
		</div>
		
		<div class="popup popup_modify" id="popup_modify_pw">
			<div class=" popup_cont">
				<h2 class="title">修改密码</h2>
				<span id="span_2" style="margin-left: 130px; height:14px;"></span>
				<form action="/system/update" method="post" id="form_2">
					<div class="clearfix">
						<label for="">账号：</label>
						<span id="showusercode"></span>
					</div>
					<input  id="uuid" name="sysId" value="" type="hidden"/>
					<div class="clearfix">
						<label for="">新密码：</label>
						<input type="password" id="upassword_1" name="password" placeholder="请输入新密码" onblur="return checkModifyPassword();"/>
					</div>
					<div class="clearfix">
						<label for="">重复密码：</label>
						<input type="password" id="upassword_2" placeholder="请再次输入密码" onblur="return checkModifyPassword1();"/>
					</div>
					<a href="javascript:void(0)"class="close"><img src="/img/icon_close.png"/></a>
					<input class="confirm left" type="button" value="确认" onclick="return formsubmit_2();"/>
					<input class="cancle right " type="button" value="取消"/>
				</form>
			</div>
			<div class="bg"></div>
		</div>
		<div class="popup popup_state">
			<div class="popup_cont">
				<h2 class="title">提示</h2>
				<p>确认是否修改该群组状态？</p >
				<div class="clearfix">
					<input class="confirm left" type="button" value="确认"/>
					<input class="cancle right " type="button" value="取消"/>
				</div>
			</div>
			<a href="#" class="close"><img src="img/icon_close.png"/></a>
			<div class="bg"></div>
		</div>
	</body>
	
	<script type="text/javascript" src="/js/dateselect.js"></script>
	<!-- <script type="text/javascript">
	$(function(){
		$(".state a").click(function () {
			$(this).addClass("se");
			var a=$(".state a.se");
			if($(this).hasClass("current")){
				$(this).attr('disabled',false); 
				$(this).removeClass("se");
			}
			else{
				$(".popup_state").show();
				$(".popup .confirm").click(function(){
					$(".popup_state").hide();
					a.addClass("current");
					a.siblings().removeClass("current");
					a.removeClass("se");
					a.attr('disabled',true);  
				});
			}
		});
	})
	</script> -->
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
 	//修改状态停用
 	function change_ty(uid,sta){
 		if(sta==1){
			var queding = confirm("确定停用修改吗");
			if(queding){ 
				window.location.href = "/system/changeTY?uid="+uid;
			}
 		}
 		
			/* $.ajax({
		    	url: 'changeTY',
		    	type: 'post',
		    	dataType: 'text',
		    	data: {"uid":uid,"status":0},      
		    	success: function(data) {	
	    		},
	    		error: function(){
	    			alert("error");
	    			return false;
	    		}
	    	}); */
		//location.reload(); 
	} 
	//修改状态启用
 	function change_qy(uid,sta){
		if(sta==0){
			var queding = confirm("确定修改吗");
			if(queding){
				window.location.href = "/system/changeQY?uid="+uid;
			}
		}
			/* $.ajax({
		    	url: 'changeQY',
		    	type: 'post',
		    	dataType: 'text',
		    	data: {"uid":uid,"status":1},      
		    	success: function(data) {	
	    		},
	    		error: function(){
	    			alert("error");
	    			return false;
	    		}
	    	}); */
		//location.reload();
	} 
	//修改信息提交
	function submitupdate(){
		if(checkuphone_1() && checkremarks_1){
			var form_3 = document.getElementById("form_3");
			form_3.submit();
		}else{
			alert("信息不正确")
		}
	}
	
	//修改信息弹框
	function modifyxx(uid,username,name,groupname,status,phone,remarks,gid){
		document.getElementById("uuid_2").value=uid;
		//document.getElementById("group_id").value=groupname;
		//$("#group_id").find("option[text='"+groupname+"']").attr("selected",true); 
		//$("#group_id").val(groupname);
		$("#showusername").html(username);
		$("#showname").html(name);
		$("#uphone_1").val(phone);
		$("#remarks_1").val(remarks);
		if(status == 1){
			$("#op1").attr("selected","selected");
		} else {
			$("#op0").attr("selected","selected");
		}
		
         $("#hxSelect option").each(function () {
             var txt = $(this).text(); //获取单个text
             var v = $(this).val();
             if(txt == gid){
            	 $("#hxSelect option[value="+v+"]").attr("selected", true);
             }
         });
		$(".popup_edit").show();
	}
	
	//验证修改时的手机号
	function checkuphone_1(){
		var uphone=document.getElementById("uphone_1").value;
		//var re = "/^1\d{10}$/";
		 if(!(/^1[34578]\d{9}$/.test(uphone))){
			 $("#span_3").html("<font style=\" color:red\">请输入正确的手机号</font>");
			 return false;
		 }else{
			 $("#span_3").html("");
		 }
		 return true;
	}
	
	//验证修改时的备注
	function checkremarks_1(){
		var uremarks=document.getElementById("remarks_1").value;
		if(uremarks==null || uremarks.trim() == ""){
			$("#span_3").html("<font style=\" color:red\">备注不能为空</font>");
			return false;
		}else{
			$("#span_3").html("");
		}
		return true;
	}
	
	//修改密码提交
	function formsubmit_2(){
		if(checkModifyPassword() && checkModifyPassword1()){
			var form_2 = document.getElementById("form_2");
			form_2.submit();
		}else{
			alert("请输入正确的密码")
		}
	}
	
	//验证修改新密码
	function checkModifyPassword(){
		var password=document.getElementById("upassword_1").value;
		if(password == null || password.trim() ==""){
			$("#span_2").html("<font style=\" color:red\">密码不能为空</font>");
			return false;
		}
		return true;
	}
	
	//验证确认密码
	function checkModifyPassword1(){
		var password_2=document.getElementById("upassword_2").value;	//确认密码
		var password_1=document.getElementById("upassword_1").value;	//新密码
		if(password_2 == null || password_2.trim() == ""){
			$("#span_2").html("<font style=\" color:red\">确认密码不能为空</font>");
			return false;
		}
		if(password_2 != password_1){
			$("#span_2").html("<font style=\" color:red\">密码不一致</font>");
			return false;
		}else{
			$("#span_2").html("");
		}
		return true;
	}
	//修改密码弹框
	function modifypw(pw,username){
		document.getElementById("uuid").value=pw;
		$("#showusercode").html(username);
		$(".popup_modify").show();	
		
	}
	
	//新增提交
	function submitform(){
		if(checkUsername()&&checkname()&&checkphone()&&checkremarks()){
			var form_1 = document.getElementById("form_1");
			form_1.submit();
		}else{
			alert("请填写完整的信息");
		}
	}
	//验证姓名
	function checkname(){
		var uname= document.getElementById("uname").value;
		//汉字验证
		var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
		if(uname ==null || uname.trim() == ""){
			$("#span1").html("<font style=\" color:red\">姓名不能为空</font>");
			return false;
		}
		if(reg.test(uname)){
			$("#span1").html("<font style=\" color:green\">姓名正确</font>");
		}else{
			$("#span1").html("<font style=\" color:red\">姓名必须为汉字</font>");
			return false;
		}
		return true;
	}
	//手机号码
	function checkphone(){
		var uphone=document.getElementById("uphone").value;
		//var re = "/^1\d{10}$/";
		 if(!(/^1[34578]\d{9}$/.test(uphone))){
			 $("#span1").html("<font style=\" color:red\">请输入正确的手机号</font>");
			 return false;
		 }else{
			 $("#span1").html("");
		 }
		 return true;
	}
	//备注
	function checkremarks(){
		var uremarks=document.getElementById("uremarks").value;
		if(uremarks==null || uremarks.trim() == ""){
			$("#span1").html("<font style=\" color:red\">备注不能为空</font>");
			return false;
		}else{
			$("#span1").html("");
		}
		return true;
	}
	//账号
	function checkUsername(){
		var username= document.getElementById("username").value;
		//汉字验证
		var regg = new RegExp("[\\u4E00-\\u9FFF]+","g");
		if(username==null||username.trim() == ""){
			$("#span1").html("<font style=\" color:red\">账号不能为空</font>")
			return false;
		}
		if(regg.test(username)){
			$("#span1").html("<font style=\" color:red\">账号不能为汉字</font>")
			return false;
		}
		if(username.length<5){
			$("#span1").html("<font style=\" color:red\">账号至少5位</font>")
			return false;
		}else{
			$("#span1").html("<font style=\" color:green\">账号可用</font>")
		}
		$.ajax({
	    	url: 'jquery',
	    	type: 'post',
	    	dataType: 'text',
	    	data: {"username":username},      
	    	success: function(data) {
					if(data =="yes"){
							$("#span1").html("<font style=\" color:green\">账号可用</font>")
					}
					if(data =="no"){
						$("#span1").html("<font style=\" color:red\">用户名不可用</font>");
						return false;
					}		
    		},
    		error: function(){
    			alert("error");
    			return false;
    		}
    	});
		return true;
    }
	//重置
	function chongzhi(){
		document.getElementById("username_1").value="";
		document.getElementById("name_1").value="";
		document.getElementById("group_1").value="";
		document.getElementById("status_1").value="";
		document.getElementById("inpstart").value="";
		document.getElementById("inpend").value="";
	}
    
	</script>
</html>