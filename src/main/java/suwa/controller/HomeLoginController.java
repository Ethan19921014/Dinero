package suwa.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeLoginController
 */
@WebServlet("/HomeLoginController")
public class HomeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("in servlet");
		 model.User user = (model.User)request.getSession().getAttribute("userNowLogin");
		 
		 
		 if(user == null) {
			 //System.out.println("in user not login");
			 request.setAttribute("notLogIn", "請先登入");
			 request.getRequestDispatcher( "/dineroHome.jsp").forward(request, response);
			 //response.sendRedirect(request.getContextPath()+"/dineroHome.jsp");
		 }else {
			 //System.out.println("in user  login");
			 String homeTowhere = request.getParameter("homeTowhere");
			 
			 
			 if(homeTowhere.equals("toOrd")) {
				 response.sendRedirect(request.getContextPath()+"/gewei/cartHome.jsp");
			 }else if(homeTowhere.equals("toUser")){
				 response.sendRedirect(request.getContextPath() + "/profiles/profiles-list.jsp");
			 }else if(homeTowhere.equals("toProduct")) {
				 response.sendRedirect(request.getContextPath() + "/ReadProduct"); 
			 }else if (homeTowhere.equals("toActivity")) {
				 response.sendRedirect(request.getContextPath() + "/ActJSP/ShowActivity.jsp");
			 }else if (homeTowhere.equals("toBulletin")) {
				 response.sendRedirect(request.getContextPath() + "/messageboard/messageIndex.jsp");
			};
		   
		 }
		 
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
}
