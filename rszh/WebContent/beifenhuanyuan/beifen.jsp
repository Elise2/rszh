<%@ page language="java" import="java.util.*,servlet.beifen_huanyuan.*" pageEncoding="UTF-8"%> 
<%@ page import = "java.util.Properties" %>
<%@ page import = "java.io.InputStream" %>
<%@ page import = "jdbc.*" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>数据库备份测试</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <!--  
    <link rel="stylesheet" type="text/css" href="styles.css">  
    -->  
  
  </head>  
    
  <body>  
<%  
Command com = new Command();  
Properties properties = new Properties();
InputStream inStream = JdbcTools.class.getClassLoader()
		.getResourceAsStream("jdbc.properties");
properties.load(inStream);
String username = properties.getProperty("user");//MySQL数据库的用户名  
String password = properties.getProperty("password");//MySQL数据库的密码  
String database = "project";//数据库名字  
String url = "e:/project.sql";//备份的目的地址  
boolean check = com.backupDatabase(username, password, database, url);  
if(check){  
 %>  
 如需备份请按确定键：
 <form action="/beifenServlet" method = "post">
 <input type = "submit" value = "确定">
 </form>
 <%}  %> 
  </body>  
</html>  