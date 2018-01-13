package com.demo;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.jfinal.core.Controller;

public class LoginController extends Controller {
	
	public void index(){
		String username = getPara("un");
		String password = getPara("pw");
		byte[] asPassWord = Base64.getDecoder().decode(password);
		try {
			password = new String(asPassWord,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("");
			renderText("{mode:false}");
		}
		//System.out.println("UserName:"+username+"Password:"+password);
		renderText("{mode:true}");
	}
	//ex. URL:http://localhost/login?un=liukun&pw=0000
}
