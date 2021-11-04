package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{userid}/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Auth(role = "")
	@GetMapping("")
	public String SettingBasic(@AuthUser UserVo authUser) {
		System.out.println("어드민 컨트롤러 옴");
		return "blog/blog-admin-basic";
	}

	@Auth(role = "")
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public String SettingCate() {
		return "blog/blog-admin-category";

	}
	
	@Auth(role = "")
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String SettingWrite(@AuthUser UserVo authUser, @PathVariable(value = "userid") Optional<String> userid,Model model) {
		
		List<CategoryVo> catelist = adminService.getcateinfo(authUser.getId());
		model.addAttribute("catelist",catelist);
		
		return "blog/blog-admin-write";
	}
	
	@Auth(role = "")
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String SettingWrite(@AuthUser UserVo authUser ,Model model,
			@RequestParam(value = "title",required = true, defaultValue = "") String title,
			@RequestParam(value = "content",required = true, defaultValue = "") String content,
			@RequestParam(value = "category", required = true, defaultValue = "1") String no 
			) {
		
		PostVo vo = new PostVo();
		vo.setCategory_no(Long.parseLong(no));
		vo.setContents(content);
		vo.setTitle(title);
		
		adminService.write(vo);
		
		return "redirect:/blog/"+ authUser.getId();
	}
	
	
	
}
