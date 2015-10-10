<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="bean.*" import="rsjl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/dtree.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.sorted.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/ckform.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<script>
$(function () {
	$('#add').click(function(){
		//window.location.href="/jsp/rsjl/addShowPerson.jsp";
	 });
	$('#mod').click(function(){
		window.location.href="add.html";
 	});
	$("#del").click(function(){
		var len = $("#mainTable input:checked").length;
		if(len == 0){
			alert("请选择列！");
		}else if(len >= 2){
			alert("只能选择一列");
		}else if( len == 1){
			var id = $("input:checked").val();
			var tableName = $("#tableName").val();
			window.location.href="/deleteJl?id="+id+"&tableName="+tableName;
		}
	});
	$("#mod").click(function(){
		var len = $("#mainTable input:checked").length;
		if(len == 0){
			alert("请选择列！");
			return false;
		}else if(len >= 2){
			alert("只能选择一列");
			return false;
		}else if( len == 1){
			var id = $("input:checked").val();
			var tableName = $("#tableName").val();
			window.location.href="/jsp/rsjl/mod.jsp?id="+id+"&tableName="+tableName;
		}
	});
	
});
</script>
<title>Insert title here</title>
<style type="text/css">
#mainTable th{
min-width:100px;
white-space:nowrap;
}
#mainTable td{
min-width:100px;
white-space:nowrap;
}
</style>
</head>
<body>
	<%
		List<CommentObject> tableNameList = (List<CommentObject>)request.getAttribute("tableNameList");
	    List<CommentObject> dataList = (List<CommentObject>)request.getAttribute("dataList");
		List<String> renyuanleibieList = (List<String>)request.getAttribute("renyuanleibieList");
		List<CommentObject> rowList = (List<CommentObject>)request.getAttribute("rowList");
		List<CommentObject> bumenList = (List<CommentObject>)request.getAttribute("bumenList");
		List<CommentObject> list = (List<CommentObject>)request.getAttribute("list");
		System.out.println("tableNameList :"+tableNameList);
		System.out.println("renyuanleibieList :"+renyuanleibieList);
		System.out.println("rowList :"+rowList);
		System.out.println("bumenList :"+bumenList);
		System.out.println("list :"+list);
		System.out.println("dataList :"+dataList);
		
	%>
	<%for(int i=0;i<tableNameList.size();i++){ %>
	<img src="/images/folder.gif"/>
	<a style="font-size:18px;" href="/jlszServletRequest?tableName=<%=tableNameList.get(i).getValues().get("table_name")%>"><%=tableNameList.get(i).getValues().get("table_name")%></a>&nbsp;&nbsp;

	<%} %>
	<%
	String nn=(String)request.getAttribute("table");
	System.out.println("nn^^^"+nn);
	request.setAttribute("table", nn);
	%>
	<div style="margin-top: 2px">
	<form id="table_tool"
		action="/jsp/rsjl/addShowPerson.jsp" method="post">
		<input type="hidden" name="tableName" value="<%=nn%>" id="tableName"> 
		<input type="hidden" name="action" value="">
		<input type="submit" class="btn btn-success" value="添加" id="add" />
		<button type="button" class="btn btn-success" id="mod">修改</button>
		<button type="button" class="btn btn-success" id="del">删除</button>
		<button type="button" class="btn btn-success" id="daoru">导入</button>
		<button type="button" class="btn btn-success" id="daochu">导出</button>
		<button type="button" class="btn btn-success" id="">导入图片或链接</button>
		<button type="button" class="btn btn-success" id="">导出图片或链接</button>
		
	</form>
	</div>
		<div id="contentLeft" style="font-size: 14px; width:15%; height: 440px;margin-top:0px; border: 1px solid #DDDDDD; overflow:scroll;float:left;">
		<form method="post" action="">
			<select name="workersGroup">
				<option value="syry" selected="selected">所有人员</option>
				<option value="zzry">在职人员</option>
				<option value="lzry">离职人员</option>
				<option value="dpry">待聘人员</option>
			</select>
		</form>
		<div class="dtree">
			<script type="text/javascript">
					d = new dTree('d');
					d.add(1, -1, '公司名称'); 
		</script>
			<%
				for (int i = 0; i <bumenList.size(); i++) {
						Object id =bumenList.get(i).getValues().get("id");
						System.out.println("id："+id);
						Object parentId =bumenList.get(i).getValues()
								.get("parent_id");
						System.out.println("parentId："+parentId);
						String name = (String)bumenList.get(i).getValues()
								.get("depart");
			%>
			<script type="text/javascript">
				d.add(<%=id%>,<%=parentId%>,"<%=name%>", '/jlszServletRequest?部门=<%=name%>');
			</script>
			<%
				}
			%>
			<script type="text/javascript">
				document.write(d);
			</script>
		</div>
	</div>
	
	
	<div id="contentRight" style="width:81%;height:440px;font-size: 14px; margin-left:0px;margin-top:0px;border: 1px solid #DDDDDD; overflow:scroll;">
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th id="checkCol"><input type="checkbox" name="check" />
		<th>编号</th>
		<th>姓名</th>
		<th>部门</th>
		<%
		for(int i = 0; i < rowList.size();i++){
			String nameString = rowList.get(i).getValues().get("row_name")+"";
			if(nameString.equals("id")){
				rowList.remove(i);
				break;
			}
		}
		for(int i = 0; i < rowList.size();i++){
			String nameString = rowList.get(i).getValues().get("row_name")+"";
			if(nameString.equals("编号")){
				rowList.remove(i);
				break;
			}
		}
		for(int i=0;i<rowList.size();i++){ %>
		<th><%=rowList.get(i).getValues().get("row_name")%></th>
		<%} %>

    </tr>
    </thead>
	     <%	
	 	 List<CommentObject> list1 = LHCX.queryList(nn);
	     for (int i =0; i < list1.size(); i++) {
	    	 CommentObject com = list1.get(i);
	    	 System.out.println("com :"+com);
	    	String bianhao = com.getValues().get("编号")+"";
	    	String id = com.getValues().get("id")+"";
	    	%>
	    	<tr>
	    	<th><%= i+1 %></th>
			<td><input type="checkbox" value="<%= id %>"/></td> 
			<td><%= bianhao %></td>
			<td><%= com.getValues().get("姓名")+"" %></td>
			<td><%= com.getValues().get("部门")+"" %></td>
	    	<%
	    	for(int j=0;j<rowList.size();j++){
	    		%><td><%=com.getValues().get(rowList.get(j).getValues().get("row_name"))%></td><%
	    	}
	    	%>
			</tr>
		<%
		}
	%>
	</table>
	</div>
</body>
</html>