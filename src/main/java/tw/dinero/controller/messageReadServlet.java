package tw.dinero.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dinero.model.MessageBean;
import tw.dinero.model.MessageDao;

/**
 * Servlet implementation class messageReadServlet
 */
@WebServlet("/messageReadServlet")
public class messageReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public messageReadServlet() {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MessageDao dao = new MessageDao();
		try{
			int messageId = Integer.parseInt(request.getParameter("messageId"));
			List<MessageBean> list = dao.selectMessage(messageId);
			request.setAttribute("messages", list);
			request.getRequestDispatcher("messageboard/messageIndex.jsp").forward(request, response);
		}catch (Exception e) {
			response.sendRedirect("messageIndexServlet");
		}
		
		
//		response.sendRedirect("messageboard/messageIndex.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
