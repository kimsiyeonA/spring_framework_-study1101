<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.myaws.myapp.domain.*"%> 
 <% 
 BoardVo bv = (BoardVo)request.getAttribute("bv");
 int midx= 0;
 if(session.getAttribute("midx")!=null){
	 midx= Integer.parseInt(session.getAttribute("midx").toString());
	} 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
 <link href="/resources/css/boardCss.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script> 
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
    
    $("#dUrl").click(function() {
		$("#dUrl").attr("href", download()); // dUrl이라는 id를 가진 태그의 href 속성에 download() 함수로 리턴받은 값을 넣는다.
          
    });
    
    $("#btn").click(function() {
        //alert("추천버튼 클릭");

        $.ajax({
            type: "get",
            url: "<%= request.getContextPath() %>/board/boardRecom.aws?bidx=<%= bv.getBidx() %>", 
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

//파일 다운로드시 썸네일을 다운로드 되므로 파일 이름에서 "s-"를 제거해야함
 function checkImageType(fileName) { // 파일명 확장자 일치하는 파일 이름을 리턴함
     var pattern = /jpg$|gif$|png$|jpeg$/i; // 서브스캔용 정규표현식
     return fileName.match(pattern);
 }

 function getOriginalFileName(fileName) { // 원본 파일 이름 추출
     var idx = fileName.lastIndexOf("s_") + 1; // DB에 있는 파일 이름 형식 : /2024/11/08/s-64ca590f-3e9e-4194-80f6-c6645e1f611f_rose.jpg
     return fileName.substr(idx);
 }

 function getImageLink(fileName) { // 링크에서 이미지 추출
     var front = fileName.substr(0,12); // 0 ~ 12 문자까지만 추출. /2024/11/08/
     var end = fileName.substr(14); // 14부터 끝까지 추출. 64ca590f-3e9e-4194-80f6-c6645e1f611f_rose.jpg
     return front + end; // 13번 문자 제외하여 연결
 }

 function download() { // 주소 사이에 & 다운 로드 주소를 리턴 // 썸네일 이미지 이름으로 원본 이미지 이름 변환
     var downloadImageName = getImageLink("<%=bv.getFilename()%>"); 
     var downLink = "<%=request.getContextPath()%>/board/displayFile.aws?fileName=" + downloadImageName + "&down=1";
     return downLink;
 }
  
</script>
</head>
<body>
<form name="frm">
<div class="divlineblack">
 	<div>
		<h2> <%=bv.getSubject() %>(조회수:<%=bv.getViewcnt() %> )</h2>
		<p class="inline"> <%=bv.getWriter() %> ( <%=bv.getWriterday().substring(0,10) %>)  <span><button type="button" id="btn">추천(<%=bv.getRecom() %>)</button></span></p>
		
	</div>
	<div class="divlinebt">
		<p>
		 <%=bv.getContents() %>
		</p>
	</div>
	
		<img src="<%=request.getContextPath()%>/board/displayFile.aws?fileName=<%=bv.getFilename()%>">
		<%=bv.getFilename()%>
		<a href="<%=request.getContextPath() %>/board/displayFile.aws?fileName=<%=bv.getFilename()%>&down=1" id ="dUrl">
		<button type = "button"  class="btncontentdown"  name="btn2" >
		첨부파일 다운로드	
		</button>
		</a>
</div>
<br>
<div class="btnLeft">
 	<a href="<%=request.getContextPath() %>/board/boardUpdate.aws?bidx=<%-- <%=bv.getBidx() %> --%>">
		<button type = "button" onclick="alertUpdate();">
		수정
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardDelete.aws?bidx=<%=bv.getBidx() %>">
		<button type = "button"  onclick="alertDelete();">
		삭제
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardReply.aws?bidx=<%-- <%=bv.getBidx() %> --%>">
		<button type = "button" onclick="alertReply();">
		답변
		</button>
	</a>
	
	<a href="<%=request.getContextPath() %>/board/boardList.aws">
		<button type = "button" onclick="alertList();">
		목록
		</button>
	</a> 

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