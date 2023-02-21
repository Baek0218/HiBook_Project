<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	// request 가져온 데이터 담기
	String boardTypeCd = (String) request.getAttribute("boardTypeCd");
	String boardTypeCdNm = (String) request.getAttribute("boardTypeCdNm");
	// 검색
	String keyWord = request.getParameter("keyWord")==null ? "" : request.getParameter("keyWord");
	ArrayList<BoardDTO> BoardList = (ArrayList<BoardDTO>) request.getAttribute("boardList");
	ArrayList<BoardDTO> NoticeList = (ArrayList<BoardDTO>) request.getAttribute("noticeList");
	
	int currentPage = (Integer) request.getAttribute("currentPage");
	int startPage = (Integer) request.getAttribute("startPage");
	int pageBlock = (Integer) request.getAttribute("pageBlock");
	int endPage = (Integer) request.getAttribute("endPage");
	int pageCount = (Integer) request.getAttribute("pageCount");
	int count = (Integer) request.getAttribute("count");
	int pageSize = (Integer) request.getAttribute("pageSize");
	
	String id = (String) session.getAttribute("id");
	String adminYn = (String)session.getAttribute("adminYn"); 
	%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/board.css" rel="stylesheet" type="text/css">
<div class="boardContainer">
<!-- 내용 시작 -->
<div>
<p id="boardTag"><%=boardTypeCdNm %> 💬</p>
<h4 class="memId"><%=id %> 님이 로그인 하셨습니다.</h4>
<form acion="BoardList.bo" method="get">
	<div class="board_search">
		<input type="text" id="keword" name="keword" value="<%=keyWord %>"  placeholder="검색어를 입력하세요">
		<input type="hidden" name="boardType" value="<%=boardTypeCd %>">
		<input type="submit" id="scrhBtn" value="검색">
	</div>
</form>	
	<div class="tableBar">
	<table class="board_list">
		<colgroup>
			<col width="60px;">
			<col width="*">
			<col width="80px;">
			<col width="200px;">
			<col width="60px;">
		</colgroup>
		
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
		</thead>
		<tbody>
			<%
			// 공지사항 for
			for (int i = 0; i < NoticeList.size(); i++) {
				// 배열 한칸에 내용 가져오기 
				BoardDTO dto = NoticeList.get(i);
			%>
	
			<tr>
				<td>공지</td>
				<td class="tl">
					<a href="BoardContent.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>">
						<%=dto.getTitle()%>
					</a>
				</td>
				<td><%=dto.getInsertId()%></td>
				<td><%=dto.getInsertDate()%></td>
				<td><%=dto.getViewCnt()%></td>
			</tr>
	
			<%
			} // 공지사항 for 끝
			%>
			
			<%
			// 일반글 for
			for (int i = 0; i < BoardList.size(); i++) {
				// 배열 한칸에 내용 가져오기 
				BoardDTO dto = BoardList.get(i);
				int num = (count - (currentPage -1) * pageSize) - i; 
			%>
	
			<tr>
				<td><%=num%></td>
				<td class="tl">
					<a href="BoardContent.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>">
						<%if(dto.getParentId() != 0){%> &nbsp;&nbsp;ㄴ<%}%>
						<%=dto.getTitle()%>
						
					</a>
				</td>
				<td><%=dto.getInsertId()%></td>
				<td><%=dto.getInsertDate()%></td>
				<td><%=dto.getViewCnt()%></td>
			</tr>
	
			<%
			} // 일반글 for
			%>
		</tbody>
	</table>
	</div>
	
<%-- 	<input type="button" class="button buttonBlueGray" value="글쓰기" onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd %>'"> --%>


		<%
		if ("notice".equals(boardTypeCd)) {
			
			if ("Y".equals(adminYn)) {%>
			
				<input type="button" class="button buttonBlueGray" value="글쓰기"
					   onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd%>'">
		
		<% }} else { %>
				<input type="button" class="button buttonBlueGray" value="글쓰기"
					   onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd%>'">

		<%
		}
		%>

		<div class="ssgap page">	
	
	<%
			// 10페이지 이전
			if (startPage > pageBlock) {
			%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keword=<%=keyWord%>&pageNum=<%=startPage - pageBlock%>">&lt;&lt;</a>
	<%
	}
	
	for (int i = startPage; i <= endPage; i++) {
	%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keword=<%=keyWord%>&pageNum=<%=i%>"><%=i%></a>
	<%
	}
	// 10페이지 다음
	if (endPage < pageCount) {
		%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keword=<%=keyWord%>&pageNum=<%=startPage + pageBlock%>">&gt;&gt;</a>
	<%
	}%>
	
	
	</div>
</div>
<!-- 내용 끝 -->	
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->