<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
	<tr>
		<td height="10" width="20">&nbsp;</td>
		<td width="530" valign="top">
			<h1 align="center">글 수정</h1>
		</td>
	</tr>
	<tr><td height="5">&nbsp;</td></tr>
	<tr><td height="10">&nbsp;</td>
		<td>
		
			<!-- 포스트 수정화면 시작 -->
			<form action="/blog/${blog.blogId}/updatePost" method="post">
				<table width="720" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td>제목 :</td>
						<td>
							<input type="hidden" name="postId" value="${post.postId }">
							<input type="text" size="50" name="title" value=" ${post.title }">
							<select name="categoryName">
								<c:forEach var="category" items="${categoryList }">
									<option value="${category.categoryName}"  selected>${category.categoryName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>내용 :</td>
						<td colspan="10"><textarea name="content" rows="10" cols="80"> ${post.content }</textarea></td>
					</tr>
					<tr><td height="5">&nbsp;</td></tr>
					<tr><td colspan="10" align="center">&nbsp;<input type="submit" value="수정하기"></td></tr>
				</table>
			</form> 
			<!-- 포스트 수정화면 종료 -->
			
		</td>
	</tr>
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>

<%@include file="../layout/footer.jsp" %>
