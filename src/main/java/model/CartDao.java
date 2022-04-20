package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.DTD;

import connectionFac.ConnectionFactory;
import suwa.tool.DateTransformer;
import suwa.tool.IdWorker;

public class CartDao {

	Connection conn;

	// 建立連線1
	public CartDao() {

		// this.conn = ConnectionFactory.getConnection();
		conn = ConnectionFactory.createOneConnection();

	}

	// 建立連線2
	public CartDao(Connection conn) {
		this.conn = conn;
	}

	public static void main(String[] args) {
		CartDao cartDao = new CartDao();
		CartDto cart = new CartDto();
//		cart.setCartTotal(82000);
//		cart.setCustId(666);
//		try {
		// 新增test
//			cartDao.inserIntoDataBase(cart);

		// 查詢多筆 test
//			List<CartDto> ords = cartDao.selectOrdByCustId(667);
//			cartDao.showOrds(ords);

//			
//			//刪除test
//			//1323064935927779328
//			String ordStr = "1323064935927779328";
//			cartDao.deleteOrd(ordStr);

		// 搜尋test
//		try {
//			List<CartDto> selectAllCart = cartDao.SelectAllCart();
//			selectAllCart.forEach(System.out::println);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			

		// id查詢test

//		CartDto ord = cartDao.selectByCartId("1324170425835458560");
//		System.out.println(ord);

//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// 日期查詢test

		try {
			List<CartDto> ords = cartDao.selectByDate("", "安安安");
			ords.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserIntoDataBase(CartDto cartDto) throws SQLException {
		String sqlQuery = "Insert into Cart(cartId,custId,total,tradeDate) values(?,?,?,?)";

		conn.setAutoCommit(false);

		PreparedStatement prepareStatement = conn.prepareStatement(sqlQuery);

		prepareStatement.setString(1, cartDto.getCartId());
		prepareStatement.setInt(2, cartDto.getCustId());
		prepareStatement.setInt(3, cartDto.getCartTotal());
		prepareStatement.setString(4, cartDto.getTradeDate());

		prepareStatement.executeUpdate();

		conn.commit();
		conn.setAutoCommit(true);

		System.out.println("新增成功");
		prepareStatement.close();

	}

	public List<CartDto> selectOrdByCustId(int custid) {

		PreparedStatement preState = null;
		ResultSet rs = null;

		try {
			String sqlQuery = "select * from cart where custId = ?";

			preState = conn.prepareStatement(sqlQuery);

			preState.setInt(1, custid);

			List<CartDto> ords = new ArrayList<CartDto>();

			rs = preState.executeQuery();

			while (rs.next()) {

				CartDto ord = new CartDto();
				ord.setCartId(rs.getString(1));
				ord.setCustId(custid);
				ord.setCartTotal(rs.getInt(3));
				ord.setTradeDate(rs.getString(4));
				ords.add(ord);
			}

			return ords;

		} catch (SQLException e) {
			System.out.println("查無結果");
			return null;
		} finally {
			try {
				rs.close();
				preState.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public CartDto selectByCartId(String cartId) {

		PreparedStatement preState = null;
		ResultSet rs = null;

		try {
			String sqlQuery = "select * from cart where cartId =?";
			preState = conn.prepareStatement(sqlQuery);
			preState.setString(1, cartId);
			rs = preState.executeQuery();

			rs.next();
			CartDto ord = new CartDto();

			ord.setCartId(rs.getString(1));
			ord.setCustId(rs.getInt(2));
			ord.setCartTotal(rs.getInt(3));
			ord.setTradeDate(rs.getString(4));

			return ord;
		} catch (SQLException e) {

			System.out.println("查無結果");
			return null;
		} finally {
			try {
				rs.close();
				preState.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public List<CartDto> selectByDate(String minDate, String maxDate) throws SQLException {

		String sqlQuery = "Select * from cart where tradeDate between ? and ?";

		if (minDate == null || minDate == "") {
			minDate = "1996-11-30";
		}

		if (maxDate == null || maxDate == "") {
			maxDate = "2056-11-30";
		}

		maxDate = new StringBuilder(maxDate).append(" 23:59").toString();

		PreparedStatement preState = conn.prepareStatement(sqlQuery);
		preState.setString(1, minDate);
		preState.setString(2, maxDate);
		ResultSet rs = preState.executeQuery();
		List<CartDto> ords = new ArrayList<CartDto>();

		while (rs.next()) {
			CartDto ord = new CartDto();
			ord.setCartId(rs.getString(1));
			ord.setCustId(rs.getInt(2));
			ord.setCartTotal(rs.getInt(3));
			ord.setTradeDate(rs.getString(4));
			ords.add(ord);
		}
		return ords;
	}

	public List<CartDto> selectByTotal(String minStr, String maxStr) throws SQLException {

		String sqlQuery = "Select * from cart where total between ? and ?";
		// deal with null and exception
		int min;
		int max;

		if (minStr == null || minStr == "") {
			minStr = "0";

		}

		if (maxStr == null || maxStr == "") {
			maxStr = "2147483646";

		}

		try {
			min = Integer.parseInt(minStr);
			if (min < 0) {
				min = 0;
			}
		} catch (NumberFormatException e) {
			min = 0;
		}

		try {
			max = Integer.parseInt(maxStr);
		} catch (NumberFormatException e) {
			max = 2147483646;
		}

		PreparedStatement preState = conn.prepareStatement(sqlQuery);
		preState.setInt(1, min);
		preState.setInt(2, max);
		ResultSet rs = preState.executeQuery();
		List<CartDto> ords = new ArrayList<CartDto>();

		while (rs.next()) {
			CartDto ord = new CartDto();
			ord.setCartId(rs.getString(1));
			ord.setCustId(rs.getInt(2));
			ord.setCartTotal(rs.getInt(3));
			ord.setTradeDate(rs.getString(4));
			ords.add(ord);
		}
		return ords;
	}

	public List<CartDto> SelectAllCart() throws SQLException {
		String sqlString = "Select * from cart";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sqlString);
		List<CartDto> ords = new ArrayList<CartDto>();
		while (rs.next()) {
			CartDto ord = new CartDto();

			ord.setCartId(rs.getString(1));
			ord.setCustId(rs.getInt(2));
			ord.setCartTotal(rs.getInt(3));
			ord.setTradeDate(rs.getString(4));
			ords.add(ord);

		}

		return ords;

	}

	//
	public void deleteOrd(String cartId) throws SQLException {
		String sqlQuery = "delete from cart where cartId = ?";

		PreparedStatement preState = conn.prepareStatement(sqlQuery);
		preState.setString(1, cartId);

		preState.execute();

		System.out.println("刪除成功");

		preState.close();
	}

	public void showOrds(List<CartDto> ords) {
		ords.stream().forEach(System.out::println);
	}

	public List<CartDto> advancedSelect(String cartIdParam, String custIdParam
			, String minStr, String maxStr,String minDateStr ,String maxDateStr)
			throws SQLException {
		CartDao dao = new CartDao();
		List<CartDto> ords = dao.SelectAllCart();
		//cartId 搜尋
		List<CartDto> answer = ords.stream().filter(o -> {
			if (cartIdParam != null && cartIdParam != "") {
				return o.getCartId().equals(cartIdParam);
			} else {
				return true;
			}
		// custId 搜尋	
		}).filter(o -> {
			if (custIdParam != null && custIdParam != "") {
				int parseInt = Integer.parseInt(custIdParam);
				return o.getCustId() == parseInt;
			} else {
				return true;
			}
		// price 搜尋
		}).filter(o -> {
			int min;
			int max;
			if (minStr == null || minStr == "") {
				min = 0;
			} else {
				min = Integer.parseInt(minStr);
			}
			
			if (maxStr == null || maxStr == "") {
				max = Integer.MAX_VALUE;
			} else {
				max = Integer.parseInt(maxStr);
			}
			
			if(max < min) {
				int tmp = max;
				max = min;
				min = tmp;
			}
			
			return o.getCartTotal() >= min && o.getCartTotal() <= max;
		// date 搜尋
		}).filter(o->{
			String censoredMinDate;
			String censoredMaxDate;
			if (minDateStr == null || minDateStr == "") {
				censoredMinDate = "1996-11-30";
			}else {
				censoredMinDate = minDateStr;
			}
			
			if (maxDateStr == null || maxDateStr == "") {
				censoredMaxDate = "2056-11-30";
			} else {
				censoredMaxDate = maxDateStr;
			}
			DateTransformer dtf = new DateTransformer();
			Date minTimestamp = null;
			Date maxTimestamp = null;
			Timestamp ordTimeStamp = null;
			try {
				minTimestamp = dtf.strToJavaDate(censoredMinDate);
				maxTimestamp = dtf.strToJavaDate(censoredMaxDate);
				ordTimeStamp = dtf.sqlDateStrToCalendar(o.getTradeDate());
				
				if(minTimestamp.after(maxTimestamp)) {
					
					Date tmpDate = minTimestamp;
					minTimestamp = maxTimestamp;
					maxTimestamp = tmpDate;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return ordTimeStamp.compareTo(minTimestamp) >= 0 && ordTimeStamp.compareTo(maxTimestamp) <= 0;
		}).collect(Collectors.toList());
		//.forEach(System.out::println);

		return answer;
	}
}
