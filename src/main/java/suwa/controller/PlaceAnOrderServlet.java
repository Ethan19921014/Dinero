package suwa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDao;
import model.CartDto;
import model.CartItemDao;
import model.CartItemDtoBean;
import model.ProductDao;
import suwa.tool.DateTransformer;
import suwa.tool.GreenWorldService;
import suwa.tool.IdWorker;

/**
 * Servlet implementation class PlaceAnOrderServlet
 */
public class PlaceAnOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceAnOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toDo = request.getParameter("toDo");	
		if(toDo.equals("insert")) {
			
			insert(request, response);
			
		}else if(toDo.equals("delete")) {
			
			delete(request, response);
			
		}else if(toDo.equals("edit")) {
			
			edit(request, response);
			
		}else if(toDo.equals("toGreenService")) {
			toGreenService(request, response);
		}else if(toDo.equals("clearCart")) {
			clearCart(request, response);
		}else if (toDo.equals("checkOut")) {
			
			checkout(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		System.out.println("in insert step=========================");
		String preCustId =  Optional.ofNullable(request.getParameter("custId")).orElse("未輸入");
		String preProdId = Optional.ofNullable(request.getParameter("prodId")).orElse("未輸入");
		String prePrice = Optional.ofNullable(request.getParameter("price")).orElse("未輸入");
		String preQty = Optional.ofNullable(request.getParameter("qty")).orElse("未輸入");
		
		int custId = Integer.parseInt(preCustId) ;
		int prodId = Integer.parseInt(preProdId)  ;
		int price = Integer.parseInt(prePrice);
		int qty = Integer.parseInt(preQty);
		
		ProductDao productDao = new ProductDao();
		Integer stock = productDao.getProdStock(prodId);
		
		if(stock < qty) {
			
			throw new ServletException("購買數量過多");
		}
		
		HttpSession session = request.getSession(true);
		
		boolean existCart;
		
		CartDto cart = new CartDto();
	    CartDto cartSession = (CartDto)session.getAttribute("cartDto");
		//System.out.println("cart from session is null or not?");
		//System.out.println(cartSession);
	    
	    //判斷session 當中是否有購物車
	    //若沒有
		if(cartSession == null) {
			//System.out.println("in cart session is null");
			cart = new CartDto(custId);
			List<CartItemDtoBean> items = new ArrayList<CartItemDtoBean>();
			cart.setItems(items);
			existCart = false;
		//若有
		}else {
			//購物車當中是否有商品?
			
			//System.out.println("items ?????????");
			//System.out.println(cartSession.getItems());
			
			
			//有list
			if(!(null == cartSession.getItems())) {
				System.out.println("from 有 list");
				cart =cartSession;
				existCart = true;
				//有list 但 沒東西
				if(cartSession.getItems().isEmpty()){
					System.out.println("from 有list 但沒商品");
					existCart = false;
				}
				
				
			}else {
				//System.out.println("from 沒 list");
				cart = new CartDto(custId);
				List<CartItemDtoBean> items = new ArrayList<CartItemDtoBean>();
				cart.setItems(items);
				existCart = false;
			}
			
			
		}
		
		
		
		CartItemDtoBean cartItemDtoBean = new CartItemDtoBean(cart);
		
		
		//prodId
		cartItemDtoBean.setProdId(prodId);
		
		//p q  sum
		cartItemDtoBean.setPrice(price);
		cartItemDtoBean.setQty(qty);
		cartItemDtoBean.setItemTotal();
//		System.out.println("被新增商品");
//		System.out.println(cartItemDtoBean);
		
		List<CartItemDtoBean> items = cart.getItems();
		boolean addNewItem = false;
		if(existCart) {
			System.out.println("cart 中有 商品");
			
			//判斷 該商品是否已存在
			for(CartItemDtoBean motoItem : items) {
				 if (items.isEmpty()){
						//System.out.println("車子為空 跳出迴圈");
						//ConcurrentModificationException
						addNewItem = true;
						break;
					}
				
				if(motoItem.getProdId() == cartItemDtoBean.getProdId()) {
					//System.out.println("id 重複");
					motoItem.setQty(motoItem.getQty()+cartItemDtoBean.getQty());
					motoItem.setItemTotal(cartItemDtoBean.getItemTotal()+motoItem.getItemTotal());
					break;
				}else {
					//System.out.println("id不重複");
					addNewItem = true;
				}
			}
			
			if(addNewItem) {
				//System.out.println("在add new item 情況新增");
				cart.getItems().add(cartItemDtoBean);
			}
		}else {
			//System.out.println("在沒 add new item 情況新增");
			cart.getItems().add(cartItemDtoBean);
			
		}
		
				
		
		
		
		session.setAttribute("cartDto",cart);
		
		response.sendRedirect("/dinero/gewei/outcome.jsp");
	}
	
	private void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete=============");

		String indexStr = request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		HttpSession session = request.getSession();
		
		CartDto cartDto = (CartDto) session.getAttribute("cartDto");
		List<CartItemDtoBean> items = cartDto.getItems();
		items.remove(index);
		cartDto.setItems(items);
		
		session.setAttribute("cartDto", cartDto);
		
		request.getRequestDispatcher("/gewei/outcome.jsp").forward(request, response);
	}
	
	private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit=============");
		String newQty = request.getParameter("newQty");
		int newQtyInt = Integer.parseInt(newQty);
		
		String prodIdStr = request.getParameter("prodId");
		int prodId = Integer.parseInt(prodIdStr);
		
		ProductDao productDao = new ProductDao();
		Integer stock = productDao.getProdStock(prodId);
		
		
		//int index = (Integer)session.getAttribute("index");
		int index  = Integer.parseInt(request.getParameter("index"));
		HttpSession session = request.getSession();
		CartDto cartDto = (CartDto)session.getAttribute("cartDto");
		
		if(newQtyInt >stock || newQtyInt == 0) {
			cartDto.getItems().remove(index);
			throw new ServletException("購買數量過多或改為0");
		}
		
		
		//修改數量
		cartDto.getItems().get(index).setQty(newQtyInt);
		//修改總額
		cartDto.getItems().get(index).setItemTotal();
		
		int tmpCartTotal =0;
		
		List<CartItemDtoBean> items= cartDto.getItems();
		
		tmpCartTotal = items.stream().mapToInt((item)->item.getItemTotal()).reduce(0,(a,b)->a+b);
		
		cartDto.setCartTotal(tmpCartTotal);
		
		session.setAttribute("cartDto", cartDto);
		
		request.getRequestDispatcher("/gewei/outcome.jsp").forward(request, response);
	}
	
	public void checkout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkout=============");
		//從session 取得 cartDto
		
		//HttpSession session = request.getSession();
		//CartDto cartDto = (CartDto)session.getAttribute("cartDto");	
//		IdWorker idWorker = new IdWorker(1);
//		cartDto.setCartId(idWorker.nextIdStr());
//		DateTransformer dtf = new DateTransformer();
//		cartDto.setTradeDate(dtf.newStringDate());
		
		CartDto cartDto = (CartDto) request.getServletContext().getAttribute("cartDto");	
		
		System.out.println(cartDto);
		//bug
//		IdWorker idWorker = new IdWorker(1);
//		cartDto.setCartId(idWorker.nextIdStr());
		
		//set item cartId
		cartDto.setItemCartId();
		List<CartItemDtoBean> items = cartDto.getItems();
		
		
		
		CartDao cartDao = new CartDao();
		CartItemDao cartItemDao = new CartItemDao();
		ProductDao productDao = new ProductDao();
		
		
		try {
			cartDao.inserIntoDataBase(cartDto);
			items.forEach( item ->{
				int prodId = item.getProdId();
				cartItemDao.insertIntoTable(item);
				
				productDao.updateProdStock(prodId, productDao.getProdStock(prodId), item.getQty());
				
				});
			
			
		} catch (SQLException e) {
			System.out.println("新增失敗");
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/gewei/CreateOrder.jsp").forward(request, response);
		
	}
	
	public void toGreenService(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("totoGreenService=============");
		//從session 取得 cartDto
				HttpSession session = request.getSession();
				CartDto cartDto =(CartDto) session.getAttribute("cartDto");
				
				IdWorker idWorker = new IdWorker(1);
				cartDto.setCartId(idWorker.nextIdStr());
				DateTransformer dtf = new DateTransformer();
				cartDto.setTradeDate(dtf.newStringDate());
				
				session.setAttribute("cartDto", cartDto);
				request.getServletContext().setAttribute("cartDto", cartDto);
				
				GreenWorldService.initial();
					String form = GreenWorldService.genAioCheckOutAll(cartDto);
				 
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
//				System.out.println("被set前的cartDto"+cartDto);
//				session.setAttribute("cartDto",cartDto);
//				System.out.println("被set後的cartDto"+cartDto);
				out.print(form);

	}
	
	public void clearCart(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		CartDto cartDto = (CartDto)session.getAttribute("cartDto");
		//List<CartItemDtoBean> items = cartDto.getItems();
		
		cartDto.setItems(new ArrayList<CartItemDtoBean>());
		session.setAttribute("cartDto", cartDto);
		response.sendRedirect("/dinero/gewei/outcome.jsp");
		
	}
}
