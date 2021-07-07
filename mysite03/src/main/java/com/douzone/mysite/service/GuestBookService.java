package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	GuestBookRepository guestBookRepository;

	
	public Long getMaxNo() {
		return guestBookRepository.MaxNo();
	}
	
	public List<GuestBookVo> getMessageList() {
		return guestBookRepository.findAll();
	}
	
	public List<GuestBookVo> getMessageList(Long no/* 기준 */) {
		return guestBookRepository.findAll(no);
	}

	public boolean deleteMessage(Long no, String password) {
		
		return guestBookRepository.deleteGuestBook(no, password);
		
	}

	public void addMessage(GuestBookVo vo) {
		
		guestBookRepository.insert(vo);

	}
	
	

}
