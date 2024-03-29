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
    	$(function(){
    		$('#add').click(function(){
    			window.location.href="/jsp/rssz/jlsz/addNewTable.jsp";
    		 });
    		$('#mod').click(function(){
    			var len = $("#mainTable input:checked").length;
    			if(len == 0){
    				alert("请选择列！");
    			}else if(len >= 2){
    				alert("只能选择一列");
    			}else if( len == 1){
    				var val = $("input:checked").val();
    				window.location.href="/jsp/rssz/jlsz/modTable.jsp?tableName="+val;
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
    				window.location.href="/tableDao?action=del&tableName="+val;
    			}
    		 });
    		$("#up").click(function(){
    			var len = $("#mainTable input:checked").length;
    			if(len == 0){
    				alert("请选择列！");
    			}else if(len >= 2){
    				alert("只能选择一列");
    			}else if( len == 1){
    				var val = $("input:checked").val();
    				$("#table_tool :input[name='tableName']").val(val);
    				$("#table_tool :input[name='action']").val("up");
    			}
    		});
    		
    		$("#down").click(function(){
    			var len = $("#mainTable input:checked").length;
    			if(len == 0){
    				alert("请选择列！");
    			}else if(len >= 2){
    				alert("只能选择一列");
    			}else if( len == 1){
    				var val = $("input:checked").val();
    				$("#table_tool :input[name='tableName']").val(val);
    				$("#table_tool :input[name='action']").val("down");
    			}
    		});
        });
    </script>
</head>
<body>

	<%
		//获得main_table中除档案表（personal）之外的其他表信息
			List<CommentObject> tableInfo = (List<CommentObject>) request
					.getAttribute("tableInfo");
			System.out.println("人事设置主界面获得的表信息： " + tableInfo);
	%>

	<form class="form-inline definewidth m20" id="table_tool" action="/tableDao" method="post">    
   		 <input type="hidden" name="tableName" value="">
		 <input type="hidden" name="action" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="mod">修改</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
   		 <input type="submit" class="btn btn-success" value="上移"  id="up" />
   		 <input type="submit" class="btn btn-success" value="下移" id="down" />
	</form>
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
        <th id="checkCol"><input type="checkbox" name="check" />
        <th>记录表名</th>
        <th>操作</th>
    </tr>
    </thead>   
    <%
	for (int i = 0; i < tableInfo.size(); i++) {
		String tableName = (String) tableInfo.get(i).getValues()
				.get("table_name");
			%>
			<tr>
			<td><%= i %></td>
			<td><input type="checkbox" value="<%=tableName%>"/></td>
			<td><%=tableName%></td>
			<td><a href="/showDetialsServlet?tableName=<%=tableName%>">查看详情</a></td>
			</tr>
			<%
	}
	%>	
</table>
</body>
</html>