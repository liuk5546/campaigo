package com.campaigo.dbcontrol;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.UserDAO;
import com.campaigo.model.User;

public class UserManager {
	private UserDAO dao = new UserDAO();
	public String login(String id,String pwd){
		String retUserJson="";
		User loginUser = dao.findUserById(id);
		byte[] asPassWord = Base64.getDecoder().decode(pwd);
		
		try {
			pwd = new String(asPassWord,"utf-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(loginUser.getPwd().equals(pwd)){
			loginUser.setPwd(new String(Base64
					.getEncoder()
					.encodeToString(loginUser
							.getPwd()
							.getBytes())));
			
			
		}else{
			loginUser.init();
		}
		retUserJson = JSON.toJSONString(loginUser);
		return retUserJson;
	}
}
