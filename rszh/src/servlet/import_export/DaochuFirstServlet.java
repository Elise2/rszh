package servlet.import_export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DaochuFirstServlet
 */
@WebServlet("/daochuFirstServlet")
public class DaochuFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getRowNameList(request);
		request.setAttribute("list",list);
		request.getRequestDispatcher("/import_export/daochu_a.jsp?tableName=personal").forward(request, response);
	}

}