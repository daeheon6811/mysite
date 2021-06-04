package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;


	
	

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

	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}




	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo result = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("e", email);
		map.put("p",password);
				
		return sqlSession.selectOne("user.findByEmailAndPassword" , map);
		
	}

	public UserVo findByNo(Long no) {
		
		return sqlSession.selectOne("user.findByNo", no);
	}
	
	public void update(UserVo userVo) {
		
		sqlSession.update("user.update", userVo);
	
		
	}

	
}
