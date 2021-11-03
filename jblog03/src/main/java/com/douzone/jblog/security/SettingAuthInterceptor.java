package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class SettingAuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login" );
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		
		//url substring 작업
		String url = request.getRequestURI();
		System.out.println(url);
		url.indexOf("/blog");
		System.out.println();
		int startindex = url.indexOf("/blog") + "/blog/".length(); //8
		int endindex = url.indexOf("/",startindex );
		String id = url.substring(startindex, endindex);
		System.out.println(id);
		
		UserVo userId = userService.getUserId(id);
		
		
		if(userId == null) {
			System.out.println("일치 아이디 없음 > 메인으로 이동함");
			response.sendRedirect(request.getContextPath());
			return false;
		}

		if(!userId.getId().equals(authUser.getId())) {
			System.out.println("아이디 일치하지 않음 > 메인으로 이동함");
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		System.out.println("아이디 일치, true함");
		return true;
	}
	
	
	
}
