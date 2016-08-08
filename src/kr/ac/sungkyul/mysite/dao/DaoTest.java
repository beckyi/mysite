package kr.ac.sungkyul.mysite.dao;

import java.util.List;

import kr.ac.sungkyul.mysite.vo.GuestbookVo;

public class DaoTest {
	
	public static void main(String[] args) {
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.getList();
		
		for(GuestbookVo vo : list) {
			System.out.println(vo);
		}
	}

}
