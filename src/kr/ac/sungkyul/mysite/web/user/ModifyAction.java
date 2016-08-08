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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( "/mysite/main", request, response);
			return;
		}
		
		UsersVo authUser = (UsersVo)session.getAttribute( "authUser" );
		
		if( authUser == null ){
			WebUtil.redirect( "/mysite/main", request, response);
			return;
		}		
		
		Long no = authUser.getNo();

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UsersVo vo = new UsersVo();
		vo.setNo(no);
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		
		UsersDao dao = new UsersDao();
		dao.update(vo);
		
		//세션정보수정
		authUser.setName(name);
		WebUtil.redirect("/mysite/users?a=modifyform&res=success", request, response);
	}

}
