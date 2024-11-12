<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String bidx = request.getAttribute("bidx").toString();


	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}
	
	
	if(msg != ""){
		  out.println("<script>alert('"+msg+"')</script>");
	}

 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제</title>
<link href="<%=request.getContextPath() %>/resources/css/boardCssReal.css" rel="stylesheet">
<script>

function check1(){

	var fm = document.frm;
	
	if (fm.password.value == ""){
		alert("비밀번호를 입력해주세요");
		fm.password.focus(); 
		return;
	}
	
	let ans = confirm("삭제하시겠습니까");
	
	if(ans == true){
		fm.action="<%=request.getContextPath()%>/board/boardDelectAction.aws";
		fm.method="post";
		fm.submit();
	}
	return;
}

function deleteCancel(){

	alert("삭제를 취소하시겠습니까?");

	return; 
}
</script>

</head>
<body>
<form name="frm">
<input type="hidden" name="bidx" value="${bidx}" >
<div>
	<table >
		<tr >
			<td class="tdDel">비밀번호</td>
			<td>
			<input type="password" name="password">
			</td>
		</tr>
	</table>
</div>

<div class="btnLeft">
	<button type = "button"  onclick="check1();">저장</button>
	<button type = "button"  onclick="deleteCancel(); history.back();">취소</button>
</div>
</form>
</body>
</html>