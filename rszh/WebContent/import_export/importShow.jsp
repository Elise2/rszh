<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>导入</title>
<link href="css/importShow.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#mainTable tr:even").css("background", "#F3F3F3");
	});
</script>
</head>
<body>
	<%
		//获得传过来的值
		String flag = request.getParameter("flag");
		List<String> columnName = (List<String>) request
				.getAttribute("columnName");
		List<Vector> data = (List<Vector>) request.getAttribute("data");
		
		 session.setAttribute("columnName",columnName);
		 session.setAttribute("data",data);
	     /* System.out.println( (List<String>) request
					.getAttribute("columnName1")); */
	%>
	<%
		//以表单的形式输出导入的数据
	%>
	   <form  action="importgoServlet"  method="post">
	    <input  type="submit"   value="导入"/>
	    </form> 

	<div id="drData">
		<table border="0" bgcolor="gray" cellspacing="1" cellpadding="5"
			id="drTable">
			<tr>
				<%
					for (int i = 0; i < columnName.size(); i++) {
				%>
				<td><%=columnName.get(i)%></td>
				<%
					}
				%>
			</tr>
			<%
				for (int j = 0; j < data.size(); j++) {
			%>
			<tr>
				<%
					for (int m = 0; m < columnName.size(); m++) {
				%>
				<td><%=data.get(j).get(m)%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	 
<%-- 	<button onclick="location.href='importgoServlet?columnname=<%=columnName%>'" type="submit">导入</button>
 --%>	
</body>
</html>