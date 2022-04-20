package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.ActivityBean;
import dao.ActivityDao;

/**
 * Servlet implementation class ShowActivityServlet
 */
@WebServlet("/ShowActivityServlet")
public class ShowActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	
	public ShowActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//gotoSubmitProcess(request, response);
		System.out.println("test");
	    
	    ActivityDao activityDao = new ActivityDao();
	    List<ActivityBean> list;
		try {
			list = activityDao.readAllActivity(); 
			request.setAttribute("showAllData", list);  
//	        System.out.println(list);
	        for(ActivityBean a:list) {
//	        System.out.println(a.getActName());
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //request.getRequestDispatcher("/dinero/ActJSP/ShowActivity.jsp").forward(request,response);
		response.sendRedirect("/dinero/ActJSP/ShowActivity.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
