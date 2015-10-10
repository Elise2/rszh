<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="bean.CommentObject"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/dtree.js"></script>
<title>导入到</title>
</head>

<%
	List<CommentObject>  bumenList = (List<CommentObject>)request.getAttribute("bumenList");
    System.out.println("departList"+bumenList);
     /*  String departname=(String)request.getAttribute("departname");  
      System.out.println("departname"+departname); */
    
  	String departname=request.getParameter("部门");
  	//request.setAttribute("departname", departname);
  	//System.out.println("部门名1："+request.getParameter("部门")); %>
<body>
	<h3>请继续选择类别和部门</h3>
	<form method="post" action="lastimportServlet?departname=<%=departname%>">
	

		<select name="workersGroup">
			<option value="syry" selected="selected">所有人员</option>
			<option value="zzry">在职人员</option>
			<option value="lzry">离职人员</option>
			<option value="dpry">待聘人员</option>
		</select> 
		<input name="btn" type="submit" value="确认" />
	</form>
	<div class="dtree">
		<script type="text/javascript">
					d = new dTree('d');
					d.add(1, -1, '公司名称'); 
					</script>
			<%
				for (int i = 0; i <bumenList.size(); i++) {
					Object id =bumenList.get(i).getValues().get("id");
					Object parentId =bumenList.get(i).getValues()
							.get("parent_id");
					String name = (String)bumenList.get(i).getValues()
							.get("depart");
			%>
			<script type="text/javascript">
			d.add(<%=id%>,<%=parentId%>,"<%=name%>", 'importgoServlet?部门=<%=name%>');
			</script>
				<%
									}	
			
				%>
				<script type="text/javascript">
					document.write(d);
				</script>
	</div>
	<%
	request.getRequestDispatcher("/showPersonal").forward(request, response);
	%>
</body>
</html>