package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;
import dao.MainTableDao;

/**
 * Servlet implementation class NewTableServlet
 */
@WebServlet("/newTableServlet")
public class NewTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		ActivityTableDao atd = new ActivityTableDao();
		/*String tableName = request.getParameter("tableName");
		//获取参数
		if (request.getParameter("action") != null && tableName != null) {
			String action = request.getParameter("action");
			if (action.equals("添加新表")){
				System.out.println("添加新表操作被执行");
				mtd.createTable(tableName);
			}else if(action.equals("修改表名")){
				mtd.alterTable("培训1", tableName);
			}else if(action.equals("删除表")){
				mtd.dropTable(tableName);
			}else if(action.equals("上移")){
				mtd.bigUpMove(tableName);
			}else if(action.equals("下移")){
				mtd.bigdownMove(tableName);
			}
		}*/
		//获取数据库中表名的集合
		List<CommentObject> tableNames = mtd.getTableNameList();
		System.out.println(tableNames);
		//获得指定表的列的信息
		List<CommentObject> rowsInfo = atd.getRowNameList(request);
		System.out.println(rowsInfo);
		//如果
		//放入attr
		request.setAttribute("tableNames", tableNames);
		request.setAttribute("rowsInfo", rowsInfo);
		request.getRequestDispatcher("/renshishezhi/bmsz.jsp").forward(request, response);
	}

}
