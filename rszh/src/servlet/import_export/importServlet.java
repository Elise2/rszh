package servlet.import_export;

import servlet.import_export.importDao.ExcelOperationUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



//import org.apache.poi.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.CommentObject;
import dao.DepartDao;


@WebServlet("/importServlet")
public class importServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = true;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String filePath = request.getParameter("src");
	
		System.out.println("D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/rszh"+filePath);
		String fileType = filePath.substring(filePath.length() - 3,
				filePath.length());
		System.out.println(fileType);
		if ("xls".equals(fileType) || "lsx".equals(fileType)) {
			List<String> columnName = ExcelOperationUtil
					.readExcelColumnData("D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/rszh"+filePath);
			List<Vector> data = ExcelOperationUtil.readExcelData("D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/rszh"+filePath);
 
		/*	//session 中保存路径，最后获得，以便删除
			HttpSession session = request.getSession();
			session.setAttribute("deletepath","E:/work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/renshi"+filePath);*/
			request.setAttribute("columnName", columnName);
			request.setAttribute("data", data);
			System.out.println(columnName);
			System.out.println(data);
		} else {
			flag = true;
			System.out.println("导入文件的格式不正确，请重新导入xls文件");
		}
		request.getRequestDispatcher("/import_export/importShow.jsp").forward(request,response);
	}
}
