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
<script>
    $(function () {
		$('#add').click(function(){
			var tableName = $("#tableName").val();
			window.location.href="jlszAddRowRequest?tableName="+tableName;
		 });
		$('#backList').click(function(){
			window.location.href="/jlszServlet";
	 	});
		$("#del").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var tableName = $("#tableName").val();
				window.location.href="jlszDelRow?tableName="+tableName+"&rowName="+val;
			}
		});
		$("#mod").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var tableName = $("#tableName").val();
				var rowName = $("#"+val+" td[name='rowName']").text();
				var rowType = $("#"+val+" td[name='rowType']").text();
				var comment = $("#"+val+" td[name='comment']").text();
				window.location.href="/jlszModRowRequest?tableName="+tableName+"&rowName="+val+"&rowType="+rowType+"&comment="+comment;
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
				var rowName = $("#"+val+" td[name='rowName']").text();
				$("#table_tool :input[name='rowName']").val(rowName);
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
				var rowName = $("#"+val+" td[name='rowName']").text();
				$("#table_tool :input[name='rowName']").val(rowName);
				$("#table_tool :input[name='action']").val("down");
			}
		});
    });
</script>
</head>
<body>
	<%
		String tableName = request.getParameter("tableName");
		List<CommentObject> list = (List<CommentObject>)request.getAttribute("list");
		System.out.print("tableDetialList："+list);
	%>
	<h4>您现在的位置：<%=tableName%>的详细信息</h4>
	<form class="form-inline definewidth m20" id="table_tool" action="jlszUpandDown" method="post">    
   		 <input type="hidden" name="tableName" value="<%=tableName%>" id="tableName">
   		 <input type="hidden" name="rowName" value="">
		 <input type="hidden" name="action" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="mod">修改</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
   		 <input type="submit" class="btn btn-success" value="上移"  id="up" />
   		 <input type="submit" class="btn btn-success" value="下移" id="down" />
   		 <button type="button" class="btn btn-success" id="backList">返回记录列表</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th id="checkCol"><input type="checkbox" name="check" />
		<th>列名</th>
		<th>数据类型</th>
		<th>备注</th>
    </tr>
    </thead>
	     <%
					for (int i = 1; i < list.size(); i++) {
						String rowName = (String)list.get(i).getValues().get("row_name");
						String rowType = (String)list.get(i).getValues().get("row_type");
						String comment = (String)list.get(i).getValues().get("comment");
				%>
			<tr id="<%=rowName%>">
			<th><%= i %></th>
			<td><input type="checkbox" value="<%=rowName%>"/></td>
			<td name="rowName"><%=rowName%></td>
			<td name="rowType"><%=rowType%></td>
			<td name="comment"><%
				if(comment == null|| comment ==""){
					%>无备注信息<%
				}else{
					%><%=comment%><%
				}
			%></td>
		</tr>
		<%
		}
	%>
	</table>
</body>
</html>