<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
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
        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
<script type="text/javascript">
    $(function () {
		$('#add').click(function(){
			var val = $("#table_tool input[name='parentId']").val();
			window.location.href="/jsp/rssz/bmsz/add.jsp?parentId="+val;
		 });
		$('#mod').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='parentId']").val();
				window.location.href="/jsp/rssz/bmsz/mod.jsp?id="+val+"&parentId="+pid;
			}
		 });
		$('#del').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='parentId']").val();
				window.location.href="/bmDao?action=del&id="+val+"&parentId="+pid;
			}
		 });
		$('#up').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='parentId']").val();
				
				window.location.href="/bmDao?action=up&id="+val+"&parentId="+pid;
			}
		 });
		$('#down').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='parentId']").val();
				window.location.href="/bmDao?action=down&id="+val+"&parentId="+pid;
			}
		 });
    });
</script>
</head>
<body>

	<%
		List<CommentObject> bumenList = (List<CommentObject>)request.getAttribute("bumenList");
		String pId = request.getParameter("parentId");
	%>

	<form class="form-inline definewidth m20" id="table_tool" action="" method="post">    
   		 <input type="hidden" name="id" value="">
   		 <input type="hidden" name="parentId" value="<%=pId%>">
		 <input type="hidden" name="action" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="mod">修改</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
   		 <button type="button" class="btn btn-success" id="up" >上移</button>
   		 <button type="button" class="btn btn-success" id="down" >下移</button>
	</form>
	<%
		if(bumenList.isEmpty()){
			%><h2>无部门</h2><%
		}else{
			%>
			<table class="table table-bordered table-hover definewidth m10" id="mainTable">
			    <thead>
			    <tr>
			        <th>序号</th>
					<th id="checkCol"><input type="checkbox" name="check" />
					<th>部门名</th>
					<th>操作</th>
			    </tr>
			    </thead>
				    <%
				    	for(int i =0;i < bumenList.size();i++){
				    		Object id  = bumenList.get(i).getValues().get("id");
				    		Object depart = bumenList.get(i).getValues().get("depart");
				    		Object parentId = bumenList.get(i).getValues().get("parent_id");
				    		%>
				    		<tr>
				    		<td><%= i+1 %></td>
							<td><input type="checkbox" name="check" value="<%= id %>" /></td>
							<td><%= depart %></td>
							<td><a href="/bmszServlet?parentId=<%= id %>">查看下级</a></td>
							</tr>
				    		<%
				    	}
				    %>
			</table>
			<%
		}
	%>
</body>
</html>