package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.douzone.jblog.repository.AdminRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminrepository;
	
	public List<PostVo> getpostinfo(String id) {
		return adminrepository.getpostinfo(id);
	}

	public List<CategoryVo> getcateinfo(String id) {
		return adminrepository.getcateinfo(id);
	}

	public void write(PostVo vo) {
		adminrepository.write(vo);
		
	}

}
