package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
	   
	public List<BoardVo> getList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select a.no, a.title, a.user_no, b.name, a.view_count, to_char(a.reg_date,'yyyy-mm-dd am hh24:mm:ss') from board a, users b where a.user_no = b.no";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long userNo = rs.getLong(3);
				String userName = rs.getString(4);
				Integer viewCount = rs.getInt(5);
				String regDate = rs.getString(6);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setViewCount(viewCount);
				vo.setRegDate(regDate);
				
//				System.out.println(vo);
				
				list.add(vo);
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
	
//	public void insert(BoardVo vo) {
//	      Connection conn = null;
//	      PreparedStatement pstmt = null;
//	      
//	      try {
//	         conn = getConnection();
//	         String sql = "insert into board values(seq_board.nextval, ?, ?, ?, ?)";
//	         pstmt = conn.prepareStatement(sql);
//	         
//	         pstmt.setString(1, vo.getName());
//	         pstmt.setString(2, vo.getEmail());
//	         pstmt.setString(3, vo.getPassword());
//	         pstmt.setString(4, vo.getGender());
//	         
//	         System.out.println(vo.getName());
//	         System.out.println(vo.getEmail());
//	         System.out.println(vo.getPassword());
//	         
//	         pstmt.executeUpdate();
//	         
//	      } catch (SQLException e) {
//	         e.printStackTrace();
//	      }
//	   }
//	
//	public BoardVo update(BoardVo vo){
//		   Connection conn = null;
//		   PreparedStatement pstmt = null;
//		   
//		   try{
//			   conn = getConnection();
//			   
//			   Long no = vo.getNo();
//			   String name = vo.getName();
//			   String password = vo.getPassword();
//			   String gender = vo.getGender();
//			   
//			   boolean isPasswordEmpty = "".equals(password);
//			   
//			   String sql = null;
//			   
//			   if(isPasswordEmpty == true){
//				   sql = "update users set name = ?, gender = ? where no = ?";
//			   } else{
//				   sql= "update users set name = ?, password = ?, gender = ? where no = ?";
//			   }
//			   
//			   pstmt = conn.prepareStatement(sql);
//			   
//			   if(isPasswordEmpty == true){
//				   pstmt.setString(1, name);
//				   pstmt.setString(2, gender);
//				   pstmt.setLong(3, no);
//			   } else{
//				   pstmt.setString(1, name);
//				   pstmt.setString(2, password);
//				   pstmt.setString(3, gender);
//				   pstmt.setLong(4, no);
//			   }
//			   
//			   pstmt.executeUpdate();
//			   
//		   } catch(SQLException e){
//			   e.printStackTrace();
//		   } finally{
//			   try{
//				   if(pstmt != null ){
//					   pstmt.close();
//				   }
//				   if(conn != null ){
//					   conn.close();
//				   }
//			   } catch(SQLException e){
//				   e.printStackTrace();
//			   }
//		   }
//		   
//		   return vo;
//	   }
//	   
//	public int delete(Long no){
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			int count = 0;
//			
//			try {
//				conn = getConnection();
//				
//				//3. SQL 준비
//				String sql = "delete from board where no = ?";
//				pstmt = conn.prepareStatement(sql);
//				
//				//4. 바인딩
//				pstmt.setLong(1,no);
//				
//				//5. SQL 실행
//				count = pstmt.executeUpdate();
//				
//			} catch (SQLException e) {
//				System.out.println("SQL ERROR: "+e);
//			}finally{
//				try {
//					if(pstmt != null){
//						pstmt.close();
//					}
//					if(conn != null){
//						conn.close();
//					}
//				} catch (SQLException e) {
//					System.out.println("SQL ERROR: "+e);
//				}
//			}
//			return count;
//		}
}
