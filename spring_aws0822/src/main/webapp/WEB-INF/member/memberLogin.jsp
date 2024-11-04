<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}


%>

<!DOCTYPE HTML>
<HTML>
 <HEAD>

  <TITLE> MemberLogin </TITLE>
  <style>
  body{
  width:100%;
  height: 100%; 
  font-weight:700; color:#138a47; 
  
  }
  header {
	width:100%;
	height: 15%; 
	clear: both; 
	text-align: center;

}
nav{
	width: 15%; 
	height: 15%; 
	display:inline-block;
	background-color : #dce8fa;
}
section { 
	text-align: center;
	width: 70%; 
	height: 50%; 
	display:inline-block;

}
aside { 
	width: 15%; 
	height: 15%; 
	display:inline-block;
	background-color : #f7dcfa; 
}
footer { 
	width: 100%; 
	height: 15%; 
	clear: both; 
	text-align: center;
	background-color : #ffded4; 
}
form{
display: inline-block;
padding : 10px;
border : 10px solid #138a47;
}

table,tr,td{
	text-align: center;
}
table{



}

td{
	width: 100px; 
	height: 30px; 
}
  </style>
  <script>
  <%
  if(msg != ""){
	  out.println("alert('"+msg+"')");
  }
  %>
  
  // 아이디, 비밀번호 유효성 검사
  function check(){
	  // 이름으로 객체 찾기 
	  let memberid = document.getElementsByName("memberid"); // 이름은 복수 이므로 아이디 배열 변수임
	  let memberpwd = document.getElementsByName("memberpwd");
	  //alert(memberid[0].value);
	  
	  if(memberid[0].value==""){
		  alert("아이디를 입력해주세요");
		  memberid[0].focus();
		  return;
	  }else if(memberpwd[0].value==""){
		  alert("비밀번호를 입력해주세요");
		  memberpwd[0].focus();
		  return;
	  }
	
	  var fm = document.frm;
	  fm.action="<%=request.getContextPath()%>/member/memberLoginAction.aws"; // 가상경로지정 action은 처리하는 의미
	  fm.method="post";
	  fm.submit();
  }
  </script>
 </HEAD>

 <BODY>

 <header><span style="font-weight:1000; color:#138a47; font-size:30px;">로그인</span> </header>
 <nav></nav>
 <section>
  
 <article>

<form name="frm" action=".test0920_result.html" method="post">
	<table>
	<tr>
		<td>아이디</td>
		<td><input type = "text" name = "memberid" maxlength = "50" style = "width:150px;"></td>
		<td>비밀번호</td>
		<td><input type = "password" name = "memberpwd" maxlength = "50" style = "width:150px;"></td>
	</tr>
	<tr>
		<td colspan = "4" >
		<input type = "button" name = "btn" value = "로그인하기" onclick="check();">
		</td>
	</tr>
	</table>
	<a href = "" > 아이디 찾기</a> |
	<a href = "" > 비밀번호 찾기</a> |
	<a href = "" > 회원가입 </a>
</form>
 </article>
 </section>
 <aside>

 </aside>

 <footer>

 </footer>

 </BODY>
</HTML>
