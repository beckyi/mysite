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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		System.out.println(email +":"+ password);
		UsersDao dao = new UsersDao();
		UsersVo vo = dao.get(email, password);
		
		if( vo == null ) {
			/*로그인 실패*/
			WebUtil.redirect("/mysite/users?a=loginform&r=fail", request, response);
			return;
		}
		
		/*로그인 처리*/
		HttpSession session = request.getSession(true);	//true - 없으면 만들어서 줘라!
		session.setAttribute("authUser", vo);
		
		//메인으로 리다이렉트
		WebUtil.redirect("/mysite/main", request, response);
	}

}
