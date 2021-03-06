package com.campaigo.control;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.CampaignDAO;
import com.campaigo.dbcontrol.CampainManager;
import com.campaigo.dbcontrol.UserManager;
import com.campaigo.model.Campaign;
import com.jfinal.core.Controller;

public class CampaignControl extends Controller {
	public void ask(){
		String campaignJson = getPara("info");
		String campaiOwn = getPara("id");
		
		boolean isOk = new CampainManager().askForCampaign(campaignJson,campaiOwn);
		renderText(String.valueOf(isOk));
	}
	
	public void GetAll() {
		List<Campaign> camList = new CampainManager().getAll();
		String jsonList = JSON.toJSONString(camList);
		renderText(jsonList);
	}
	
	public void register(){
		String id = getPara("id");
		String caid = getPara("caid");
		CampainManager cm = new CampainManager();
		boolean render;
		if(new UserManager().isExist(id)&&cm.isExist(Integer.valueOf(caid)))
			render = cm.register(id, caid);
		else
			render = false;
		renderText(String.valueOf(render));
	}
	//活动报名状态获取
	public void regStatus(){
		String id = getPara("userid");
		String caid = getPara("campaiId");
		String render = String.valueOf(new CampainManager().isRegister(caid, id));
		renderText(render);
	}
	//
	public void lender(){
		String caid = getPara("campaiId");
		String lenderJson = JSON.toJSONString(new UserManager().findLender(caid));
		renderText(lenderJson);
	}
	
	//活动搜索
	public void search(){
		String camname = getPara("wd");
		renderText(JSON.toJSONString(new CampaignDAO().findByName(camname)));
	}
	//Upcoming列表&Past列表
	public void myList(){
		String id = getPara("id");
		renderText(new CampainManager().findMyList(id));
	}
}
