<%@page import="com.javalec.guestbook.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javalec.guestbook.vo.UsersVO"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 목록</title>
</head>
<body>
	<% String id = (String)session.getAttribute("id"); //id라는 이름의 세션값 가져오기
	System.out.println("getboardList >> sessionid: " +  id);%>
	
	<center>
		<h1>게시판 목록</h1>
		<h3></h3>
		<!-- 검색 시작 -->
		<form action="getboardlist.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width=700px>
				<tr>
					<td align="right"><select name="searchCondition">
							<option value="title">제목
							<option value="content">내용
					</select> <input name="searchKeyword" type="text" /> <input type="submit"
						value="검색" /></td>
				</tr>
			</table>
		</form>



		<!-- 검색 종료 -->
		<table border="1" cellpadding="0" cellspacing="0" width=700px>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>

			<tr>
		<% 
		 ArrayList<BoardVO> list =(ArrayList)(request.getAttribute("list")); %>
		<%for(BoardVO b : list) { %>
				<td><%=b.getSeq()%></td>
				<td align="left"><a href="getboard.do?seq=<%=b.getSeq()%>"><%=b.getTitle()%></a></td>
				<td><%=b.getWriter() %></td>
				<td><%=b.getRegdate() %></td>
				<td><%=b.getCnt() %></td>
			</tr>
			<%} %>
		</table>




		<br>
		<div align="center" width:100%	height:50pxmargin:20pxauto;>
			<a href="insertBoard.jsp">게시글 등록</a>&nbsp;&nbsp;&nbsp;
			<br>
			<a href="logout.do">로그아웃</a>&nbsp;&nbsp;&nbsp;
		</div>
	</center>

</body>
</html>