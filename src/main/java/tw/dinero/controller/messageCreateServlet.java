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
 * Servlet implementation class messageCreateServlet
 */
@WebServlet("/messageCreateServlet")
public class messageCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public messageCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messagetitle = request.getParameter("messagetitle");
		String message = request.getParameter("message");
		MessageDao dao= new MessageDao(); 
		MessageBean m = new MessageBean();
		m.setMessagetitle(messagetitle);
		m.setMessage(message);
		
		dao.addMessage(m);
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
