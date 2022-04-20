package tw.dinero.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.util.DatabaseBean;

public class MessageDao {
	
	private DataSource dataSource;
	
	//MessageDao dao=new MessageDao(); 就會執行public MessageDao(){...}建構式
	public MessageDao() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/project2");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	//查詢全部
	public List<MessageBean> findAll(){
		try (Connection conn = dataSource.getConnection()){
			String sql="select * from message";
			Statement State = conn.createStatement();
			ResultSet rs = State.executeQuery(sql);
			List<MessageBean> list = new ArrayList<>();
			while(rs.next()) {
				MessageBean m = new MessageBean();
				m.setMessageId(rs.getInt("messageId"));
				m.setMessagetitle(rs.getString("messagetitle"));
				m.setMessage(rs.getString("message"));
				m.setMessagetime(rs.getTimestamp("messagetime"));
				m.setPic(rs.getString("pic"));
				m.setResponse(rs.getString("response"));
				m.setResponsetime(rs.getTimestamp("responsetime"));
			
				list.add(m);
			}
			rs.close();
			State.close();
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	
		
	//查詢 BY Id
		public List<MessageBean> selectMessage(int id) { 
			try(Connection conn =dataSource.getConnection()){
				
				String sql ="select * from message where messageId=?";
				PreparedStatement preState = conn.prepareStatement(sql);
				preState.setInt(1,id);
				ResultSet rs = preState.executeQuery();
				List<MessageBean> list = new ArrayList<>();
				while(rs.next()) {
					MessageBean m1 = new MessageBean();
					m1.setMessageId(rs.getInt("messageId"));
					m1.setMessagetitle(rs.getString("messagetitle"));
					m1.setMessage(rs.getString("message"));
					m1.setMessagetime(rs.getTimestamp("messagetime"));
					m1.setPic(rs.getString("pic"));
					m1.setResponse(rs.getString("response"));
					m1.setResponsetime(rs.getTimestamp("responsetime"));
				
					list.add(m1);
				}
				rs.close();
				preState.close();
				return list;
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	
	
	//新增留言
		public void addMessage(MessageBean m) {
			try (Connection conn = dataSource.getConnection()){
				Timestamp t = new Timestamp(Instant.now().toEpochMilli());
				
				String sql ="insert into message (messagetitle,message,messagetime) values(?,?,?)";
				PreparedStatement preState = conn.prepareStatement(sql);
				preState.setString(1, m.getMessagetitle());
				preState.setString(2,m.getMessage());
				preState.setTimestamp(3, t);
				preState.execute();
				preState.close();
				
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}	
	
		//刪除留言 BY Id
		public void deleteMessage(MessageBean m) {
			try(Connection conn = dataSource.getConnection()){				
				String sql = "delete message where messageId=?";
				PreparedStatement preState = conn.prepareStatement(sql);
				preState.setInt(1, m.getMessageId());
				preState.execute();
				preState.close();
//				int rs = preState.executeUpdate();
//				if(rs==0) {
//					throw new SQLException();
//				}		
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
		//修改by ID
		public void updateMessage(MessageBean m) {
			try(Connection conn =dataSource.getConnection()) {
				String sql = "update message set messagetitle=?,message=? where messageId=?";
				PreparedStatement preState = conn.prepareStatement(sql);
				preState.setString(1, m.getMessagetitle());
				preState.setString(2, m.getMessage());
				preState.setInt(3, m.getMessageId());
				preState.execute();
				preState.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				// TODO: handle exception
			}
		}
		
		
	public boolean isConnectedOK() {
		try(Connection conn = dataSource.getConnection()){
			return !conn.isClosed();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	

	
	
	
}
