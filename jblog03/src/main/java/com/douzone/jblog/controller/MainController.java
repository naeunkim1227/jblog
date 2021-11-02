package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.vo.UserVo;

@Controller
public class MainController {

	@RequestMapping({"","/main"})
	public String index(@AuthUser UserVo authUser) {
		return "main/index";
	}
	
	
}
