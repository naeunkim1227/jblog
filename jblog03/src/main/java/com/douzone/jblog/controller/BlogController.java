package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@RequestMapping("/{authUser.id}")
	public String index(@AuthUser UserVo authUser) {
		return "blog/blog-main";
	}
	
	
	
}
