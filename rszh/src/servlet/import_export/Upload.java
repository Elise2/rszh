package servlet.import_export;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String path=request.getParameter("path");
		System.out.println("filename:"+path);
		PrintWriter out = response.getWriter();
		//
		String nameString = null;
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig()
					.getServletContext();
			File repository = (File) servletContext
					.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);

			// Set factory constraints
			factory.setSizeThreshold(4*1024);
			//factory.setRepository(yourTempDirectory);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(4*1024*1024);
			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    
				    // Process a regular form field
				    if (item.isFormField()) {
				  //      processFormField(item);
				    	
				    	 String name = item.getFieldName();
					     String value = item.getString();
					     System.out.println(name+":"+value);
				    } else {
				   //     processUploadedFile(item);
				    	
				    	// Process a file upload
				    	
				    	    String fieldName = item.getFieldName();
				    	    String fileName = item.getName();
				    	    String contentType = item.getContentType();
				    	    boolean isInMemory = item.isInMemory();
				    	    long sizeInBytes = item.getSize();
				    	   
				    	    System.out.println(fieldName+":"+fileName+":"+contentType+":"+isInMemory+":"+sizeInBytes);
				    	    if (fileName!=null) {
				    	    	nameString = "/importFile/"+fileName;
				    	    	System.out.println("nameString"+nameString);
				    	        File uploadedFile = new File("D://workspace//.metadata//.plugins//org.eclipse.wst.server.core//tmp3//wtpwebapps//rszh//importFile",fileName);
				    	        try {
									item.write(uploadedFile);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    	    } else {
				    	    	
				    	    }
				    	    System.out.println(nameString);
				    	    response.sendRedirect("/importServlet?src="+nameString);
				    	}
				    }
				
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
