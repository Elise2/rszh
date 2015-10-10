package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.GetMapUtil;
import jdbc.JdbcTools;
import bean.CommentObject;

/**
 * 对动态创建的表的增删改查，下拉列
 * @author Tianci
 *
 */
public class ActivityTableDao extends Dao{

	/**
	 * 创建数据库
	 * @param name
	 * @return
	 */
	public boolean createDatabase(String name){
		String sql = "create database "+ name;
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql);
			System.out.println(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return true;
	}
	
	/**
	 * 删除数据库
	 * @param name
	 * @return
	 */
	public boolean deleteDatabase(String name){
		String sql = "drop database "+ name;
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql);
			System.out.println(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return true;
	}
	
	/**
	 * 判断列是否为选择列(例如：rowName=民族，返回值是1)
	 * @param params
	 * @return
	 */
	public boolean isSelectRow(String rowName){
		String sql = "select judge_chose_row from main_table where row_name=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql, rowName);
			int i = (Integer)list.get(0).getValues().get("judge_chose_row");
			if (i == 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return false;
	}
	
	/**
	 * 如果经过判断指定列是选择列，则调用该方法获取选择列的列值(例如：rowName=民族，返回list[苗，汉，满。。。])
	 * @param rowName
	 * @return
	 */
	public List<String> getSelectRowValueList(String rowName){
		String sql = "select row_value from select_table where row_name=?";
		List<String> values = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql, rowName);
			for (int i = 0; i < list.size(); i++) {
				String rowValue = (String) list.get(i).getValues().get("row_value");
				values.add(rowValue);
			}
			return values;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return null;
	}
	
	public List<CommentObject> getSelectRowValueListWithName(String rowName){
		String sql = "select row_name,row_value from select_table where row_name=?";
	
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql, rowName);
			
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return null;
	}
	
	/**
	 * 给选择表加入新的选择项
	 * @param rowName
	 * @param rowType
	 */
	public void addNewSelectRow(String rowName,String rowValue){
		String sql = "insert select_table(row_name,row_value) values(?,?);";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql, rowName,rowValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * 修改列名
	 * @param rowName
	 * @param oldRowType
	 * @param rowValue
	 */
	public void alterSelectRow(String rowName,String oldRowValue,String rowValue){
		String sql = "update select_table set row_value=? where row_name=? and row_value=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql, rowValue, rowName, oldRowValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * 删除选择列的列值
	 * @param rowName
	 * @param rowValue
	 */
	public void delSelectRowValue(String rowName,String rowValue){
		String sql = "delete from select_table where row_name=? and row_value=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql, rowName, rowValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * 对指定的表插入记录(传入的参数中包括指定的表名[tableName=表名]，[指定表的列名=列值](可以指定一个插入的字段或者多个))
	 * @param request 为HttpServletRequest的参数类型，要求传入时除了表名及要插入的列的键值对外，不能包含有其他的与插入表无关的键值对
	 */
	public void add(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		add(params);
	}
	
	/**
	 * 插入记录的重载函数，当要传入的request中包含有与表插入无关的键值对时，用Map<String, Object> params的方法传入参数，map中放入表名，
	 * 表字段的键值对，不能包含有与表无关的键值对。
	 * 如何获得Map<String, Object> params：
	 * 1:"Map<String, Object> params= GetMapUtil.getRequestMap(request);"
	 * 2:移除与表无关的键值对
	 * @param params
	 */
	public void add(Map<String, Object> params){
		String sql = getInsertSql(params);
		params.remove("tableName");
		int size = params.size();
		String[] args = new String[size];
		int i = 0;
		for (String key : params.keySet()) {
			args[i++] = (String)params.get(key);
		}
		Connection connection = null;
		try {
			
			connection = JdbcTools.getConnection();
			write(connection, sql, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * 获取在main_table中该表列名的集合(该集合中每个对象应包含有如下键值对：[id=][row_name=][row_type=][judge_chose_row=][comment=]);
	 * 注意:request中应包含有[tableName=],有其他键值对不影响
	 * @param request
	 * @return
	 */
	public List<CommentObject> getRowNameList(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		String tableName = (String)params.get("tableName");
		params.remove("tableName");
		List<String> rowNameList = new ArrayList<String>();
		String sql = "select id,row_name,row_type,judge_chose_row,comment from main_table where table_name=? order by row_index asc";
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		 return list;
	}
	
	public List<CommentObject> getRowNameList(String tableName){
		List<String> rowNameList = new ArrayList<String>();
		String sql = "select id,row_name,row_type,judge_chose_row,comment from main_table where table_name=? order by row_index asc";
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		 return list;
	}
	
	/**
	 * 拼接SQL语句
	 */
	private String getInsertSql(Map<String, Object> params){
		String tableName = (String)params.get("tableName");
		params.remove("tableName");
		String sql = "insert into "+tableName+"(";
		String str = "(";
		int size = params.size();
		int i = 0;
		for (String key : params.keySet()) {
			sql = sql+key;
			str = str + "?";
			if (i < size-1) {
				sql = sql+",";
				str = str + ",";
			}
			i++;
		}
		str = str + ")";
		sql = sql + ") values"+str;
		System.out.println("拼接的插入的sql语句： "+sql);
		return sql;
	}
	
	/**
	 * 根据传回的表名和id值删除指定表的记录，request中应包含有[tableName=],[id=](此id为指定表的指定记录的id)；
	 * 注意：request 中不能有其他的键值对
	 * @param request
	 */
	public void delete(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		delete(params);
	}
	
	/**
	 * delete的重载函数，参数中应包含：[tableName=],[id=](此id为指定表的指定记录的id)；
	 *  如何获得Map<String, Object> params：
	 * 1:"Map<String, Object> params= GetMapUtil.getRequestMap(request);"
	 * 2:移除除了（tableName，id）之外的键值对
	 * @param params
	 */
	public void delete(Map<String, Object> params){
		String sql = getDeleteSql(params);
		System.out.println(sql);
		params.remove("tableName");
		int size = params.size();
		String[] args = new String[size];
		int i = 0;
		for (String key : params.keySet()) {
			args[i++] = (String)params.get(key);
		}
		Connection connection = null;
		try {
			
			connection = JdbcTools.getConnection();
			write(connection, sql, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * 获取删除指定表的制定id记录的sql语句
	 * @param table
	 * @param id
	 * @return
	 */
	private String getDeleteSql(Map<String, Object> params){
		String tableName = (String)params.get("tableName");
		params.remove("tableName");
		String sql = "delete from "+tableName+"  where id=?";
		System.out.println("拼接的删除的sql语句： "+sql);
		return sql;
	}
	
	/**
	 * 修改指定表指定id的字段值,request中应包含[tableName=][id=][指定修改的字段名=](可以一次传入多个字段)，
	 * 注意：request中不能包含与指定键值对之外的其他键值对
	 * @param request
	 */
	public void alter(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		alter(params);
	}
	
	/**
	 * alter的重载函数，当request中包含有指定参数之外的键值对时，可以重新构造键值对调用此方法
	 *  如何获得Map<String, Object> params：
	 * 1:"Map<String, Object> params= GetMapUtil.getRequestMap(request);"
	 * 2:移除指定键值对之外的键值对
	 * @param params
	 */
	public void alter(Map<String, Object> params){
		String tableName = (String)params.get("tableName");
		String id = (String)params.get("id");
		params.remove("tableName");
		params.remove("id");
		int size = params.size();
		String[] args = new String[size];
		int i = 0;
		for (String key : params.keySet()) {
			args[i] = (String)params.get(key);
			String sql = getAlterSql(tableName, key);
			System.err.println(sql);
			try {
				Connection connection = null;
				connection = JdbcTools.getConnection();
				write(connection, sql, args[i],id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
	
	/**
	 * 拼接修改单个字段的sql语句
	 * @param table
	 * @param id
	 * @param rowName
	 * @return
	 */
	private String getAlterSql(String tableName,String rowName){
		String sql = "update "+tableName+" set "+rowName+"=? where id=?";
		System.out.println("拼接的修改的sql语句： "+sql);
		return sql;
	}
	
	/**
	 *  获取指定表的记录的全部字段值,request中包含[tableName=][限定查询的字段=](此字段是where语句后的查询控制，可以传入多个字段)；
	 *  注意：request中不能包含其他的与查询无关的键值对
	 * @param request
	 * @return
	 */
	public List<CommentObject> getList(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		List<CommentObject> list = getList(params);
		return list;
	}
	
	/**
	 * getList的重载函数，当request中有除表查询之外的键值对时，可重新构建传入参数，调用该方法；
	 *  如何获得Map<String, Object> params：
	 * 1:"Map<String, Object> params= GetMapUtil.getRequestMap(request);"
	 * 2:移除指定键值对之外的键值对
	 * @param params
	 * @return
	 */
	public List<CommentObject> getList(Map<String, Object> params){
		List<CommentObject> list = null;
		String sql = getSelectSql(params);
		params.remove("tableName");
		int size = params.size();
		String[] args = new String[size];
		int i = 0;
		for (String key : params.keySet()) {
			args[i++] = (String)params.get(key);
		}
		Connection connection = null;
		try {
			
			connection = JdbcTools.getConnection();
			list = get(connection, sql, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
	
	/**
	 * 查询指定表的指定字段，注意：查询到的是指定表指定字段的全部值，无法加where子句；
	 * request：[tableName=][指定字段](可以多个)
	 * @param request
	 * @return
	 */
	public List<CommentObject> getRowListValue(HttpServletRequest request){
		Map<String, Object> params = GetMapUtil.getRequestMap(request);
		List<CommentObject> list = getRowListValue(params);
		return list;
	}
	
	/**
	 * getRowListValue的重载函数，当request中包含有其他无关键值对时，调用此方法；
	 *  如何获得Map<String, Object> params：
	 * 1:"Map<String, Object> params= GetMapUtil.getRequestMap(request);"
	 * 2:移除指定键值对之外的键值对
	 * @param params
	 * @return
	 */
	public List<CommentObject> getRowListValue(Map<String, Object> params){
		List<CommentObject> list = null;
		String sql = getSelectSqlByValue(params);
		System.err.println("查詢語句： "+sql);
		params.remove("tableName");
		Connection connection = null;
		try {
			
			connection = JdbcTools.getConnection();
			list = get(connection, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
	
	private String getSelectSqlByValue(Map<String, Object> params) {
		String tableName = (String)params.get("tableName");
		System.out.println("tableName: "+tableName);
		params.remove("tableName");
		String sql = "select  ";
		int size = params.size();
		int i = 0;
		for (String key : params.keySet()) {
			sql = sql + key;
			if (i != size-1) {
				sql = sql + ",";
			}
			i++;
		}
		sql = sql + "  from "+tableName;
		System.out.println("拼接的查询的sql语句： "+sql);
		return sql;
	}

	/**
	 * 拼接查询SQL语句(根据表名查找记录,可加限定条件)
	 * @param params
	 * @return
	 */
	private String getSelectSql(Map<String, Object> params){
		System.err.println("params: "+params);
		String tableName = (String)params.get("tableName");
		System.out.println("tableName: "+tableName);
		params.remove("tableName");
		String sql = "select * from "+tableName;
		int size = params.size();
		int i = 0;
		for (String key : params.keySet()) {
			if(i == 0){
				sql = sql+"  where ";
			}
			sql = sql + key +" =? ";
			i++;
			if (i < size) {
				sql = sql + "and ";
			}
		}
		System.out.println("拼接的限定查询的sql语句： "+sql);
		return sql;
	}
}
