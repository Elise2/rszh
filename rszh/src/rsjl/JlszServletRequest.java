package rsjl;

import java.io.IOException;
import java.util.HashMap;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.DepartDao;
import dao.MainTableDao;

@WebServlet("/jlszServletRequest")
public class JlszServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atDao = new ActivityTableDao();
		MainTableDao mtd = new MainTableDao();
		
		List<CommentObject> tableNameList = mtd.getTableNameList();
		//删除personal
		for(int i = 0; i < tableNameList.size();i++){
			String nameString = tableNameList.get(i).getValues().get("table_name")+"";
			if(nameString.equals("personal")){
				tableNameList.remove(i);
				break;
			}
		}
		//获取默认的第一个表，获取其中的列信息
		System.out.println("map1");
		String tableName =(String)request.getParameter("tableName");
		
		System.out.println("map2"+tableName );

		if(tableName == null){
			tableName = tableNameList.get(0).getValues().get("table_name")+"";
		}
		System.out.println("map3");

		Map<String,Object> map=new HashMap<String,Object> ();
		map.put("tableName", tableName);
		System.out.println("map"+map);
		List<CommentObject> dataList=atDao.getList(map);
		System.out.println("dataList"+dataList);

		List<CommentObject> rowList = atDao.getRowNameList(tableName);
		//获取select_table中的人员类别
		List<String> renyuanleibieList = atDao.getSelectRowValueList("人员类别");
		//获取部门
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart();
		//如果有限定条件，则获取
		String leibie = request.getParameter("renyuanleibie");
		String depart = request.getParameter("部门");
		if(leibie == null){
			leibie = "在职人员";
		}
		if(depart == null){
			depart = "生产部";
		}
		//查询记录
		List<CommentObject> list = LHCX.queryList(tableName, depart, leibie);
		
		request.setAttribute("tableNameList", tableNameList);
		request.setAttribute("renyuanleibieList", renyuanleibieList);
		request.setAttribute("dataList", dataList);
		request.setAttribute("rowList", rowList);
		request.setAttribute("bumenList", bumenList);
		request.setAttribute("list", list);
		request.setAttribute("table", tableName);
		System.out.println("qqqq"+request.getAttribute("table"));
		request.getRequestDispatcher("/jsp/rsjl/rsjl.jsp").forward(request, response);
	}
}
