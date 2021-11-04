package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogrepository;

	public BlogVo getbloginfo(String id) {
		return blogrepository.getbloginfo(id);
		
	}

	
	
}
