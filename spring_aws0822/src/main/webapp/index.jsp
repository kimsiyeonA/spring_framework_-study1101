<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 학습하기</title>
</head>
<body>
<%-- <%
if(session.getAttribute("midx")!= null){
	out.println(session.getAttribute("memberName")
			+"<a href='"+request.getContextPath() +"/member/memberLogout.aws'>로그아웃</a>");
}
%> --%>
<%-- <a href="<%=request.getContextPath() %>/member/memberJoin.aws">회원 가입 페이지</a>
<a href="<%=request.getContextPath() %>/member/memberLogin.aws">회원 로그인 페이지</a>
<a href="<%=request.getContextPath() %>/member/memberList.aws">회원 목록 페이지</a>
<br>
<a href="<%=request.getContextPath() %>/board/boardList.aws">게시판 목록가기</a>
 --%>
 
<c:if test="${!empty sessionScope.midx}">  
<!-- ${!empty midx}로도 쓸 수있고 ${midx!=null}로도 쓸 수 있음 sessionScope.midx : 세션 범위에 있는 midx이다 sessionScope 생략해도 되지만 붙여놓으면 이해하기 쉽다-->
	${memberName}&nbsp;<a href="${pageContext.request.contextPath}/member/memberLogout.aws">로그아웃</a>
</c:if>
 <br>
<a href="${pageContext.request.contextPath}/member/memberJoin.aws">회원 가입 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/member/memberLogin.aws">회원 로그인 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/member/memberList.aws">회원 목록 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/board/boardList.aws">게시판 목록가기</a>
</body>
</html>