package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;

public class LogoutInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		System.out.println("로그아웃 인터셉터 실행");
		
		HttpSession session = request.getSession();
		if(session == null) {
			return false;
		}
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
	
	
	
}
