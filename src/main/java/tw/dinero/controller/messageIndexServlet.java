package tw.dinero.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dinero.model.MessageBean;
import tw.dinero.model.MessageDao;

/**
 * Servlet implementation class messageIndex
 */
@WebServlet("/messageIndexServlet")
public class messageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public messageIndexServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MessageDao dao= new MessageDao(); 
		
		List<MessageBean> list = dao.findAll();
		request.setAttribute("messages", list);
		request.getRequestDispatcher("messageboard/messageIndex.jsp").forward(request, response);
		
//		String messagetitle = request.getParameter("messagetitle");
//		String message = request.getParameter("message");
//		
//		MessageBean m = new MessageBean();
//		m.setMessagetitle(messagetitle);
//		m.setMessage(message);
//		
//		dao.addMessage(m);
//		
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<body>");
//		out.println("<h1> 留言板</h1>");
//		out.println(messagetitle+"<br>");
//		out.println(message+"<br>");
//		out.println("</body>");
//		out.println("</html>");

//		response.sendRedirect("messageboard/messageIndex.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
