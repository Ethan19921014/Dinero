package tw.dinero.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadProdImg
 */
@MultipartConfig(location="C:\\webws\\dinero\\src\\main\\webapp\\Img")
@WebServlet("/UploadProdImg")
public class UploadProdImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   response.setContentType("text/html;charset=UTF-8");
	   
	   Part photo = request.getPart("photo");
	   String filename = getSubmittedFileName(photo);
	   photo.write(filename);
	   
	   response.sendRedirect("Productjsp/CreateProduct.jsp");
	}

	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("Content-Disposition");
		Matcher matcher = fileNameRegex.matcher(header);
		matcher.find();
		
		String filename = matcher.group(1);
		if(filename.contains("\\")) {
			return filename.substring(filename.lastIndexOf("\\") + 1);
		}

		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
