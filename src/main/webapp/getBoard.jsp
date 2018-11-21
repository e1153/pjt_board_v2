
<%@page import="oracle.net.aso.s"%>
<%@page import="com.javalec.guestbook.vo.BoardVO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 상세</title>
</head>
<body>

<%-- 		<% String title= (String)request.getAttribute("title"); --%>
<!-- // 		String writer= (String)request.getAttribute("writer"); -->
<!-- // 		String content= (String)request.getAttribute("content"); -->
<%-- 		String seq = (String)request.getAttribute("seq");%> --%>
<% BoardVO vo = (BoardVO)request.getAttribute("vo"); %>
<% System.out.println(vo.getId()); %>
<% System.out.println(session.getAttribute("id")); %>
				
				<center>
					<h1>게시글 상세</h1>
					<hr>
					<form action="getboard.do" method="post">

						<table border="1" cellpadding="0" cellspacing="0" width=700px>
							<tr>
								<td  width=20%>제목</td>
								<td >&nbsp;&nbsp;<input name="title" type="text" value="<%=vo.getTitle() %>"
									 disabled="disabled"/></td>
							</tr>
							<tr>
								<td >작성자</td>
								<td >&nbsp;&nbsp;<input name="writer" type="text" value="<%=vo.getWriter() %>"
									disabled="disabled" /></td>
							</tr>
							<tr>
								<td >내용</td>
								<td >&nbsp;&nbsp;<textarea name="content" cols="70" rows="10" disabled="disabled"><%=vo.getContent() %></textarea>
								</td>
							</tr>
							<tr>
								<td >등록일</td>
								<td ></td>
							</tr>
							<tr>
								<td >조회수</td>
								<td ></td>
							</tr>

						</table>
					</form>
					<hr>
				</center>
				<div align="center"	width:100%	height:50px  margin:20px auto;>
					<%if(session.getAttribute("id").equals(vo.getId())){ %>
					<a href="modifypage.do?seq=<%=vo.getSeq()%>">변경
					<input type="hidden" name="seq" value="<%=vo.getSeq()%>"></a>&nbsp;&nbsp;&nbsp; 
					
					<a href="delete.do?seq=<%=vo.getSeq()%>" >삭제
					<input type="hidden" name="seq" value="<%=vo.getSeq()%>"></a>&nbsp;&nbsp;&nbsp;
					<%} %>
					<a href="getboardlist.do" >목록</a>
				</div>
</body>
</html>
