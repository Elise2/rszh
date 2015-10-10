package gaojichaxun;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gaojichaxunServlet")
public class GaojichaxunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获得已经拼接好的字符串
		String sqlString = request.getAttribute("sqlString")+"";
		//得到本次的查询条件
		String ziduan = request.getParameter("字段");
		String tiaojian = request.getParameter("条件");
		String value = request.getParameter("值");
		String lianjie =  request.getParameter("连接符");
		if(lianjie.equals("ok")){
			
		}else{
			
		}
		
	}

}
