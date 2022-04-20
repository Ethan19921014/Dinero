package servlet;

import dao.ActivityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActivityBean;

import java.io.IOException;
import java.sql.SQLException;

	@WebServlet("/UpdateServlet")
	public class UpdateServlet extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ActivityBean activityBean = new ActivityBean();
    	
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        activityBean.setActId(request.getParameter("actId"));
        activityBean.setActName(request.getParameter("actName")); 
        activityBean.setActContent(request.getParameter("actContent"));
        activityBean.setEndDate(request.getParameter("endDate"));
        activityBean.setMemberRemain(request.getParameter("memberRemain"));
        activityBean.setMemberLimit(request.getParameter("memberLimit"));
        
  //      String actIdStr = request.getParameter("actId");
        
        System.out.println(request.getParameter("actId"));
        System.out.println(request.getParameter("actName"));

        ActivityDao a = new ActivityDao();
        
        try {  //��26~31�� �쥻�u�� activityDao.deleteByActId(request.getParameter("actId")); �ӨS��try-catch
        	a.updateData(activityBean); //, actContentStr, endDateStr, memberRemainStr, memberLimitStr
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
          response.sendRedirect("/dinero/ActJSP/ShowActivity.jsp");
      }
  }
