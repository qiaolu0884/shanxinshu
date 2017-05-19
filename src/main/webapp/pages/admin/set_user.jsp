<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/jedate.css"/>
		<link rel="stylesheet" type="text/css" href="/css/checkbox.css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="/css/system_set.css"/>
		<title>善心树管理平台--系统设置</title>
		<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/jedate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/system_set.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/popup.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="/js/checkbox.min.js" charset="utf-8"></script>
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
				<div class="right role">
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
				<a class="current" href="/system/groupList">设置用户组</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:syslog">
				<a href="/system/systemlog">操作日志</a>
			</shiro:hasPermission>
			</div>
			
			<div class="content tables">
				<shiro:hasPermission name="system:addgrade">
				<div class="clearfix">
					<a href="javascript:void(0);" class="add clearfix left"><img src="/img/icon_add.png"/><span>新增用户组</span></a>
				</div>
				</shiro:hasPermission>
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>序号</th>
						<th>组名称 </th>
						<th>描述</th>
						<th>状态</th>
						<shiro:hasPermission name="system:editgrade">
						<th>操作</th>
						</shiro:hasPermission>
					</tr>
					<c:forEach items="${groups}" var="groups" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${groups.groupName}</td>
							<td>${groups.remarks}</td>
							<c:if test="${groups.status==1}">
								<td class="state"><a href="javascript:void(0);" class="qy current" onclick="return change_qy('${groups.gid}');">启用</a><a href="javascript:void(0);" class="ty" onclick="return change_ty('${groups.gid}');">停用</a></td>
							</c:if>
							<c:if test="${groups.status==0}">
								<td class="state"><a href="javascript:void(0);" class="qy" onclick="return change_qy('${groups.gid}');">启用</a><a href="javascript:void(0);" class="ty current" onclick="return change_ty('${groups.gid}');">停用</a></td>
							</c:if>
							<shiro:hasPermission name="system:editgrade">
								<td><span class="edit" onclick="return modify('${groups.gid}','${groups.groupName}','${groups.status}','${groups.groupPrivilegeIds}')">编辑</span></td>
							</shiro:hasPermission>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pages clearfix">
					<div class="left clearfix pagebtn">
						<span class="left">第<b>${pagenumber}</b>页</span>
						<ul class=" left">
							<li><a href=""><img src="/img/arrow_left1.png"/></a></li>
							<li><a href=""><img src="/img/arrow_left2.png"/></a></li>
							<li><a href=""><img src="/img/arrow_right2.png"/></a></li>
							<li><a href=""><img src="/img/arrow_right1.png"/></a></li>
						</ul>
						<span class="right">共<em>${pagesum}</em>页</span>
					</div>
					<p class="right">
						<span class="left">跳转到</span>
						<input class="left num_text" type="text" name="" id="" value="" placeholder="1" />
						<input type="button" id="" value="GO" />
					</p>
				</div>
			
			</div>
		</div>
		
		
		<div class="popup popup_add add_group">
			<div class=" popup_cont">
				<h2 class="title">新增群组</h2>
				<form action="/system/addGroup" method="post" id="form_1" style="position: relative">
					<span id="span_1" style="position: absolute ; left:120px; top:0; line-height:30px;"></span>
					<div class="clearfix">
						<label for="">群组名称：</label>
						<input type="text" id="groupName" value="" name="groupName" onblur="return checkgroupName();"/>
					</div>
					<div class="clearfix selectMenu">
						<label for="">状态：</label>
						<div class="dropdownMenu" id="dropdownMenu">
							<select name="status" style="width: 190px; height: 36px; margin-left: -21px;">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select>
						</div>
					</div>
					<div class="clearfix">
						<label for="">备注：</label>
						<textarea rows="" cols="" style="height: 34px" name="remarks" id="addRemarks" onblur="return checkgroupremarks();"></textarea>
					</div>
				<!-- </form> -->
				<h2 class="title" style="margin-top: 15px;">群组授权</h2>
				<!-- <form action="" method="post" id="form_2" style="padding-top:15px;"> -->
					<div class="clearfix">
						<ul  id="checkbox1">
							<li class="clearfix">
								<span>模块</span>
								<div class="left" style="text-align: center;">
									权限选项
								</div>
							</li>
							<li class="clearfix">
								<span>首页</span>
								<div>
								<c:forEach items="${sysp1 }" var="sys">
									<p><span class="check_span"><input type="checkbox" value="${sys.id}" name="sysp" /></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<span>商户信息</span>
								<div>
								<c:forEach items="${sysp2 }" var="sys">
									<p><span class="check_span"><input type="checkbox" value="${sys.id}" name="sysp" /></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<span>交易管理</span>
								<div>
								<c:forEach items="${sysp3 }" var="sys">
									<p><span class="check_span"><input type="checkbox" value="${sys.id}" name="sysp" /></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<span style="line-height: 70px;">系统设置</span>
								<div>
								<c:forEach items="${sysp4 }" var="sys">
									<p><span class="check_span"><input type="checkbox" value="${sys.id}" name="sysp" /></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<p class="clearfix"><span class="check_span"><input type="checkbox" id="checkAll" /></span>全选</p >
								<input class="confirm" type="button" id="bt1" value="确认"/>
								<input class="cancle right " type="button" value="取消"/>	
							</li>
						</ul>
					</div>
				</form>
				<a href="javascript:void(0)"class="close"><img src="/img/icon_close.png"/></a>
			</div>
			<div class="bg"></div>
		</div>
		
		<div class="popup popup_edit add_group" id="editQz">
			<div class=" popup_cont">
				<h2 class="title">编辑群组</h2>
				<form action="/system/updateGroup" method="post" id="form_3" style="position: relative">
					<span id="span_2" style="position: absolute ; left:120px; top:0; line-height:30px;"></span>
					<input type="hidden" name="gid" id="editGid">
					<div class="clearfix">
						<label for="">群组名称：</label>
						<input type="text" id="group_name" value="${currentGroup.groupName }" onblur="return checkgroupName_2" readonly="readonly"/>
					</div>
					<div class="clearfix selectMenu">
						<label for="">状态：</label>
						<div class="dropdownMenu" id="dropdownMenu1">
							<select name="status" style="width: 190px; height: 36px; margin-left: -21px;">
								<option value="1" id="op1" <c:if test="${currentGroup.status == 1 }">selected="selected"</c:if>>启用</option>
								<option value="0" id="op0" <c:if test="${currentGroup.status == 0 }">selected="selected"</c:if>>禁用</option>
							</select>
						</div>
					</div>
				<!-- </form> -->
				<h2 class="title" style="margin-top: 25px;">群组授权</h2>
				<!-- <form action="updateGroup" method="post" id="form_4"> -->
					<div class="clearfix">
						<ul  id="checkbox">
							<li class="clearfix">
								<span>模块</span>
								<div class="left" style="text-align: center;">
									权限选项
								</div>
							</li>
							<li class="clearfix">
								<span>首页</span>
								<div>
								<c:forEach items="${sysp1 }" var="sys">
									<p><span id="editSP" class="check_span"><input type="checkbox" value="${sys.id}" name="sysp2" id="editCB"/></span>${sys.name}</p > 
								</c:forEach> 
								</div>
							</li>
							<li class="clearfix">
								<span>商户信息</span>
								<div>
								<c:forEach items="${sysp2 }" var="sys">
									<p><span id="editSP" class="check_span"><input type="checkbox" value="${sys.id}" name="sysp2" id="editCB"/></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<span>交易管理</span>
								<div>
								<c:forEach items="${sysp3 }" var="sys">
									<p><span id="editSP" class="check_span"><input type="checkbox" value="${sys.id}" name="sysp2" id="editCB"/></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<span style="line-height: 70px;">系统设置</span>
								<div>
								<c:forEach items="${sysp4}" var="sys">
									<p><span id="editSP" class="check_span"><input type="checkbox" value="${sys.id}" name="sysp2" id="editCB"/></span>${sys.name}</p > 
								</c:forEach>
								</div>
							</li>
							<li class="clearfix">
								<p class="clearfix"><span class="check_span"><input type="checkbox" id="checkAll" /></span>全选</p >
								<input class="confirm" type="button" id="bt2" value="确认"/>
								<input class="cancle right " type="button" value="取消"/>	
							</li>
						</ul>
					</div>
					
				</form>
				<a href="javascript:void(0)"class="close"><img src="/img/icon_close.png"/></a>
			</div>
			<div class="bg"></div>
		</div>
			<script>
			$(function(){
				$("#checkbox").selectCheck();
				$("#checkbox1").selectCheck();
				
				$("#bt2").click(function(){
					$("#form_3").submit();
				})
			});
		</script>
	</body>
	<script type="text/javascript">
	//add群组备注验证
		function checkgroupremarks(){
			var groupremarks = document.getElementById("addRemarks").value;
			if(groupremarks==null || groupremarks.trim()==""){
				$("#span_1").html("<font style=\" color:red\">备注不能为空</font>");
			}
			return true;
		}
	
		//add验证群组名字
		function checkgroupName(){
			var groupName = document.getElementById("groupName").value;
			var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
			if(groupName ==null || groupName.trim() == ""){
				$("#span_1").html("<font style=\" color:red\">群组名不能为空</font>");
				return false;
			}
			if(reg.test(groupName)){
				$("#span_1").html("");
			}else{
				$("#span_1").html("<font style=\" color:red\">群组必须为汉字</font>");
				return false;
			}
			$.ajax({
		    	url: '/system/jquerygroupName',
		    	type: 'post',
		    	dataType: 'text',
		    	data: {"groupName":groupName},      
		    	success: function(data) {
						if(data =="yes"){
								$("#span_1").html("<font style=\" color:green\">名称可用</font>")
						}
						if(data =="no"){
							$("#span_1").html("<font style=\" color:red\">名称重复</font>");
							return false;
						}		
	    		},
	    		error: function(){
	    			//alert("error");
	    			return false;
	    		}
	    	});
			return true;
		}
		
		//提交新增群组和权限表单
		$("#bt1").click(function(){
			var groupName = document.getElementById("groupName").value;
			var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
			var groupremarks = document.getElementById("addRemarks").value;
			if(groupName == null || groupName.trim() == ""){
				$("#span_1").html("<font style=\" color:red\">群组名不能为空</font>");
			} else if(!reg.test(groupName)){
				$("#span_1").html("<font style=\" color:red\">群组必须为汉字</font>");
			} else if(groupremarks==null || groupremarks.trim()==""){
				$("#span_1").html("<font style=\" color:red\">备注不能为空</font>");
			} else {
				$("#form_1").submit();
			}
		})
	
		//修改状态停用
		function change_ty(gid){
			var queding = confirm("确定停用修改吗");

			if(queding){ 
			$.ajax({
		    	url: '/system/changegroupTY',
		    	type: 'post',
		    	dataType: 'text',
		    	data: {"gid":gid,"status":0},      
		    	success: function(data) {	
		    		alert("停用成功");
	    		},
	    		error: function(){
	    			alert("error");
	    			return false;
	    		}
	    	});
			}
			//location.reload(); 
		}
		//修改状态启用
		function change_qy(gid){
			var queding = confirm("确定启用修改吗");

			if(queding){
			$.ajax({
		    	url: '/system/changegroupQY',
		    	type: 'post',
		    	dataType: 'text',
		    	data: {"gid":gid,"status":1},      
		    	success: function(data) {	
		    		alert("启用成功");
	    		},
	    		error: function(){
	    			alert("error");
	    			return false;
	    		}
	    	});
			}
			//location.reload();
		}
		
		//修改群组
		function modify(gid,gname,status,gprids){
			$("#editSP").attr("class","check_span");
			$("#editCB").attr("selected",false);
			document.getElementById("group_name").value=gname;
			$("#group_name").html(gname);
			$("#editGid").val(gid);
			if(status==1){
				$("#op1").attr("selected","selected");
			} else {
				$("#op0").attr("selected","selected");
			}
// 			alert(gprids);
			gprids = gprids.replace("[","");
			gprids = gprids.replace("]","");
			var arr = gprids.split(",");
			var pris = new Array();
			
			$("[id='editCB']").each(function(){
				var a = $(this).val();
				for (var i = 0; i < arr.length; i++) {
// 					alert(a.length+":"+arr[i].length);
					arr[i] = arr[i].trim();
					//alert(a+":"+arr[i]);
					//alert(a == arr[i])
 					if(arr[i] == a){
						$(this).attr("checked",true);
						$(this).parent().addClass("check_span--checked");
 						pris.push(a);
 					}
				}
			})
			//$("#showname").html(name);
			//$(".popup_edit").show();
		}
		
		//提交修改群组和权限编辑表单提交
		/* function formsubmit(){
			var form_3 = document.getElementById("form_3");
			var form_4 = document.getElementById("form_4");
			form_3.submit();
			form_4.submit();
		} */

		function checkAll(){
			var CheckAll=document.getElementById('cb');	//全选
			for(i=0;i<CheckBox.length;i++){
				CheckBox[i].checked=true;
			}
		}
	
		
	</script>
</html>