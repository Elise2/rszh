package rsjl;

import java.sql.Connection;
import java.util.List;

import dao.Dao;

import jdbc.JdbcTools;

import bean.CommentObject;

public class LHCX extends Dao{
	public static List<CommentObject> queryList(String tableName,String depart,String renyuan){
		String sql="select * from  "+tableName+"  where 编号 in (select 编号 from personal where 部门=? and 人员类别=?);";
		System.out.println("sql"+sql);
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, depart,renyuan);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
	
	public static List<CommentObject> queryList(String tableName,String depart){
		String sql="select * from  "+tableName+"  where 编号 in (select 编号 from personal where 部门=?);";
		System.out.println("sql"+sql);
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, depart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
	
	public static List<CommentObject> queryList(String tableName){
		String sql="select 姓名,部门,"+tableName+".* from personal,"+tableName+"   where personal.编号="+tableName+".编号;";
		System.out.println("sql"+sql);
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
}
