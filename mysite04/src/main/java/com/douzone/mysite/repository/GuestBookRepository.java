package com.douzone.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSource dataSource;

	public Boolean insert(GuestBookVo vo) {
		
		System.out.println(vo);
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return count == 1;
	}

	public Boolean deleteGuestBook(Long no, String password) {
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		int count = sqlSession.delete("guestbook.delete",vo);
		return count == 1;
	}

	public List<GuestBookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

}
