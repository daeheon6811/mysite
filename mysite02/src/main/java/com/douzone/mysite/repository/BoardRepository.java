package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardRepository {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.80.119:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("ok:");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
	
	
	
	public Boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			String sql = " insert into board values(null , ? , ? ,  sysdate(), ? , ? ,? , ? ,? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getGroupNo());
			pstmt.setInt(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	
	
    
	public Boolean Delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public int findAllCount() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =" select count(*) from board" ;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				result = rs.getInt(1);	
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
		
	}

	public List<BoardVo> findAll(int first , int second) {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =" select a.no ,  a.title  , a.contents , a.reg_date, a.depth , a.hit , b.no as user_no , b.name"
					+ " from board a ,user b"
					+ " where a.user_no = b.no"
					+ " order by a.group_no  desc  , a.order_no asc limit ? , ?" ;
			pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, first);
					pstmt.setInt(2, second);
					
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				int depth = rs.getInt(5);
				int hit = rs.getInt(6);
				Long userNo = rs.getLong(7);
				String userName = rs.getString(8);
	

				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRedDate(regDate);
				vo.setDepth(depth);
				vo.setHit(hit);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
		
				
				result.add(vo);			
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public List<BoardVo> findAllSearch(String searchValue , int first , int second) {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =" select a.no ,  a.title  , a.contents , a.reg_date, a.depth , a.hit , b.no as user_no , b.name"
					+ " from board a ,user b"
					+ " where a.user_no = b.no"
					+ " and a.title like '%" + searchValue + "%'"
					+ " order by a.no desc , a.group_no  desc  , a.order_no asc limit ? , ?" ;
			pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, first);
					pstmt.setInt(2, second);
					
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				int depth = rs.getInt(5);
				int hit = rs.getInt(6);
				Long userNo = rs.getLong(7);
				String userName = rs.getString(8);
	

				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRedDate(regDate);
				vo.setDepth(depth);
				vo.setHit(hit);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
		
				
				result.add(vo);			
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}







	public BoardVo findByNo(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =" select title , contents , reg_date , user_no from board where no = ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				String regDate = rs.getString(3);
				Long userNo = rs.getLong(4);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRedDate(regDate);
				vo.setUserNo(userNo);
				
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}



	public BoardVo updateView(Long no , String title , String contents) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql = "update board set title = ? , contents = ?  where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);			
			pstmt.setLong(3, no);
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if (pstmt != null) {
					pstmt.close();
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
	
	public void updateHit(Long no) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql = "update board set hit = hit + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);			

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}



	public List<BoardVo> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
