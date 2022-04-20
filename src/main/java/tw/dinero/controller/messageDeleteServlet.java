package tw.dinero.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dinero.model.MessageBean;
import tw.dinero.model.MessageDao;

/**
 * Servlet implementation class messageDeleteServlet
 */
@WebServlet("/messageDeleteServlet")
public class messageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public messageDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MessageDao dao= new MessageDao();
		
		String messageId = request.getParameter("messageId");
		
		MessageBean m = new MessageBean();
		m.setMessageId(Integer.parseInt(messageId));
		
		dao.deleteMessage(m);
		
		response.sendRedirect("messageIndexServlet");
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
