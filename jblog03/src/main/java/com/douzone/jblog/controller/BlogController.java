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
@RequestMapping("/blog/{userid}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping("")
	public String main(@PathVariable(value = "userid") Optional<String> userid , Model model) {

		List<PostVo> postlist = blogService.getpostinfo(userid.get());
		List<PostVo> categorylist = blogService.getcateinfo(userid.get());
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postlist", postlist);
		map.put("categorylist", categorylist);
		
		model.addAttribute("map",map);
		return "blog/blog-main";
	}
	
	
	@Auth(role = "")
	@RequestMapping("/setting")
	public String SettingBasic() {
		return "blog/blog-admin-basic";
	}
	
	@Auth(role = "")
	@RequestMapping("/setting/category")
	public String SettingCate() {
		return "blog/blog-admin-category";
	}
	
	@Auth(role = "")
	@RequestMapping("/setting/write")
	public String SettingWrite() {
		return "blog/blog-admin-write";
	}
	

	

	
	
}
