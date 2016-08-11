package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.mysite.vo.BoardVo;

public class BoardDao {
	public Connection getConnection() throws SQLException{
	      Connection conn = null;
	      
	      try{
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      conn = DriverManager.getConnection(url, "webdb", "webdb");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } 
	      
	      return conn;
	   }
	public List<BoardVo> getList(int page){
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			int start = ((page - 1) * 5) + 1;
			int end = page * 5;
			
			String sql = "select * from"
					+ "(SELECT c.*, rownum as rn FROM "
					+ "(select a.no, a.title, a.user_no, b.name, a.view_count, to_char(a.reg_date,'yyyy-mm-dd am hh24:mm:ss') from board a, users b where a.user_no = b.no) c)"
					+ "WHERE rn between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long userNo = rs.getLong(3);
				String userName = rs.getString(4);
				Integer viewCount = rs.getInt(5);
				String regDate = rs.getString(6);
//				rownum = rs.getInt(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setViewCount(viewCount);
				vo.setRegDate(regDate);
//				System.out.println(vo);
				
				list.add(vo);
				
				System.out.println(vo);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int countAll(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select count(*) from board";
			rs = stmt.executeQuery(sql);
			
			rs.next();
			
			count = rs.getInt(1);

		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
//	public List<BoardVo> getList(){
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		List<BoardVo> list = new ArrayList<BoardVo>();
//		
//		try {
//			conn = getConnection();
//			
//			stmt = conn.createStatement();
//			
//			String sql = "select a.no, a.title, a.user_no, b.name, a.view_count, to_char(a.reg_date,'yyyy-mm-dd am hh24:mm:ss') from board a, users b where a.user_no = b.no";
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()){
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				Long userNo = rs.getLong(3);
//				String userName = rs.getString(4);
//				Integer viewCount = rs.getInt(5);
//				String regDate = rs.getString(6);
//				
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setUserNo(userNo);
//				vo.setUserName(userName);
//				vo.setViewCount(viewCount);
//				vo.setRegDate(regDate);
////				System.out.println(vo);
//				
//				list.add(vo);
//			}
//
//		} catch (SQLException e){
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
	
	public BoardVo getView(Long bno){
		Connection conn = null;
		BoardVo vo = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select no, title, content, user_no from board where no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			rs = pstmt.executeQuery();
			
			//5. SQL 실행
			if(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userNo = rs.getLong(4);
				
				vo = new BoardVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userNo);
			}
			

		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}   
	
	public void insert(BoardVo vo) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = getConnection();
	         String sql = "insert into board values(seq_board.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContent());
	         pstmt.setInt(3, vo.getViewCount());
	         pstmt.setInt(4, vo.getGroupNo());
	         pstmt.setInt(5, vo.getOrderNo());
	         pstmt.setInt(6, vo.getDept());
	         pstmt.setLong(7, vo.getUserNo());
	         
//	         System.out.println(vo.getName());
//	         System.out.println(vo.getEmail());
//	         System.out.println(vo.getPassword());
	         
	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
	
	public void update(BoardVo vo){
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try{
			   conn = getConnection();

			   String sql= "update board set title = ?, content = ? where no=?";
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setString(1, vo.getTitle());
			   pstmt.setString(2, vo.getContent());
			   pstmt.setLong(3, vo.getNo());
		
			   pstmt.executeUpdate();
			   
		   } catch(SQLException e){
			   e.printStackTrace();
		   } finally{
			   try{
				   if(pstmt != null ){
					   pstmt.close();
				   }
				   if(conn != null ){
					   conn.close();
				   }
			   } catch(SQLException e){
				   e.printStackTrace();
			   }
		   }
	   }
	   
	public int delete(Long no){
			Connection conn = null;
			PreparedStatement pstmt = null;
			int count = 0;
			
			try {
				conn = getConnection();
				
				//3. SQL 준비
				String sql = "delete from board where no = ?";
				pstmt = conn.prepareStatement(sql);
				
				//4. 바인딩
				pstmt.setLong(1,no);
				
				//5. SQL 실행
				count = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL ERROR: "+e);
			}finally{
				try {
					if(pstmt != null){
						pstmt.close();
					}
					if(conn != null){
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("SQL ERROR: "+e);
				}
			}
			return count;
		}
}
