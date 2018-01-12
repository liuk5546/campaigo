package com.campaigo.dbcontrol;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.RelationshipDao;
import com.campaigo.dao.UserDAO;
import com.campaigo.model.PartyRelationShip;
import com.campaigo.model.User;

public class UserManager {
	private UserDAO dao = new UserDAO();
	private RelationshipDao rdao = new RelationshipDao();
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
	
	public boolean isExist(String id){
		User user = dao.findUserById(id);
		return !user.isErrorLogin();
		//true 有 false 没有
	}
	//找到发布者信息
	public User findLender(String camid){
		//User u ;
		PartyRelationShip prs = rdao.findbyKey(Integer.valueOf(camid));
		if(prs.getPartyRelationShipIdCamId()==-1){
			return new User();
		}else{
			return dao.findUserById(prs.getPartyRelationShipId());
		}
		
	}
}
