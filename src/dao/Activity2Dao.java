package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import bean.CommentObject;

import jdbc.JdbcTools;

/**
 * 补充类
 * @author Tianci
 *
 */
public class Activity2Dao extends Dao{
	
	/**
	 * 从main_table中获取表列集合，按顺序排列
	 * @return
	 */
	public List<String> getRowNamesOrderByRowIndex(String tableName){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> list = new ArrayList<String>();
		String sql = "select row_name from main_table where table_name=? order by row_index asc";
		try {
			connection = JdbcTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tableName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String rowName = resultSet.getString("row_name");
				list.add(rowName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.free(resultSet, preparedStatement, null);
		}
		return list;
	}
	
	/**
	 * 拼接查询的语句
	 * @param list
	 * @param tableName
	 * @return
	 */
	public String getSelectSql(List<String> list,String tableName){
		String sql = "select  ";
		for(int i = 0; i < list.size();i++){
			sql = sql + list.get(i);
			if(i != list.size()-1){
				sql = sql + ",";
			}
		}
		sql = sql + "  from  " + tableName;
		return sql;
	}
	
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
		String num = (String)params.get("num");
		String tiaojian = (String)params.get("tiaojian");
		String sql = "select ";
		System.out.println("tableName: "+tableName);
		params.remove("tableName");
		sql=sql+(String)params.get("rowName");
		sql = sql + " from "+tableName +" where "+tiaojian+"="+num;
		System.out.println("拼接的查询的sql语句： "+sql);
		return sql;
	}
	
	public List<String> getPerson(){
		List <String> list = new ArrayList<String>();
		list.add("姓名");
		list.add("编号");
		list.add("照片");
		list.add("性别");
		list.add("民族");
		list.add("出生日期");
		list.add("年龄");
		list.add("身份证号");
		list.add("籍贯");
		list.add("政治面貌");
		list.add("婚姻状况");
		list.add("入党时间");
		list.add("党团龄");
		list.add("入职时间");
		list.add("本单位工龄");
		list.add("学历");
		list.add("毕业院校");
		list.add("毕业时间");
		list.add("所学专业");
		list.add("联系电话");
		list.add("手机号码");
		list.add("电子邮箱");
		list.add("户口");
		list.add("家庭住址");
		return list;
	}
	public List<String> getDanwei(){
		List <String> list = new ArrayList<String>();
		list.add("人员类别");
		list.add("性别");
		list.add("职务");
		list.add("职称");
		list.add("社保号码");
		list.add("银行账号");
		list.add("合同生效日期");
		list.add("合同失效日期");
		list.add("退休日期");
		list.add("离职日期");
		list.add("离职类别");
		list.add("离职原因");
		list.add("培训名称");
		return list;
	}
	public List<String> getPeixun(){
		List <String> list = new ArrayList<String>();
		list.add("培训名称");
		list.add("培训时间");
		return list;
	}
	public List<String> getChangpai(){
		List <String> list = new ArrayList<String>();
		list.add("姓名");
		list.add("编号");
		list.add("性别");
		list.add("部门");
		list.add("职务");
		list.add("入职时间");
		return list;
	}
}
