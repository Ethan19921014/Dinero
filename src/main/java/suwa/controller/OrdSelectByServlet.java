package suwa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDao;
import model.CartDto;

/**
 * Servlet implementation class SelectByCustIdServlet
 */
@WebServlet("/OrdSelectByServlet")
public class OrdSelectByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdSelectByServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selectBy = request.getParameter("selectBy");
		if(selectBy.equals("custId")) {
			
			selectByCustId(request, response);
			
		}else if (selectBy.equals("cartId")) {
			
			selectByCartId(request, response);
			
		}else if (selectBy.equals("cartTotal")) {
			
			selectByTotal(request, response);
			
		}else if(selectBy.equals("tradeDate")) {
			
			selectByTradeDate(request, response);
			
		}else if(selectBy.equals("advanced")) {
			System.out.println("進階搜尋");
			CartDao dao = new CartDao();
			String custId = request.getParameter("custId"); 
			String cartId =request.getParameter("cartId") ;
			String minTotal = request.getParameter("minTotal");
			String maxTotal = request.getParameter("maxTotal");
			String minDate = request.getParameter("minDate");
			String maxDate = request.getParameter("maxDate");
			try {
				List<CartDto> ords = dao.advancedSelect(cartId, custId, minTotal, maxTotal, minDate, maxDate);
				HttpSession session = request.getSession();
				session.setAttribute("SearchedOrds", ords);
				request.getRequestDispatcher("/gewei/SearchedOutcome.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void selectByCustId(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int custId = Integer.parseInt(request.getParameter("custId")); 
		
		CartDao cartDao = new CartDao();
		List<CartDto> ords = null;
		
		
		ords = cartDao.selectOrdByCustId(custId);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("SearchedOrds", ords);
		request.getRequestDispatcher("/gewei/SearchedOutcome.jsp").forward(request, response);
	}
	
	private void selectByCartId(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String cartId =request.getParameter("cartId") ;
		
		CartDao cartDao = new CartDao();
		List<CartDto> ords = new ArrayList<CartDto>();
		CartDto ord = null;
		ord = cartDao.selectByCartId(cartId);
		
		ords.add(ord);
		
		HttpSession session = request.getSession();
		session.setAttribute("SearchedOrds", ords);
		request.getRequestDispatcher("/gewei/SearchedOutcome.jsp").forward(request, response);
	}
	
	private void selectByTradeDate(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException {
		String minDate = request.getParameter("minDate");
		String maxDate = request.getParameter("maxDate");
		CartDao cartDao = new CartDao();
	
		try {
			List<CartDto> ords = cartDao.selectByDate(minDate,maxDate);
			HttpSession session = request.getSession();
			session.setAttribute("SearchedOrds", ords);
			request.getRequestDispatcher("/gewei/SearchedOutcome.jsp").forward(request, response);
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
	}
	
	private void selectByTotal(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String minTotal = request.getParameter("minTotal");
		String maxTotal = request.getParameter("maxTotal");
		CartDao cartDao = new CartDao();
		
		try {
			List<CartDto> ords = cartDao.selectByTotal(minTotal,maxTotal);
			HttpSession session = request.getSession();
			session.setAttribute("SearchedOrds", ords);
			request.getRequestDispatcher("/gewei/SearchedOutcome.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
