package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.batch.Main;

import bean.ActivityBean;
//import conn.ConnFactory;

public class ActivityDao {

	private Connection conn;

//	public ActivityDao() {
//		this.conn = ConnFactory.getConnection();
//	}

	private DataSource dataSource;

	public ActivityDao() {

		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/project2");
			System.out.println("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("�s�u����");
			e.printStackTrace();
		}

	}

	// �d�ߩҦ�
	public List<ActivityBean> readAllActivity() throws SQLException {
		List<ActivityBean> list = new ArrayList<>();

		String sql = "select * from activity order by actId ";
		try (Connection conn = dataSource.getConnection();) {
			// �o�̤��μg conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ActivityBean bean = new ActivityBean();
				bean.setActId(rs.getString(1));
				bean.setActName(rs.getString(2));
				bean.setActContent(rs.getString(3));
				bean.setEndDate(rs.getString(4));
				bean.setMemberRemain(rs.getString(5));
				bean.setMemberLimit(rs.getString(6));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// create�s�W
	public void createActivity(ActivityBean activityBean) throws SQLException { 
																				
		String sql = "insert into activity values (?,?,?,?,?,?)";

		try (Connection conn = dataSource.getConnection();) {
			// conn.setAutoCommit(false); �o�|�_���s�u�I

			PreparedStatement pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, activityBean.getActId());
			pstmt.setString(2, activityBean.getActName());
			pstmt.setString(3, activityBean.getActContent());
			pstmt.setString(4, activityBean.getEndDate());
			pstmt.setString(5, activityBean.getMemberRemain());
			pstmt.setString(6, activityBean.getMemberLimit());

			pstmt.execute(); 
			conn.commit();
			conn.setAutoCommit(true);
			pstmt.close();

			System.out.println("");

		} catch (SQLException e) {

			System.err.println("");
			e.printStackTrace();
		}

	}

//========================================================================================================================================

	
	//�z�L�s���Ӭd��
	public ActivityBean findAllActivity(String actId)
	{

		String sql = "select * from activity where actId =?";
		ActivityBean bean = new ActivityBean();
		try (Connection conn = dataSource.getConnection();) {
	
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			bean.setActId(rs.getString(1));
			bean.setActName(rs.getString(2));
			bean.setActContent(rs.getString(3));
			bean.setEndDate(rs.getString(4));
			bean.setMemberRemain(rs.getString(5));
			bean.setMemberLimit(rs.getString(6));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}

//========================================================================================================================================	

	// ����X�Ҧ����
	public ActivityBean selectByActId(String actId) throws SQLException {
		ActivityBean activityBean = null;
		String sql = "select * from activity where actId = ?";
		try (Connection conn = dataSource.getConnection();) {
			// conn.setAutoCommit(false); �o�|�_���s�u

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				activityBean = new ActivityBean(rs.getString("actName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityBean;
	}

	public ActivityBean findActivity(String actId)
	{

		String sql = "select * from activity where actId =?";
		ActivityBean bean = new ActivityBean();
		try (Connection conn = dataSource.getConnection();) {
			// �o�̤��μg conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			bean.setActId(rs.getString(1));
			bean.setActName(rs.getString(2));
			bean.setActContent(rs.getString(3));
			bean.setEndDate(rs.getString(4));
			bean.setMemberRemain(rs.getString(5));
			bean.setMemberLimit(rs.getString(6));
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}


	public void updateData(ActivityBean activityBean) throws SQLException { // , String actContent, String endDate,
		// String memberRemain, String memberLimit
		String sql = "update activity set actName = ?, actContent = ?, endDate = ?, memberRemain = ?, memberLimit = ? where actId = ?";

		System.out.println("��stest");
		try (Connection conn = dataSource.getConnection();) {
// conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, activityBean.getActName());
			stmt.setString(2, activityBean.getActContent());
			stmt.setString(3, activityBean.getEndDate());
			stmt.setString(4, activityBean.getMemberRemain());
			stmt.setString(5, activityBean.getMemberLimit());
			stmt.setString(6, activityBean.getActId());
			stmt.execute();
			stmt.close();

			System.out.println("");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("��s����");

	}


	// �z�L�s���ӧR��
	public void deleteByActId(String actId) throws SQLException { 

		try (Connection conn = dataSource.getConnection();) {

			String sql = "delete from Activity where actId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, actId);

			pstmt.execute();
			pstmt.close();
			System.out.println("");

		} catch (SQLException e) {

			System.err.println("���ʧR������");
			e.printStackTrace();
		}
	}


	//�ھ�keyword�ҽk�d��
	public List<ActivityBean> searchByActName(String keyword) {
		List<ActivityBean> list = new ArrayList<>();
		String sql = "select * from activity where actName like ?";
		try (Connection conn = dataSource.getConnection();) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + keyword + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ActivityBean a = new ActivityBean();
				a.setActId(rs.getString("actId"));
				a.setActName(rs.getString("actName"));
				a.setActContent(rs.getString("actContent"));
				a.setEndDate(rs.getString("endDate"));
				a.setMemberRemain(rs.getString("memberRemain"));
				a.setMemberLimit(rs.getString("memberLimit"));

				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
