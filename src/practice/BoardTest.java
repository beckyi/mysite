package practice;

import kr.ac.sungkyul.mysite.dao.BoardDao;

public class BoardTest {
	public static void main(String[] args) {
		BoardDao bd = new BoardDao(); 
		bd.getList(3);
	}
}
