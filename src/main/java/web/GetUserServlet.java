package web;
import java.io.IOException;


import java.util.ArrayList;

import javax.management.relation.RelationSupportMBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;


public class GetUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User ub = new User();
        String name = request.getParameter("name").trim();
        String email =request.getParameter("email").trim();
        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();

        System.out.println("name catched "+name);
        System.out.println("username catched"+userName);
        System.out.println("Password catched"+password);


        ArrayList<User> list = new ArrayList<User>();
         ub=new User();
         ub.setname(name);
         ub.setemail(email);
         ub.setUsername(userName);
         ub.setPassword(password);
         list.add(ub);  

         response.setContentType("text/plain");
         response.getWriter().print(list);             
    }

}