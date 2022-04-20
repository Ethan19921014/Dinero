package tw.dinero.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
 * Servlet implementation class CreateProduct
 */
@MultipartConfig(location="C:\\webws\\dinero\\src\\main\\webapp\\Img")
@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//upload Image
		Part photo = request.getPart("photo");//使用getPart()取得Part物件
		   String filename = getSubmittedFileName(photo);
		   photo.write(filename);//指定檔名寫入location
		   
//		PrintWriter out = response.getWriter();
		Product p = new Product();
		p.setProdId(Integer.parseInt(request.getParameter("prodId")));
		p.setProdName(request.getParameter("prodName"));
		p.setPrice(Integer.parseInt(request.getParameter("price")));
		p.setDescript(request.getParameter("descript"));
		p.setStock(Integer.parseInt(request.getParameter("stock")));
		p.setCategory(request.getParameter("category"));
		p.setPetkind(request.getParameter("petkind"));
//		List result = new ArrayList();
//		result.add(p.getProdName());
//		result.add(p.getPrice());
//		result.add(p.getDescript());
//		result.add(p.getStock());
//		result.add(p.getCategory());
//		result.add(p.getPetkind());
//		String prodName = request.getParameter("prodName");
//		String price = request.getParameter("price");
//		String descript = request.getParameter("descript");
//		String stock = request.getParameter("stock");
//		String category = request.getParameter("category");
//		String petkind = request.getParameter("petkind");
		
//		out.println("新增資料");
//		out.print(header);
//		out.println(p.getProdName()+p.getPrice()+p.getDescript()+p.getStock()+p.getCategory()+p.getPetkind());
//		out.print(footer);
		ProductDao dao = new ProductDao();
		
		try {
			dao.addProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		List<Product> list = dao.findAll();
//		  System.out.println(list);
		  request.setAttribute("data", list);
			RequestDispatcher view = request.getRequestDispatcher("Productjsp/ProductForm.jsp");
			
			view.forward(request,response);
			
//		request.setAttribute("data", p);
//		RequestDispatcher view = request.getRequestDispatcher("ProductResult.jsp");
//		
//		view.forward(request,response);
	}
	
	//取得上傳檔名
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

}
