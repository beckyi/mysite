package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UsersVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(request.getParameter("r"));
		
		Long no = Long.parseLong(request.getParameter("r"));
		
		System.out.println(no);
		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		
		request.getParameter(title +" "+content);
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);

		BoardDao dao = new BoardDao();
		dao.update(vo);
		
		WebUtil.redirect("/mysite/bs?a=list", request, response);
	}

}
