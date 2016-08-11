package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session == null){
			WebUtil.redirect("/mysite/main", request, response);
			return;
		}
		
		UsersVo authUser = (UsersVo)session.getAttribute("authUser");

		if(authUser == null){
			WebUtil.redirect("/mysite/main", request, response);
			return;
		}

		Long no = authUser.getNo();
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		Integer viewCount = 0;
		Integer groupNo = 1;
		Integer orderNo = 1;
		Integer dept = 0;

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setViewCount(viewCount);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDept(dept);
		vo.setUserNo(no);

		BoardDao dao = new BoardDao();
		dao.insert(vo);
		
		WebUtil.redirect("/mysite/bs?a=list", request, response);
	}

}
