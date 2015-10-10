package servlet.renshishezhi.jlsz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.MainTableDao;

/**
 * 记录设置
 * @author Tianci
 *
 */
@WebServlet("/jlszServlet")
public class JlszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取数据库中除personal之外的表的集合
		MainTableDao mtd = new MainTableDao();
		List<CommentObject> tableInfo = mtd.getTableNameList();
		//String tableName = "培训记录";
		//判断request中有无tableName
		//if(request.getParameter("tableName")!= null){
		//	tableName = request.getParameter("tableName");
		//}
		//获取对应表中的列
		//ActivityTableDao atd = new ActivityTableDao();
		//Map<String, Object> map = new HashMap<String, Object>();
		////List<CommentObject> list = atd.getRowNameList(tableName);
		//获取数据类型
		//List<String> sjlx = atd.getSelectRowValueList("数据类型");
		//request.setAttribute("list", list);
		//request.setAttribute("sjlx", sjlx);
		request.setAttribute("tableInfo", tableInfo);
		request.getRequestDispatcher("/jsp/rssz/jlsz/jlsz.jsp").forward(request, response);
	}
}
