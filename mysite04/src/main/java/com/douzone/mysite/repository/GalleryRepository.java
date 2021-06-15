package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	private static final String namespace = "gallery.";

	@Autowired
	SqlSession sqlSession;

	public List<GalleryVo> findAllList() {
		return sqlSession.selectList(namespace + "findAllList");
	}
	
	public GalleryVo findByNo(int no) {
		return sqlSession.selectOne(namespace + "findByNo");
	}

	public void insert(GalleryVo vo) {
		sqlSession.insert(namespace + "insert" , vo);
	}
	
	public void deleteNo (int no) {
		sqlSession.delete(namespace + "deleteNo" , no); 
	}
	

}
