package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String view() {
		return "blog/blog-admin-write";
	}
	
	
	@RequestMapping("/{userid}")
	public String main(@PathVariable(value = "userid") Optional<String> userid , Model model) {

		BlogVo blogvo =  blogService.getbloginfo(userid.get());
		List<PostVo> postlist = blogService.getpostinfo(userid.get());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogvo", blogvo);
		map.put("postlist", postlist);
		
		model.addAttribute("map",map);
		return "blog/blog-main";
	}
	
	
	@Auth(role = "")
	@RequestMapping("/{userid}/setting")
	public String test() {
		return "blog/blog-admin-basic";
	}
	

	

	
	
}
