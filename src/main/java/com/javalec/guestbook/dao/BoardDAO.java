package com.javalec.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javalec.guestbook.vo.BoardVO;

@Repository
public class BoardDAO {

	public BoardDAO() {
		System.out.println("BoardDAO() call");
	}

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

	public List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "SELECT SEQ, TITLE, WRITER, to_char(REGDATE, 'yyyy/mm/dd'), CNT FROM Board ORDER BY SEQ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String date = rs.getString(4);
				int cnt = rs.getInt(5);

				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setRegdate(date);
				vo.setCnt(cnt);
				list.add(vo);
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
		return list;
	}

	public BoardVO getBoard(int no) {
		BoardVO resultVO = new BoardVO();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// cnt 증가 실행하기
			// String sql2 = "update board~~~";
			// pstmt = conn.preparedStatement(sql);
			// pstmt.setInt(1,seq);
			// pstmt.executeUpdate();

			// getboard 받아오기
			String sql = "SELECT * FROM Board WHERE seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				int seq = rs.getInt(1);
				String id = rs.getString(2);
				String title = rs.getString(3);
				String writer = rs.getString(4);
				String content = rs.getString(5);
				String date = rs.getString(6);
				int cnt = rs.getInt(7);

				resultVO.setSeq(seq);
				resultVO.setId(id);
				resultVO.setTitle(title);
				resultVO.setWriter(writer);
				resultVO.setContent(content);
				resultVO.setRegdate(date);
				resultVO.setCnt(cnt);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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
		return resultVO;
	}

	public int insert(BoardVO vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = " insert into board values (board_seq.nextval, ?, ?, ?, ?, sysdate, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getContent());
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

	public int delete(int no) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "DELETE FROM Board WHERE SEQ=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error :" + e);
			e.printStackTrace();
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

//
	public int update(BoardVO vo) {

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "UPDATE Board SET title=?, writer=?, content=? where seq=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getSeq());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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

	public List<BoardVO> STitle(String key) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "SELECT SEQ, TITLE, WRITER, content, to_char(REGDATE, 'yyyy/mm/dd'), CNT from board where title like ? order by regdate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String regdate = rs.getString(5);
				int cnt = rs.getInt(6);

				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);

				list.add(vo);
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
		return list;
	}

	public List<BoardVO> SContent(String key) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "SELECT SEQ, TITLE, WRITER, content, to_char(REGDATE, 'yyyy/mm/dd'), CNT from board where content like ? order by regdate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String regdate = rs.getString(5);
				int cnt = rs.getInt(6);

				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);

				list.add(vo);
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
		return list;
	}


public int cntConunt(int no) {

	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();
		String sql = "update board set cnt=cnt+1 where seq=?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, no);

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
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
}
