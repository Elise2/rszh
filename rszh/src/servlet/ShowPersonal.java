package servlet;

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

@WebServlet("/showPersonal")
public class ShowPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atDao = new ActivityTableDao();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", "personal");
		List<CommentObject> list = atDao.getList(params);
		System.out.println("list^^:"+list);
		List<CommentObject> rowNames= atDao.getRowNameList("personal");
		System.out.println("rowNames^^:"+rowNames);
		request.setAttribute("list", list);
		request.setAttribute("rowNames", rowNames);
		request.getRequestDispatcher("/jsp/showPersonal.jsp").forward(request, response);
	}

}
