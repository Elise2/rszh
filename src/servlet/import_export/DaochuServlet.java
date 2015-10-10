package servlet.import_export;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import util.GetMapUtil;
import bean.CommentObject;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DaochuServlet
 */
@WebServlet("/daochuServlet")
public class DaochuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getRowListValue(request);
		System.out.println("list: " + list);
		Map<String, Object> rowsMap = GetMapUtil.getRequestMap(request);
		rowsMap.remove("tableName");
		System.out.println("rowsMap: " + rowsMap);

		response.reset();
		OutputStream os4 = response.getOutputStream();
		// response.setContentType("application/pdf");
		response.setContentType("application/msexcel");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String("personal.xls".getBytes("utf-8"), "utf-8"));

		WritableWorkbook wwb = null;
		try {

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(os4);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("员工信息表", 0);

			// 下面开始添加单元格

			try {
				for (int i = 0; i < list.size(); i++) {
					//for (int j = 0; j < rowsMap.size(); j++) {
						int j = 0;
						for (Object row : rowsMap.keySet()) {

							String rowName = row.toString();
							System.out.println("row:  ^^" + rowName);
							System.out.println("1" + rowsMap.keySet());
							// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
							Label labelC1 = new Label(j, 0, rowName);
							ws.addCell(labelC1);
							Label labelC = new Label(j, i + 1, (String) list
									.get(0).getValues().get(rowName));
							System.out.println(labelC);

							// 将生成的单元格添加到工作表中
							// ws.addCell(labelC1);
							ws.addCell(labelC);
							
							j++;
						}
					//}
				}
				wwb.write();
				wwb.close();
				os4.flush();
				os4.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 从内存中写入文件中

			// 关闭资源，释放内存

		}
	}
}
