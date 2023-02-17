package com.itwillbs.board.action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.boardDAO;
import com.itwillbs.board.db.boardDTO;

public class BoardContent implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		boardDAO dao = new boardDAO();
		boardDTO dto = dao.getBoard(num);
		
		if (id!=null) { 
			System.out.println("조회수증가");
			dao.updateReadCount(dto); 
			dto = dao.getBoard(num);}
	
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}