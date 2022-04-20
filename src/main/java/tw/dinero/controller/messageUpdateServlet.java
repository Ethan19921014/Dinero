package tw.dinero.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dinero.model.MessageBean;
import tw.dinero.model.MessageDao;

/**
 * Servlet implementation class messageUpdateServlet
 */
@WebServlet("/messageUpdateServlet")
public class messageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
    public messageUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	MessageDao dao =new MessageDao();
	
	String messagetitle =request.getParameter("messagetitle");
	String message = request.getParameter("message");
	String messageId = request.getParameter("messageId");
	
	MessageBean m = new MessageBean();
	m.setMessagetitle(messagetitle);
	m.setMessage(message);
	m.setMessageId(Integer.parseInt(messageId));
	
	dao.updateMessage(m);
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
