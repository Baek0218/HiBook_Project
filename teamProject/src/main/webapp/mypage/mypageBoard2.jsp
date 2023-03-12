<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypage.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">

<div class="boardContainer">

	<h1>내가 쓴 글(커뮤니티)</h1>

	<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

	ArrayList<BoardDTO> boardList = (ArrayList<BoardDTO>) request.getAttribute("boardList");

	int currentPage = (Integer) request.getAttribute("currentPage");
	int startPage = (Integer) request.getAttribute("startPage");
	int pageBlock = (Integer) request.getAttribute("pageBlock");
	int endPage = (Integer) request.getAttribute("endPage");
	int pageCount = (Integer) request.getAttribute("pageCount");
	%>

	<!-- 체크박스로 선택해 글 여러개 삭제가능  -->
	<form action="MypageMultidelBoardPro.mypage" method="post">
		<div class=category>
			<a href="MypageMarketList.mypage" class=market>중고거래</a><br> <a
				href="MypageBoardList.mypage" class=board2>커뮤니티</a><br> <a
				href="MypageReportList.mypage" class=report>1:1문의</a>
		</div>
		<input type="submit" value="삭제" class=button>

		<!-- 체크박스 모두 선택하는 함수  -->
		<script type="text/javascript">
			function allChk(obj) {
				var chkObj = document.getElementsByName("chk");
				var rowCnt = chkObj.length - 1;
				var check = obj.checked;
				if (check) {
					for (var i = 0; i <= rowCnt; i++) {
						if (chkObj[i].type == "checkbox")
							chkObj[i].checked = true;
					}
				} else {
					for (var i = 0; i <= rowCnt; i++) {
						if (chkObj[i].type == "checkbox") {
							chkObj[i].checked = false;
						}
					}
				}
			}
		</script>

		<div class="tableBar">
			<table>
				<colgroup>
					<col width="50px;">
					<col width="80px;">
					<col width="100px;">
					<col width="*">
					<col width="80px;">
					<col width="110px;">
				</colgroup>
				<tr>
					<th><input id="allCheck" type="checkbox"
						onclick="allChk(this);" /></th>
					<th>글번호</th>
					<th>게시판유형</th>
					<th>글제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>

				<%
				for (int i = 0; i < boardList.size(); i++) {
					// 배열 한칸에 내용 가져오기 => BoardDTO 저장 => 출력 
					BoardDTO dto = boardList.get(i);
				%>
				<tr>
					<td><input type="checkbox" name="chk"
						value="<%=dto.getBoard_id()%>"></td>
					<td><%=dto.getBoard_id()%></td>
					<td><%=dto.getBoard_type()%></td>
					<td><a
						href="BoardContent.bo?boardType=<%=dto.getBoard_type()%>&boardId=<%=dto.getBoard_id()%>"><%=dto.getTitle()%></a></td>
					<td><%=dto.getView_cnt()%></td>
					<td><%=dateFormat.format(dto.getInsert_date())%></td>
				<tr>
					<%
					}
					%>
				
			</table>
		</div>
	</form>

	<div class=pageNum>
		<%
		//1페이지 이전
		if (currentPage > 1) {
		%>
		<a href="MypageBoardList.mypage?pageNum=<%=currentPage - 1%>">&lt;</a>
		<%
		}

		//페이지 번호 누르면 해당 페이지로 이동 
		for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="MypageBoardList.mypage?pageNum=<%=i%>"><%=i%></a>
		<%
		}

		//1페이지 다음
		if (currentPage < pageCount) {
		%>
		<a href="MypageBoardList.mypage?pageNum=<%=currentPage + 1%>">&gt;</a>
		<%
		}
		%>
	</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
