
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.myaws.myapp.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
/*  BoardVo bv = (BoardVo)request.getAttribute("bv");
 
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}
	
	
	if(msg != ""){
		  out.println("<script>alert('"+msg+"')</script>");
	} */
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link
	href="${pageContext.request.contextPath}/resources/css/boardCssReal.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	}else if(fm.password.value  == ""){
		alert("비밀번호를 입력해주세요");
		fm.password.focus(); 
		return;
	}
	
	let ans = confirm("저장하시겠습니까?")
	
	if(ans==true){
		fm.action="${pageContext.request.contextPath}/board/boardUpdateAction.aws";
		fm.method="post"
		fm.enctype="multipart/form-data"
		fm.submit();
	}

	return;
}

function updateCancel(){

	alert("글수정을 취소하시겠습니까?");

	return; 
}
//파일 다운로드시 썸네일을 다운로드 되므로 파일 이름에서 "s-"를 제거해야함
function checkImageType(fileName) { // 파일명 확장자 일치하는 파일 이름을 리턴함
    var pattern = /jpg$|gif$|png$|jpeg$/i; // 서브스캔용 정규표현식
    return fileName.match(pattern);
}

function getOriginalFileName(fileName) { // 원본 파일 이름 추출
    var idx = fileName.lastIndexOf("_") + 1; // DB에 있는 파일 이름 형식 : /2024/11/08/s-64ca590f-3e9e-4194-80f6-c6645e1f611f_rose.jpg
    console.log(fileName.substring(idx));
    return fileName.substring(idx);
}

function getImageLink(fileName) { // 링크에서 이미지 추출
    var front = fileName.substr(0,12); // 0 ~ 12 문자까지만 추출. /2024/11/08/
    var end = fileName.substr(14); // 14부터 끝까지 추출. 64ca590f-3e9e-4194-80f6-c6645e1f611f_rose.jpg
    return front + end; // 13번 문자 제외하여 연결
}
//주소 사이에 & 다운 로드 주소를 리턴 // 썸네일 이미지 이름으로 원본 이미지 이름 변환
function download() {
	    var downloadImage = getImageLink("${bv.filename}");
	    var downLink = "<%=request.getContextPath()%>/board/displayFile.aws?fileName=" + downloadImage + "&down=1";
	    return downLink;
	}
$(document).ready(function() {

	$("#dUrl").html(getOriginalFileName("${bv.filename}"));	

	    
});
<%-- function download() {
    var downloadImage = getImageLink("<%=bv.getFilename()%>");
    var downLink = "<%=request.getContextPath()%>/board/displayFile.aws?fileName=" + downloadImage + "&down=1";
    return downLink;
}
$(document).ready(function() {

$("#dUrl").html(getOriginalFileName("<%=bv.getFilename()%>"));	

    
}); --%>
</script>
</head>
<body>
	<form name="frm">
		<div>
			<input type="hidden" name="bidx" value="${bv.bidx}"> <input
				type="hidden" name="midx" value="${bv.midx}"> <input
				type="hidden" name="filename" value="${bv.filename}">
			<table class="writer">
				<tr>
					<td class="tdDel">제목</td>
					<td><input type="text" name="subject"
						style="width: 700px; height: 2rem;" value="${bv.subject}">
					</td>
				</tr>
				<tr>
					<td class="tdDel">내용</td>
					<td><textarea name="contents"
							style="width: 700px; height: 15rem;">${bv.contents}</textarea></td>
				</tr>
				<tr>
					<td class="tdDel">작성자</td>
					<td><input type="text" name="writer"
						style="width: 100px; height: 1rem;" value="${bv.writer}"></td>
				</tr>
				<tr>
					<td class="tdDel">비밀번호</td>
					<td><input type="password" name="password"
						style="width: 100px; height: 1rem;"></td>
				</tr>
				<tr>
					<td class="tdDel">첨부파일</td>
					<td>
						<table>

							<tr>
								<td colspan="2"><input type="file" name="attachfile">
								</td>
							</tr>

							<%-- <%if(bv.getFilename() == null || bv.getFilename().equals("")){}else{ %> --%>
							<c:choose>
							<c:when test="${empty bv.filename}">
							</c:when>
							<c:otherwise>
								<tr>
									<td class="tdDelwidth">이전 저장된 파일</td>
									<td><span id="dUrl">${bv.filename}</span> <img
										src="${pageContext.request.contextPath}/board/displayFile.aws?fileName=${bv.filename}">
									</td>
								</tr>
							</c:otherwise>
							</c:choose>
<%-- 							<<%if(bv.getFilename() == null || bv.getFilename().equals("")){}else{ %>--%>
<%-- 							<tr>
								<td class="tdDelwidth">이전 저장된 파일</td>
								<td><span id="dUrl"><%=bv.getFilename()%></span> <img
									src="<%=request.getContextPath()%>/board/displayFile.aws?fileName=<%=bv.getFilename()%>">
								</td>
							</tr> --%>
	<%-- 						<%} %> --%>
							
						</table>

					</td>
				</tr>
			</table>
		</div>
		<div class="divlineright">
			<button type="button" onclick="check1();">저장</button>
			<button type="button" onclick="updateCancel(); history.back();">취소</button>
		</div>
	</form>
</body>
</html>