package kr.ac.sungkyul.mysite.web.user;

import kr.ac.sungkyul.mysite.web.board.BoardAction;
import kr.ac.sungkyul.mysite.web.main.MainAction;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class UsersActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)){
			action = new JoinFormAction();
		} else if ("join".equals(actionName)){
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)){
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)){
			action = new LoginFormAction();
		} else if ("login".equals(actionName)){
			action = new LoginAction();
		} else if ("logout".equals(actionName)){
			action = new LogoutAction();
		} else if ("modifyform".equals(actionName)){
			action = new ModifyformAction();
		} else if ("modify".equals(actionName)){
			action = new ModifyAction();
		} else if ("board".equals(actionName)){
			action = new BoardAction();
		} else{
			action = new MainAction();
		}
		return action;
	}

}
