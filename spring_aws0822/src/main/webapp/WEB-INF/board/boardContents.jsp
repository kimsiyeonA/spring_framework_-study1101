<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@page import="mvc.vo.BoardVo" %>
 <% 
 BoardVo bv = (BoardVo)request.getAttribute("bv");
 %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
 <link href="/resources/css/boardCss.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- <script> 
function alertUpdate(){

	alert("글수정페이지로 이동합니다.");
	return; 
	
}
function alertDelete(){

	alert("글삭제페이지로 이동합니다.");
	return; 
	
}
function alertReply(){

	alert("글답변페이지로 이동합니다.");
	return; 
	
}
function alertList(){

	alert("글목록페이지로 이동합니다.");
	return; 
	
}
$(document).ready(function() {
    //lert("추천버튼 클릭1"); 
    $("#btn").click(function() {
        //alert("추천버튼 클릭");

        $.ajax({
            type: "get",
            url: "<%=request.getContextPath()%>/board/boardRecom.aws?bidx=<%=bv.getBidx()%>", 
            dataType: "json", 
            success: function(result) {
                var str = "추천(" + result.recom + ")";
                $("#btn").text(str);
            },
            error: function(result) {
                alert("전송 실패 테스트");
            }
        });
    });
});
</script>-->
</head>
<body>
<form name="frm">
<div class="divlineblack">
<%-- 	<div>
		<h2><%=bv.getSubject() %>(조회수:<%=bv.getViewcnt() %>)</h2>
		<p class="inline"><%=bv.getWriter() %>(<%=bv.getWriterday().substring(0,10) %>)  <span><button type="button" id="btn">추천(<%=bv.getRecom() %>)</button></span></p>
		
	</div>
	<div class="divlinebt">
		<p>
		<%=bv.getContents() %>
		</p>
	</div>
	<%if(bv.getFilename() == null || bv.getFilename().equals("")){}else{ %>
	<p>
	<img src="<%=request.getContextPath()%>/images/<%=bv.getFilename()%>">
	<%=bv.getFilename()%>
	
	<a href="<%=request.getContextPath() %>/board/BoardDowmload.aws?filename=<%=bv.getFilename() %>">
		<button type = "button" class="btncontentdown"  name="btn2" >
		첨부파일 다운로드	
		</button>
	</a>
	</p>
	<%} %> --%>
</div>
<br>
<div class="btnLeft">
<%-- 	<a href="<%=request.getContextPath() %>/board/boardUpdate.aws?bidx=<%=bv.getBidx() %>">
		<button type = "button" onclick="alertUpdate();">
		수정
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardDelete.aws?bidx=<%=bv.getBidx() %>">
		<button type = "button"  onclick="alertDelete();">
		삭제
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardReply.aws?bidx=<%=bv.getBidx() %>">
		<button type = "button" onclick="alertReply();">
		답변
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardList.aws">
		<button type = "button" onclick="alertList();">
		목록
		</button>
	</a> --%>

</div>
<br>
<div>
	<div class="contentadmin"> admin </div>
	<div class="content"> 
		<input type="text" >
		<button class="btncontent">댓글달기</button>
	</div>
</div>
<br>
<div>
	<table>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>내용</th>
			<th>날짜</th>
			<th>DEL</th>
		</tr>
		<tr>
			<td>1</td>
			<td>홍길동</td>
			<td>잘봤습니다.</td>
			<td>2024-10-28</td>
			<td>삭제</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>