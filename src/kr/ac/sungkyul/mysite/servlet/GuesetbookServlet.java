package kr.ac.sungkyul.mysite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.GuestbookDao;
import kr.ac.sungkyul.mysite.vo.GuestbookVo;
import kr.ac.sungkyul.web.WebUtil;

/**
 * Servlet implementation class GuesetbookServlet
 */
@WebServlet("/gs")
public class GuesetbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");

		if ("delete".equals(actionName)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookDao dao = new GuestbookDao();
			String pass = dao.pass(no);

			if (pass.equals(password)) {
				dao.delete(no);
			}

			response.sendRedirect("/mysite/gs");
			//return; //코드, 통신을 끊기 위해 (아래에서는 if 사용)
		}

		else if ("insert".equals(actionName)) {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);

			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);

			response.sendRedirect("/mysite/gs");

		} else if ("deleteform".equals(actionName)) {
			String no = request.getParameter("no");

			request.setAttribute("no", no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp"); // request
			// 연장
			rd.forward(request, response);
			
			//WebUtil.forward("/WEB-INF/views/users/deleteform.jsp", request, response);
		}else {
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();

			// request 범위(scope)에 List 객체를 저장
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp"); // request
																								// 연장
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
