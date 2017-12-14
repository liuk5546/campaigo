package com.campaigo.control;

import com.alibaba.fastjson.JSON;
import com.campaigo.dbcontrol.CampainManager;
import com.campaigo.model.Campaign;
import com.jfinal.core.Controller;

public class CampaignControl extends Controller {
	public void ask(){
		String campaignJson = getPara("info");
		String campaiOwn = getPara("id");
		
		boolean isOk = new CampainManager().askForCampaign(campaignJson,campaiOwn);
		//如果没有申请成功就false 返回一个false
		if(isOk)
			renderText(campaignJson);
		else
			renderText("false");
	}
}
