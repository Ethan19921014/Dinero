package tw.dinero.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.dinero.model.Product;
import tw.dinero.model.ProductDao;

/**
 * Servlet implementation class UpdateProduct
 */
@MultipartConfig(location="C:\\webws\\dinero\\src\\main\\webapp\\Img")
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
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
 	   
        Product p = new Product();
        p.setProdId(Integer.parseInt(request.getParameter("readId")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setPrice(Integer.parseInt(request.getParameter("price")));
        p.setProdName(request.getParameter("prodName"));
        p.setDescript(request.getParameter("descript"));
        p.setCategory(request.getParameter("category"));
        p.setPetkind(request.getParameter("petkind"));
               
        ProductDao dao = new ProductDao();
        try {
			dao.updateAllbyId(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<Product> list = dao.findAll();
        
        request.setAttribute("data", list);
        RequestDispatcher view = request.getRequestDispatcher("Productjsp/ProductForm.jsp");
        
        view.forward(request, response);
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
