<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
 <link href="/resources/css/boardCss.css" type="text/css" rel="stylesheet">
<script>

function check1(){

	var fm = document.frm;
	
	if (fm.subject.value == ""){
		alert("제목을 입력해주세요");
		fm.subject.focus(); 
		return;
	}else if(fm.contents.value == ""){
		alert("내용을 입력해주세요");
		fm.contents.focus(); 
		return;
	}else if(fm.writer.value == ""){
		alert("작성자를 입력해주세요");
		fm.writer.focus(); 
		return;
	}else if(fm.password.value == ""){
		alert("비밀번호를 입력해주세요");
		fm.password.focus(); 
		return;
	}

	let ans = confirm("저장하시겠습니까?");
	
	if(ans == true){
		fm.action="<%=request.getContextPath()%>/board/boardWriteAction.aws";
		fm.method="post";
		fm.enctype="multipart/form-data"
		fm.submit();
	}
	return;
}

function check2(){

	alert("글작성을 취소하시겠습니까?");

	return; 
}
</script>
</head>
<body>
<form name="frm">
<div>
	<table class="writer">
		<tr>
			<td  class="tdDel">제목</td>
			<td>
			<input type="text" name="subject" style = "width:700px; height: 2rem;">
			</td>
		</tr>
		<tr>
			<td class="tdDel">내용</td>
			<td>
			<textarea name="contents" style = "width:700px; height: 15rem;" ></textarea>
			</td>
		</tr>
		<tr>
			<td class="tdDel">작성자</td>
			<td>
			<input type="text" name="writer" style = "width:100px; height: 1rem;" >
			</td>
		</tr>
		<tr>
			<td class="tdDel">비밀번호</td>
			<td>
			<input type="password" name="password" style = "width:100px; height: 1rem;" >
			</td>
		</tr>
		<tr>
			<td class="tdDel">첨부파일</td>
			<td>
			<input type="file" name="filename">
			</td>
		</tr>
	</table>
</div>
<div class="divlineright">
	<button  type = "button" name="btn1" onclick="check1();">저장</button>
	<button  type = "button" name="btn2" onclick="history.back();">취소</button>
</div>
</form>
</body>
</html>