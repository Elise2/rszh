package servlet.import_export;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jdbc.JdbcTools;
import dao.DepartDao;


@WebServlet("/lastimportServlet")
public class lastimportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String departname = request.getParameter("departname");
		DepartDao dd = new DepartDao();
		Connection connection = null;
		System.out.println("session:"
				+ request.getSession().getAttribute("data"));
		List<Vector> data = (List<Vector>) request.getSession().getAttribute(
				"data");
		System.out.println("session:"
				+ request.getSession().getAttribute("columnName"));
		List<Vector> columnName = (List<Vector>) request.getSession()
				.getAttribute("columnName");

		String sql3 = "insert into personal(编号) values(?)";
		System.out.println(sql3);
		try {
			connection = JdbcTools.getConnection();
			for (int i = 0; i < data.size(); i++) {
				DepartDao dd1 = new DepartDao();
				dd1.write(connection, sql3, data.get(i).get(0));
			}
		/*} catch (Exception e) {
			e.printStackTrace();
		}
		try {*/
			connection = JdbcTools.getConnection();
			for (int j = 0; j < data.size(); j++) {
				String sql2 = "update personal set 部门=? where 编号=? ";
				dd.write(connection, sql2, departname, data.get(j).get(0));

				for (int m1 = 1; m1 < columnName.size(); m1++) {
					String sql = "update personal set " + columnName.get(m1)
							+ "=? where 编号=? ";
					System.out.println(sql);
					dd.write(connection, sql, data.get(j).get(m1), data.get(j)
							.get(0));
				}
			}
			//request.getSession().getAttribute("deletepath");
		importDao idd=new importDao();
	        idd.removeAll( new File("d:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\rszh\\importFile"));
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
