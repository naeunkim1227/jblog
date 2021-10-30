package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("로그인 인터셉터 실행");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo authUser = userService.login(id, password);
		
		System.out.println(authUser.getId());
		System.out.println(authUser.getName());
		
		if(authUser == null) {
			System.out.println("회원 정보 없음");
			
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
		
			return false;
		}
		
		
		HttpSession session =request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
	
	
}
