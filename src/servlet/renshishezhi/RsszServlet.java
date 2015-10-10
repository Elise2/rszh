package servlet.renshishezhi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.DepartDao;
import dao.MainTableDao;

/**
 * 请求人事设置功能模块时，准备好记录设置中除档案表之外的其他表，
 * 主界面默认显示的是当前部门信息
 * @author Tianci
 *
 */
@WebServlet("/rsszServlet")
public class RsszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获得main_table中除档案表（personal）之外的其他表信息
		MainTableDao mtd = new MainTableDao();
		List<CommentObject> tableNameList = mtd.getTableNameList();
		//获得部门信息
		DepartDao dp = new DepartDao();
		List<CommentObject> departList = dp.selectDepart();
		//将tableNameList，departList放入request中
		request.setAttribute("tableNameList", tableNameList);
		request.setAttribute("departList", departList);
		//转发给人事设置主界面
		request.getRequestDispatcher("/renshishezhi/rssz.jsp").forward(request, response);
	}

}
