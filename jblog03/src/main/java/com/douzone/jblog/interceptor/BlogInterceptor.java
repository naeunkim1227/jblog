package com.douzone.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("블로그 인터셉터 실행!");
		
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
		
		BlogVo blog = (BlogVo) request.getServletContext().getAttribute("blog");
		
		if(blog == null) {
			System.out.println("블로그가 널입니다...");
			blog =  blogService.getbloginfo(authUser.getId());
			
			if(blog == null) {
				blogService.makebloginfo(authUser.getId());
				blog = blogService.getbloginfo(authUser.getId());
			}
			
			request.getServletContext().setAttribute("blog", blog);
		}
		
		return true;
				
	}
	
}
