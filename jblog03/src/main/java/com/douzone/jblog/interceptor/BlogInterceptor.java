package com.douzone.jblog.interceptor;

import java.util.HashMap;
import java.util.Map;

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
		
		String url = request.getRequestURI();
		url.indexOf("/blog");
		int startindex = url.indexOf("/blog") + "/blog/".length();
		String id = url.substring(startindex, url.length());
		
		System.out.println(id + "의 블로그 정보 가져오기");
		
		// user/categoryno/boardno 의 경우 parse해주기 
		if(id.contains("/")) {
			int num = (id.lastIndexOf("/")-2);
			System.out.println(num);
			id = id.substring(0, num);
			System.out.println("id는?" + id);
		}
		
		BlogVo blog =  blogService.getbloginfo(id);

		
		if(blog == null ) {
			System.out.println(id + "의 블로그 없음 / 메인 이동");
			response.sendRedirect(request.getContextPath());
			return false; 
		}
		request.getServletContext().setAttribute("blog", blog);
			
		return true;
	}
	
}
