package suwa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDao;
import model.CartDto;
import model.CartItemDao;
import model.CartItemDtoBean;
import suwa.tool.IdWorker;

/**
 * Servlet implementation class GreenController
 */
@WebServlet("/GreenController")
public class GreenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GreenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
//		 String success = request.getParameter("RtnCode");
		 
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(request.getParameter(names.nextElement()));
			
		}
			
		 System.out.println("println out side loop");
		 
		 
//		 if(success.equals("1")) {
//			 
//			 System.out.println("inside loop");
//			 HttpSession session = request.getSession();
//				CartDto cartDto =(CartDto) session.getAttribute("cartDto");
//				
//				IdWorker idWorker = new IdWorker(1);
//				cartDto.setCartId(idWorker.nextIdStr());
//				
//				//set item cartId
//				cartDto.setItemCartId();
//				List<CartItemDtoBean> items = cartDto.getItems();
//				
//				
//				
//				CartDao cartDao = new CartDao();
//				CartItemDao cartItemDao = new CartItemDao();
//				
//				
//				
//				try {
//					cartDao.inserIntoDataBase(cartDto);
//					items.forEach( item ->cartItemDao.insertIntoTable(item) );
//				} catch (SQLException e) {
//					System.out.println("新增失敗");
//					e.printStackTrace();
//				}
//				
//		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
