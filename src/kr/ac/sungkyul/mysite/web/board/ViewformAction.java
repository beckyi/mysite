package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.dao.UsersDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UsersVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ViewformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.parseLong(request.getParameter("r"));
		
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.getView(no);
		
		// request 범위(scope)에 List 객체를 저장
		request.setAttribute("Boardvo", vo);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
