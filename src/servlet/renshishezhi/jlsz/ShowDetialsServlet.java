package servlet.renshishezhi.jlsz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;

@WebServlet("/showDetialsServlet")
public class ShowDetialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取指定表中的所有列信息
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getRowNameList(request);
		//获取数据类型
		List<String> sjlx = atd.getSelectRowValueList("数据类型");
		request.setAttribute("list", list);
		request.getSession().setAttribute("sjlx", sjlx);
		request.getRequestDispatcher("/jsp/rssz/jlsz/showDetials.jsp").forward(request, response);
	}
}
