package com.javalec.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.javalec.guestbook.vo.UsersVO;


@Repository
public class UsersDAO {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버로딩실패 : " + e);
		}
		return conn;
	}

	public int insert(UsersVO vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into users values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getRole());
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public UsersVO selectUser(UsersVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UsersVO resultVo = new UsersVO() ;
		try {
			conn = getConnection();

			String sql = "select id, password from users where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultVo.setId(rs.getString(1));
				resultVo.setPassword(rs.getString(2));
				
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return resultVo;
	}
	
//	public UsersVO login(UsersVO vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		UsersVO result = new UsersVO();
//		try {
//			conn = getConnection();
//
//			String sql = "select id, password from users where id=? and password=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getId());
//			pstmt.setString(2, vo.getPassword());
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String password = rs.getString("password");
//				result.setPassword(password);
//				result.setId(id);
//			}
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//
//			try {
//				if (rs != null)
//					rs.close();
//
//				if (pstmt != null)
//					pstmt.close();
//
//				if (conn != null)
//					conn.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return result;
//	}


}
