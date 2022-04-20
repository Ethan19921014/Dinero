//CreateServlet

package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActivityBean;
import dao.ActivityDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	 
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActivityBean newActivity =  new ActivityBean();
		
		ActivityDao activityDao = new ActivityDao();
		
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
		
		newActivity.setActId(request.getParameter("actId"));
		newActivity.setActName(request.getParameter("actName"));
		newActivity.setEndDate(request.getParameter("endDate"));
		newActivity.setMemberRemain(request.getParameter("memberRemain"));
		newActivity.setMemberLimit(request.getParameter("memberLimit"));
		newActivity.setActContent(request.getParameter("actContent"));
	    
	    try {
			activityDao.createActivity(newActivity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    request.getSession(true).setAttribute("newActivity", newActivity);
	    request.getRequestDispatcher("/ShowActivityServlet").forward(request,response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);  //執行doGet方法，要改就在doGet裡面改
	}

}
