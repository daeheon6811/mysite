package com.douzone.mysite.web.main;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}
	
	

}
