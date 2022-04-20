package suwa.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ProductBase64Dao;
import model.ProductDao;
import model.ProductDto;

/**
 * Servlet implementation class ProdunctController
 */
@WebServlet("/ProductController")

@MultipartConfig(
fileSizeThreshold = 1024*1024*2, //2mb
maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*50		
		)

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private  static final String SAVE_DIR = "uploadFiles";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String toDo = request.getParameter("toDo");
		if(toDo.equals("create")) {
			createBase64(request, response);
//			create(request, response);
//			saveImg(request, response);
			getServletContext().getRequestDispatcher("/gewei/CreateProSuccess.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	private void saveImg(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		String appPath =  request.getServletContext().getRealPath("");
		
		System.out.println(appPath);
		
		String savePath = appPath+ File.separator+SAVE_DIR;
		
		File fileSavDir = new File(savePath);
		if(!fileSavDir.exists()) {
			fileSavDir.mkdir();
		}
		
		for(Part part :request.getParts()) {
			if(part.getName().startsWith("file")) {
			String fileName = extractFileName(part);
			//String fileName = "test.jpg";
			System.out.println(fileName);
			fileName = new File(fileName).getName();
			System.out.println(fileName);
			part.write(savePath + File.separator +fileName);
			}
		}
		
		request.setAttribute("message", "Upload has been done successfully!");
        
	}
	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] headers = contentDisp.split(";");
		
		for(String s:headers) {
			if(s.trim().startsWith("filename")) {
				return s.substring( s.indexOf("=") + 2 , s.length() - 1 );
			}
		}
		
		return "";
	}
	
	public void create(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		
		
		String prodIdStr = request.getParameter("prodId");
		String stockStr = request.getParameter("stock");
		String priceStr = request.getParameter("price");
		
		int prodId;
		int stock;
		int price;
		
		String prodName = request.getParameter("prodName");
		String descript = request.getParameter("descript");
		String category = request.getParameter("category");
		String petkind = request.getParameter("petkind");
		
		try {
			prodId = Integer.parseInt(prodIdStr);
			stock = Integer.parseInt(stockStr);
			price = Integer.parseInt(priceStr);
			
		} catch (NumberFormatException e) {
			throw new ServletException(e);
		}
		
		
		
		ProductDto product = new ProductDto();
		product.setProdId(prodId);
		product.setStock(stock);
		product.setPrice(price);
		product.setProdName(prodName);
		product.setPetkind(petkind);
		product.setDescript(descript);
		product.setCategory(category);
		
		ProductDao dao = new ProductDao();
		
		dao.create(product);
		
	}
	
	public void createBase64(HttpServletRequest request,HttpServletResponse response) throws ServletException {
		;
		
		//prodId
		int prodId;
		try {
			prodId = Integer.parseInt(request.getParameter("prodId"));	
		} catch (NumberFormatException e) {
			throw new ServletException(e);
		}
		
		//prodName
		String prodName =  request.getParameter("prodName");
		
		//base64Str
		String base64Str = request.getParameter("base64Str");
		
		ProductBase64Dao dao = new ProductBase64Dao();
		
		dao.insertIntoDataBase(prodId, prodName, base64Str);
	}

}
