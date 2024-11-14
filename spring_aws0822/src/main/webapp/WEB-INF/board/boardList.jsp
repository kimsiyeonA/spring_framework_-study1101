<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.myaws.myapp.domain.*"%>
<%
/* ArrayList<BoardVo> blist = (ArrayList<BoardVo>)request.getAttribute("blist");
PageMaker pm = (PageMaker)request.getAttribute("pm");
String keyword =  pm.getScri().getKeyword();
String searchType = pm.getScri().getSearchType();
String param="keyword="+keyword+"&searchType="+searchType+"";
int totalCount = pm.getTotalCount();  */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link
	href="${pageContext.request.contextPath}/resources/css/boardCssReal.css"
	rel="stylesheet">


</head>
<body>
	<%-- <form name="frm" method="get" action="<%=request.getContextPath() %>/board/boardList.aws" > --%>
	<form name="frm" method="get"
		action="${pageContext.request.contextPath}/board/boardList.aws">
		<div class="divlinerightm">
			<select class="list" name="searchType">
				<option value="subject">제목</option>
				<option value="writer">작성자</option>
			</select> <input type="text" class="list" name="keyword" maxlength="30">
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
				<%-- 	<%
				int num = totalCount - (pm.getScri().getPage() - 1) * pm.getScri().getPerPageNum();

				//for (BoardVo bv : blist) {
				String lvlStr = "";
				for (int i = 1; i <= bv.getLevel_(); i++) {
					lvlStr = lvlStr + "&nbsp;&nbsp;&nbsp;";
					if (i == bv.getLevel_()) {
						lvlStr = lvlStr + "ㄴ";
					}
				}
				
				%> --%>
				<c:forEach items="${blist}" var="bv" varStatus="status">
					<!--  전체 반복문 / (ArrayList<BoardVo>)request.getAttribute("blist"); 변환시키지 않고 사용함 / status는 0 번부서 시작함-->
					<!--  bv 박스에 담아서 ${bv.writer} 처럼 bv에서 꺼냄-->
					<!--  status.index 는 0부터 시작함-->

					<%-- <tr>
						<td><%=num%></td>
						<td class="tdleft"><%=lvlStr%> <a
							href="<%=request.getContextPath()%>/board/boardContents.aws?bidx=<%=bv.getBidx()%>"><%=bv.getSubject()%></a>
						</td>
						<td><%=bv.getWriter()%></td>
						<td><%=bv.getViewcnt()%></td>
						<td><%=bv.getRecom()%></td>
						<td><%=bv.getWriterday().substring(0, 10)%></td>
					</tr> --%>

					<tr>
						<td>${pm.totalCount-((status.index)+(pm.scri.page-1)*pm.scri.perPageNum)}</td>
						<!-- 번호가 토탈 게시판 숫자부터 시작함  그래서 토탈~ / 토탈-15 ~ 으로 페이지 마다 바뀌어짐 -->
						<!--  (PageMaker)request.getAttribute("pm")변환시키지 않고 사용함 / status는 0 번부서 시작함-->
						<td class="tdleft">
							<%
							//=lvlStr
							%> <c:forEach var="i" begin="1" end="${bv.level_}"
								step="1">
								<!-- ${bv.level_} 1이라도 들어가면 한바퀴를 도니 레벨에 따라 for문이 실행되는 것을 알 수 있음 -->
						
							&nbsp;&nbsp;
							<c:if test="${1==bv.level_}">
								ㄴ
							</c:if>
							</c:forEach> <a
							href="${pageContext.request.contextPath}/board/boardContents.aws?bidx=${bv.bidx}">${bv.subject}</a>
						</td>
						<td>${bv.writer}</td>
						<td>${bv.viewcnt}</td>
						<td>${bv.recom}</td>
						<td>${bv.writerday}</td>
					</tr>

					<%
					//num = num - 1;
					//}
					%>
				</c:forEach>
			</table>
		</div>

		<div class="divlineright">
			<a href="${pageContext.request.contextPath}/board/boardWrite.aws">
				<button type="button">글쓰기</button>
			</a>
		</div>

	 <c:set var="queryParam" value="keyword=${pm.scri.keyword}&searchType=${pm.scri.searchType} "></c:set>
	 <div class="divlinecenter">
	 
	 	<c:if test="${pm.prev==true}">
	 		<a href="${pageContext.request.contextPath}/board/boardList.aws?page=${pm.startPage-1}&${queryParam}"><span>◀</span></a>
	 	</c:if>
	 	
	 	<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}" step="1">
	 		<a href="${pageContext.request.contextPath}/board/boardList.aws?page=${i}&${queryParam}"><span>${i}</span></a>
	 	</c:forEach>
	 	
	 	<c:if test="${pm.next==true&&pm.endPage>0}">
	 		<a href="<%=request.getContextPath()%>/board/boardList.aws?page=${pm.endPage+1}&${queryParam}"><span>▶</span></a>
	 	</c:if>
	 	
<%-- 			<%
			if (pm.isPrev() == true) {
			%>
			<a
				href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getStartPage() - 1%>&<%=param%>"><span>◀</span></a>
			<%
			}
			%>

			<%
			for (int i = pm.getStartPage(); i <= pm.getEndPage(); i++) {
			%>
			<a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=i%>&<%=param%>"><span><%=i%></span></a>
			<%
			}
			%>


			<%
			if (pm.isNext() == true && pm.getEndPage() > 0) {
			%>
			<a
				href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getEndPage() + 1%>&<%=param%>"><span>▶</span></a>
			<%
			}
			%> --%>
		</div>
	</form>
</body>
</html>