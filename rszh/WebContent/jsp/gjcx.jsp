<%@page import="bean.CommentObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
</script>
</head>
<body>
	<%
		
	%>
	<form id="main_serch_area" action="gaojiSerch" method="post">
		<div id="mouban">
			<select name="ziduanName">
				<option value="姓名">姓名</option>
				<option value="年龄">年龄</option>
				<option value="部门">部门</option>
			</select>
			<select name="tiaojian">
				<option value="=">等于</optiopn>
				<option value="like">包含</optiopn>
				<option value=">">大于</optiopn>
				<option value="<">小于</optiopn>
			</select>
			<input type="text" name="keyword" value=""> 
			<input type="radio" name="fangshi" value="and">并且
			<input type="radio" name="fangshi" value="or">或者
	  </div>
		<br id="before">
		<button class="btn btn-success" id="go_on">继续</button>
		<input type="submit" value="提交">
	</form>
</body>
</html>