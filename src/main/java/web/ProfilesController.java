//package web;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.ProfilesDao;
//import dao.ProfilesDaoImpl;
//import model.Profiles;
//
///**
// * ControllerServlet.java This servlet acts as a page controller for the
// * application, handling all requests from the todo.
// * 
// * @email Ramesh Fadatare
// */
//
//@WebServlet("/")
//public class ProfilesController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static final long ProfileID = 0;
//	private ProfilesDao ProfilesDao;
//
//	public void init() {
//		ProfilesDao = new ProfilesDaoImpl();
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getServletPath();
//
//		try {
//			switch (action) {
//			case "/new":
//				showNewForm(request, response);
//				break;
//			case "/insert":
//				insertProfiles(request, response);
//				break;
//			case "/delete":
//				deleteProfiles(request, response);
//				break;
//			case "/edit":
//				showEditForm(request, response);
//				break;
//			case "/update":
//				updateProfiles(request, response);
//				break;
//			case "/list":
//				listProfiles(request, response);
//				break;
//			default:
//				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
//				dispatcher.forward(request, response);
//				break;
//			}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
//	}
//
//	private void listProfiles(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		List<Profiles> ListProfiles = ProfilesDao.selectAllProfiles();
//		request.setAttribute("ListProfiles", ListProfiles);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("profiles/profiles-list.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("profiles/profiles-form.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		int ProfileID = Integer.parseInt(request.getParameter("ProfileID"));
//		Profiles existingProfiles = ProfilesDao.selectProfiles(ProfileID);
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher("profiles/profiles-form.jsp");
//		request.setAttribute("Profiles", existingProfiles);
//		dispatcher.forward(request, response);
//
//	}
//
//	private void insertProfiles(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//		
//		String gender = request.getParameter("gender");
//		String Email = request.getParameter("Email");
//		String cellphone = request.getParameter("cellphone");
//		
////		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
////		LocalDate birthday = LocalDate.parse(request.getParameter("birthday"),df);
//		
//		String address = request.getParameter("address");
//		Profiles newProfiles = new Profiles(gender, Email, cellphone,  address);
//
//		ProfilesDao.insertProfiles(newProfiles);
//		response.sendRedirect("list");
//	}
//
//	private void updateProfiles(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//		int ProfileID = Integer.parseInt(request.getParameter("ProfileID"));	
//		String gender = request.getParameter("gender");
//		String Email = request.getParameter("Email");
//		String cellphone = request.getParameter("cellphone");
////		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
////		LocalDate birthday = LocalDate.parse(request.getParameter("birthday"),df);
//		String address = request.getParameter("address");
//		Profiles updateProfiles = new Profiles(ProfileID, gender, Email, cellphone,  address);
//		
//		ProfilesDao.updateProfiles(updateProfiles);
//		
//		response.sendRedirect("list");
//	}
//
//	private void deleteProfiles(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//		int ProfileID = Integer.parseInt(request.getParameter("ProfileID"));
//		ProfilesDao.deleteProfiles(ProfileID);
//		response.sendRedirect("list");
//	}
//}
