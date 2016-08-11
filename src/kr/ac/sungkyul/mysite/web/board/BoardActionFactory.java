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
		} else if("write".equals(actionName)){
			action = new WriteAction();
		} else if("delete".equals(actionName)){
			action = new DeleteAction();
		} else if("modifyform".equals(actionName)){
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)){
			action = new ModifyAction();
		} else {
			action = new BoardAction();
		}
		
		return action;
	}

}
