	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.myaws.myapp.domain.MemberVo"%>
	
	<%
	//ArrayList객체를 화면까지 가져왔다.
	ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist"); // 형식이 안맞아서 강제 형변환으로 진행
	
	System.out.println("첫객체 아이디 출력 : "+alist.get(0).getMemberid());
	
	%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>회원 목록 보기</title>
	<style>
	table{
	 border : 1px solid #1d435c;
	 border-collapse : collapse; 
	 /*table과 td, th 선 합치기*/
	}
	td, th {
	 border : 1px dotted #1f93e0;
	 padding : 10px;
	 
	}
	
	th {
	width : 100px;
	height : 40px;
	
	}
	td {
	width : 100px;
	height : 20px;
	text-align: right;
	}
	thead{
	background: #70a7cc;
	color : white;
	}
	tbody tr:nth-child(even) { /* 짝수 <tr>에 적용*/
		background : aliceblue;
	}
	tbody tr:hover {
		background : pink;
	}
	
	tfoot{
	border-bottom : 1px solid #1d435c;
	}
	</style>
	</head>
	<body>
	<h3>회원목록</h3>
	<hr>
	<table>
	<thead>
	<tr>
		<th>회원번호</th>
		<th>회원아이디</th>
		<th>회원이름</th>
		<th>회원성별</th>
		<th>가입일</th>
	</tr>
	</thead>
	<tbody>
		<% 
		int num = 0;
		for(MemberVo mv : alist){%>
		<tr>
			<td><%  out.println(mv.getMidx()); %></td>
			<td><%=mv.getMemberid() %></td>
			<td><%=mv.getMembername() %></td>
			<td><%=mv.getMembergender() %></td>
			<td><%=mv.getWriteday().substring(0,10) %></td> <!--날짜시간 문자 자르기-->  
		</tr>
		<% 
		num = num+1; 
		}
		%>
	</tbody>
	<tfoot>
	<tr>
		<td colspan = "5">총 <%=num %>명입니다.</td>
	</tr>
	</tfoot>
	</table>
	
	</body>
	</html>