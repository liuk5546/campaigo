package com.campaigo.control;

import com.campaigo.dbcontrol.UserManager;
import com.jfinal.core.Controller;

public class LoginController extends Controller {
	//http://localhost:8080/login?un=31501337&pw=MTIzNDU2
	public void index(){
		String id = getPara("un");
		String pwd = getPara("pw");
		UserManager um = new UserManager();
		renderText(um.login(id, pwd));
	}
	
}
