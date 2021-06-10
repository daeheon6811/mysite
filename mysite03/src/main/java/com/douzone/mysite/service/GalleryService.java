package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryRepository galleryRepository;
	


	public List<GalleryVo> findAllList() {
		return galleryRepository.findAllList();
	}

	public void insert(GalleryVo vo) {
		galleryRepository.insert(vo);
	}
	
	public void deleteNo (int no) {
		galleryRepository.deleteNo(no);
	}
	

}
