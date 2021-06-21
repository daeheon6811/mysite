package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	String board = "board.";

	

	public BoardVo findByNo(Long Boardno) {
		
		System.out.println("findByNo");
		return sqlSession.selectOne(board+ "findByNo" ,  Boardno);
	}
	public boolean replyUpdate(int groupNo, int orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo",groupNo);
		map.put("orderNo",orderNo);
		
		int count = sqlSession.update("board.replyUpdate" , map);
		
		return count == 1;
		
	}
	public Boolean insert(BoardVo vo) {
	    int count =  sqlSession.insert("board.insert",vo);
	    return count == 1;
	}
	
	
	
    
	public Boolean delete(Long no) {
		int count = sqlSession.delete("board.delete" , no);
		return count == 1;
	}
	
	
	public double findAllCount() {
		
		System.out.println("findAllCount()");
		return sqlSession.selectOne("board.findAllCount");

	}

	
	public List<BoardVo> findAllSearch(String searchValue , int first , int second) {
		
	   Map<String, Object> map = new HashMap<String, Object>();
	   
	   map.put("searchValue",searchValue);
	   map.put("first", first);
	   map.put("second", second);	   
		

		return sqlSession.selectList("board.findAllSearch",map);
	
	}



	public void updateView(Long no , String title , String contents) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("no",no);
		map.put("title",title);
		map.put("contents",contents);
		
		sqlSession.update("board.updateView", map);
		
		
		
		
	}
	
	public int findMaxGroupNo() {
		return sqlSession.selectOne("board.findMaxGroupNo");
	}
	public int findMaxOrderNo() {
		int result = 0;

		
		return result;
	}
	public void updateHit(Long no) {
	
		System.out.println("updateHit");
		sqlSession.update("board.updateHit", no);

		
	}



	public List<BoardVo> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
