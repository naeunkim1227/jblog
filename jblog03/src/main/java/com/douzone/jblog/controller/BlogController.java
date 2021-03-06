package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String index() {
		return "blog/blog-main";
	}
	
	@RequestMapping( "/{userid}")
	public String main(@AuthUser UserVo authUser, @PathVariable(value = "userid") Optional<String> userid , Model model) {
		List<PostVo> postlist = adminService.getpostinfo(userid.get());
		List<CategoryVo> categorylist = adminService.getcateinfo(userid.get());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postlist", postlist);
		map.put("categorylist", categorylist);
		
		model.addAttribute("map",map);
		
		return "/blog/blog-main";
	}
	
	@RequestMapping("{userid}/{list.category_no}/{list.no}")
	public String showwrite(@PathVariable(value = "userid") Optional<String> userid , 
			@PathVariable(value = "list.category_no") Long category_no, 
			@PathVariable(value = "list.no") Long no 
			,Model model ) {
		
		PostVo vo = new PostVo();
		
		vo.setNo(no);
		vo.setCategory_no(category_no);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<PostVo> picpost = adminService.getpostinfo(vo);
		List<PostVo> postlist = adminService.getpostinfo(userid.get());
		List<CategoryVo> categorylist = adminService.getcateinfo(userid.get());
		
		map.put("picpost", picpost);
		map.put("postlist", postlist);
		map.put("categorylist", categorylist);
		
		model.addAttribute("map",map);
		
		
		return "/blog/blog-main";
	
	}
}
