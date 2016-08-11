package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int firstPage=1;
		int lastPage;
		
		BoardDao dao = new BoardDao();
		
		int max = dao.countAll();
		
		if((max%5)>0){
			lastPage = (max / 5) + 1;
		} else{
			lastPage = max / 5;
		}
		
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("lastPage", lastPage);
		
		int curpage = Integer.parseInt(request.getParameter("page"));
		
		List<BoardVo> list = dao.getList(curpage);
		
		// request 범위(scope)에 List 객체를 저장
		request.setAttribute("blist", list);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}
