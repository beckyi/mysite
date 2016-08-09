package kr.ac.sungkyul.mysite.web.board;

import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println(actionName);
		if("viewform".equals(actionName)){
			action = new ViewformAction();
		} else if("writeform".equals(actionName)){
			action = new WriteformAction();
		} else{
			action = new BoardAction();
		}
		
		return action;
	}

}
