package com.douzone.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		return "user/login"; 
	}
	
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, 
			BindingResult result, 
			Model model) {
		
		if(result.hasErrors()) {
		
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userservice.join(vo);
		String id = vo.getId();
		System.out.println(id);
		blogService.makebloginfo(id);
		
		return "redirect:/user/joinsuccess";
	}
	
	
	
}
