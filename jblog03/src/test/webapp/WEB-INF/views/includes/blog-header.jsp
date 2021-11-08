<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h1>${blog.title }</h1>
<ul>
	<li><a href="${pageContext.request.contextPath}/user/logoutauth">로그아웃</a></li>
	<li><a
		href="${pageContext.request.contextPath}/blog/${authUser.id}/setting">블로그
			관리</a></li>
</ul>
