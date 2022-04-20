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
 * Servlet implementation class ReadProductById
 */
@WebServlet("/ReadProductById")
public class ReadProductById extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		
		Product p = new Product();
		ProductDao dao = new ProductDao();
		
		
		
		if(request.getParameter("prodId").equals("")) {
			List<Product> list = dao.findAll();

			  request.setAttribute("data", list);
				RequestDispatcher view = request.getRequestDispatcher("Productjsp/ProductForm.jsp");
				
				view.forward(request,response);
		}else {
			
			int answer = Integer.parseInt(request.getParameter("prodId")); //空的轉換會出錯
		
		List<Product> list = dao.FindById(answer);
		
		request.setAttribute("data", list);
		RequestDispatcher view = request.getRequestDispatcher("Productjsp/ProductForm.jsp");
		
		view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
