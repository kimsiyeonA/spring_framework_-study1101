<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"  %>
	<%@ page import="com.myaws.myapp.domain.*"%> 
<%
ArrayList<BoardVo> blist = (ArrayList<BoardVo>)request.getAttribute("blist");
PageMaker pm = (PageMaker)request.getAttribute("pm");
String keyword =  pm.getScri().getKeyword();
String searchType = pm.getScri().getSearchType();

String param="keyword="+keyword+"&searchType="+searchType+"";

int totalCount = pm.getTotalCount(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
 <link href="/resources/css/boardCss.css" type="text/css" rel="stylesheet">

</head>
<body>
<form name="frm" method="get" action="<%=request.getContextPath() %>/board/boardList.aws" >
<div class="divlinerightm">
	<select class="list" name="searchType">
		<option value="subject" >제목</option>
		<option value="writer" >작성자</option>
	</select>
	<input type="text" class="list" name = "keyword" maxlength = "30">
	<button type="submit" class=btnlist>검색</button>
</div>
<div>
	<table>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>날짜</th>
		</tr>
	<%
	int num = totalCount - (pm.getScri().getPage()-1) * pm.getScri().getPerPageNum();
	
	for(BoardVo bv : blist){ 
 		 String lvlStr = "" ;
		for(int i=1; i<=bv.getLevel_(); i++){
			lvlStr = lvlStr+ "&nbsp;&nbsp;&nbsp;";
			if(i==bv.getLevel_()){
				lvlStr=lvlStr+"ㄴ";
			}
		} 
	%>
		<tr>
			<td><%=num %></td>
			<td  class="tdleft">
				<%=lvlStr%>
				<a href="<%=request.getContextPath()%>/board/boardContents.aws?bidx=<%=bv.getBidx()%>"><%=bv.getSubject() %></a>
			</td>
			<td><%=bv.getWriter() %></td>
			<td><%=bv.getViewcnt() %></td>
			<td><%=bv.getRecom()%></td>
			<td><%=bv.getWriterday().substring(0,10) %></td>
		</tr>
	
	<%
	num = num-1;
	}
	%> 
	</table>
</div>

<div class="divlineright">
	<a href="<%=request.getContextPath() %>/board/boardWrite.aws">
	<button type="button">글쓰기</button>
	</a>
</div>

<div class="divlinecenter">
	<% if(pm.isPrev()==true){ %>	
	<a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getStartPage()-1%>&<%=param %>">◀</span></a>
	<%}%>	
	
	<% for(int i = pm.getStartPage(); i<=pm.getEndPage();i++){ %>	
	<a href="<%=request.getContextPath() %>/board/boardList.aws?page=<%=i%>&<%=param %>"><span><%=i %></span></a>
	<%}%>	
	

	<% if(pm.isNext()==true && pm.getEndPage()>0){ %>	
	<a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getEndPage()+1%>&<%=param %>"><span>▶</span></a>
	<%}%>	 
</div>
</form>
</body>
</html>