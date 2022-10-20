<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
</head>
<body>
<center>

<!-- 검색 화면 시작 -->
<form action="searchBlog" method="post">
	<table width="720" height=200 border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" colspan="10" align="center">
				<img src="images/logo.jpg" border="0">
			</td>
		</tr>				
		<tr>
			<td align="center">
			
				<input type="radio" name="searchCondition" value="TITLE" checked="checked">블로그 제목&nbsp;&nbsp;
				<input type="radio"	name="searchCondition" value="TAG" <c:if test="${condition == 'TAG' }">checked="checked"</c:if> >태그
			</td>
		</tr>				
		<tr>
			<td width="70%" colspan="2" align="center">
				
				<c:if test="${user == null }">
					<a href="login"><b>로그인</b></a>&nbsp;&nbsp;
				</c:if>
				
				<c:if test="${user != null}">
					<a href="logout"><b>로그아웃</b></a>&nbsp;&nbsp;
				</c:if>
     			
     			<c:if test ="${NoBlogUser != null}">
     				<a href="insertBlog"><b>블로그등록</b></a>&nbsp;&nbsp;
     			</c:if>
     			
     			<c:if test="${blogUser != null }">
      				<a href="blog/${blogUser.userId }"><b>블로그바로가기</b></a>&nbsp;&nbsp;
      			</c:if>
      			
				<input type="text" name="searchKeyword"	size="50">
				<input type="submit" value="검색">
			</td>
		</tr>

	</table>
</form>
<!-- 검색 화면 종료 -->

<!-- 블로그 목록 시작 -->
<br><br>
<table width="550" border="0" cellpadding="1" cellspacing="1">
	<tr bgcolor="#9DCFFF">
		<th height="30"><font color="white">블로그 제목</font></th>
		<th width="100"><font color="white">상태</font></th>
		<th width="100"><font color="white">삭제</font></th>
	</tr>
	
	<c:forEach var="findBlog" items="${findBlogList}">
		<tr>
			<td align="left"><a href="/blog/${findBlog.blogId}">${findBlog.title}</a></td>
			<td align="center">${findBlog.status}</td>
			<td align="center"></td>
		</tr>
	</c:forEach>
	
	
	<c:forEach var="blog" items="${blogList}">
		<tr>
			<td align="left"><a href="/blog/${blog.blogId }">${blog.title }</a></td>
			<td align="center">${blog.status }</td>
			<td align="center">
			<c:if test = "${blogUser.role == 'ADMIN' || NoBlogUser.role == 'ADMIN' }">
				<c:if test="${blog.status =='삭제 요청' }">
				<a href="deleteBlog/${blog.blogId}"><img height="9" src="images/delete.jpg" border="0"></a>
				</c:if>
			</c:if>	
			</td>		
		</tr>
	</c:forEach>
	
	
</table>
<!-- 블로그 목록 종료 -->

</center>
</body>
</html>