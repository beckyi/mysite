package kr.ac.sungkyul.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.UsersDao;
import kr.ac.sungkyul.mysite.vo.UsersVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인증여부 확인
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		//인증이 되었을 경우 사용자 번호를 통해 UsersVo 받아온다.
		UsersDao dao = new UsersDao();
		UsersVo vo = dao.get(no);
		
		request.setAttribute("userVo", vo);
		
		WebUtil.forward("/WEB-INF/views/users/modifyform.jsp", request, response);
	}

}
