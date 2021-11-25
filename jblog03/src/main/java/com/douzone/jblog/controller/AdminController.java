package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.cotroller.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{userid}/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BlogService blogService;
	
	@Auth(role = "")
	@GetMapping({"/basic","" })
	public String SettingBasic(@AuthUser UserVo authUser, Model model) {
		BlogVo blog = blogService.getbloginfo(authUser.getId());
		model.addAttribute("blog", blog);
		
		return "blog/blog-admin-basic";
	}
	
	@Auth(role = "")
	@PostMapping("/basic")
	public String SettingBasic(@AuthUser UserVo authUser,@RequestParam(value = "title" , required = true, defaultValue = "") String title,
			@RequestParam(value = "logo-file") MultipartFile multipartFile
			) {
		adminService.settingbasic(authUser.getId() ,title, multipartFile);
		
		return "redirect:/blog/"+authUser.getId();
	}
	
	//category
	@Auth(role = "")
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public String SettingCate(@PathVariable(value = "userid") String userid,Model model) {
		
		List<CategoryVo> catelist = adminService.getcateinfo(userid);
		model.addAttribute("catelist",catelist);
		
		
		return "blog/blog-admin-category";

	}
	
	
	//ajax 카테고리 추가
	@ResponseBody
	@Auth(role = "")
	@RequestMapping(value="/addCate", method = RequestMethod.POST)
	public JsonResult AddCate(@RequestBody CategoryVo vo) {
		
		System.out.println(vo.getBlog_id());
		adminService.addCategory(vo);
		
		return JsonResult.success(vo);
	}
	
	
	//ajax 카테고리 삭제
	@Auth(role ="")
	@DeleteMapping("/deleteCate/{no}")
	public JsonResult DeleteCate(@PathVariable(value = "no") String no) {
		System.out.println("왓슴...제발....");
		int num = Integer.parseInt(no);
		
		adminService.deleteCategory(num);
		
		return JsonResult.success(no);
	}
	
	
	//write
	@Auth(role = "")
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String SettingWrite(@AuthUser UserVo authUser, @PathVariable(value = "userid") Optional<String> userid,Model model) {
		
		List<CategoryVo> catelist = adminService.getcateinfo(authUser.getId());
		model.addAttribute("catelist",catelist);
		
		return "blog/blog-admin-write";
	}
	
	
	
	// 글 쓰기
	@Auth(role = "")
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String SettingWrite(@AuthUser UserVo authUser ,Model model,
			@RequestParam(value = "title",required = true, defaultValue = "") String title,
			@RequestParam(value = "content",required = true, defaultValue = "") String content,
			@RequestParam(value = "category", required = true, defaultValue = "1") String no 
			) {
		
		System.out.println("cate" + no);
		PostVo vo = new PostVo();
		vo.setCategory_no(Long.parseLong(no));
		vo.setContents(content);
		vo.setTitle(title);

		
		adminService.write(vo, authUser.getId());
		
		return "redirect:/blog/"+ authUser.getId();
	}
	
	
	
}
