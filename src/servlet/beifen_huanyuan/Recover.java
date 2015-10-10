package servlet.beifen_huanyuan;

import java.io.*;   
import java.lang.*; 
import dao.*;
  
/*   
* 还原MySql数据库   
* */   
public class Recover extends Dao{  
public boolean loadDelete(String username, String password,String dataName){    
	    String stmt1 = "mysql  -u "+username+" -p"+password+" drop database "+dataName; 
	    try { 
	    Runtime.getRuntime().exec("cmd /c "+stmt1);
	    //dao.deleteDatabase("project"); 
	    System.out.println("数据已被删除"+stmt1);   
	    } catch (IOException e) {   
	    e.printStackTrace();   
	    }   
	    return true;  
	}  
public boolean load(String username, String password,String dataName,String path){  
    String filepath = path; // 备份的路径地址    
      //新建数据库test   
      String stmt1 = "mysql -u "+username+" -p"+password+" create database project"; 
      String stmt2 = "mysql -u "+username+" -p"+password+" "+dataName +"< " + filepath;   
      String cmd = "cmd"+" /c "+stmt2; 
      System.out.println("stmt1"+stmt1);
      System.out.println("cmd"+cmd);
      try { 
      Runtime.getRuntime().exec("cmd /c "+stmt1); 
     //dao.createDatabase("project");
      Runtime.getRuntime().exec(cmd); 
      System.out.println("数据已从 " + filepath + " 导入到数据库中");   
      } catch (IOException e) {   
      e.printStackTrace();   
      }   
      return true;  
}  
}   