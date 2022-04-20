<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
	
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String dburl="jdbc:sqlserver://localhost:1433;DatabaseName=project2"; 
    String dbusername="sa";    
    String dbpassword ="a29751101"; 
    
    if(request.getParameter("uname")!=null) 
    {
        String user_name=request.getParameter("uname"); 
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            Connection con=DriverManager.getConnection(dburl,dbusername,dbpassword); 
           
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("SELECT * FROM users WHERE username=? "); 
            pstmt.setString(1,user_name); 
            ResultSet rs=pstmt.executeQuery(); 
            
            if(rs.next())               
            {  
                %>
                <span class="text-danger"> 帳號 <%=rs.getString("username")%> 已存在 ! </span>
                <%
            }
            else
            {
                %>
                <span class="text-success">帳號可使用!</span>
                <%
            }

            con.close(); //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
%>