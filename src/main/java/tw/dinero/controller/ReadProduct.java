package tw.dinero.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dinero.model.Product;
import tw.dinero.model.ProductDao;

/**
 * Servlet implementation class ReadProduct
 */
@WebServlet("/ReadProduct")
public class ReadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html;charset=UTF-8");
	  Product p = new Product();
	  ProductDao dao = new ProductDao();
	  List<Product> list = dao.findAll();
//	  System.out.println(list);
	  request.setAttribute("data", list);
		RequestDispatcher view = request.getRequestDispatcher("Productjsp/ProductForm.jsp");
		
		view.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
