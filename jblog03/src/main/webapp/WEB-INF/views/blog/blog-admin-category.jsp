<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	
	
	$("#btn-add").click(function(){
		
		var vo = {
				blog_id : $("#userid").val(),
				name: $("#name").val(),
				desc: $("#desc").val()
			}
			
		var num = $("#num").val() + 1 ;
		
			$.ajax({
				url: "${pageContext.request.contextPath}/${userid}/admin/addCate",
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(vo), 
				success: function(response){
					if(response.result == "success"){
					
					alert('추가');
					
					var html = "";
					html += ("<tr><td>"+num+"</td>");			
					html += ("<td>"+ response.data.name	+"</td>");			
					html += ("<td>"+  0	+"</td>");			
					html += ("<td>"+ response.data.desc	+"</td>");			
					html += ("<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg' class='btn-del' id='${list.no}'></td></tr>");			
					
					$(".admin-cat").append(html);
					$("#name").val('');
					$("#desc").val('');
					
						
					}
				}
				
				
			})
	});
	
	
	
	$(".btn-del").click(function(){
		
		var no	= $(this).attr('id');
		
		$.ajax({
			url: "${pageContext.request.contextPath}/${userid}/admin/deleteCate/" + no,
			type: "delete",
			data: '',
			dataType: 'json',
			success: function(response){
				console.log(response);
				/* if(response.result == "success"){
				alert('삭제 완료');
				 var te = $(this).parent();
				
				 console.log(te);
				} */
				
			}
			
		}) 
		
	
	});
	
	
	
	
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-header.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${catelist }" varStatus="st" var="list">
		      		<tr id="row">
						<td id="num">${st.index + 1}</td>
						<td>${list.name}</td>
						<td>${list.postcnt}</td>
						<td>${list.desc }</td>
						<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg" class="btn-del" id="${list.no}">${list.no}</td>
					</tr>  
		      		</c:forEach>
				
								  
				</table>
			
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" id="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<input type="hidden" value= "${ authUser.id }" id="userid"/>
		      			<td><input type="submit" value="카테고리 추가" id="btn-add"></td>
		      		</tr> 
		      	</table> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>