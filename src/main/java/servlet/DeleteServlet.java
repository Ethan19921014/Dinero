package servlet;

import dao.ActivityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String actIdStr = request.getParameter("actId"); //�n�g ���Ѽ�
        System.out.println(actIdStr);
        
        ActivityDao activityDao = new ActivityDao();
        
        try {  //��26~31�� �쥻�u�� activityDao.deleteByActId(request.getParameter("actId")); �ӨS��try-catch
			activityDao.deleteByActId(actIdStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("/dinero/ActJSP/ShowActivity.jsp");
    }
}