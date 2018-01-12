package com.campaigo.control;

import com.campaigo.dbcontrol.CampainManager;
import com.jfinal.core.Controller;

public class OrgController extends Controller {
	public void loadnopass(){
		renderText(new CampainManager().findNoPass());
	}
	
	public void orgnaizepass(){
		if(new CampainManager().passCampaign(getPara("campaiId"))){
			renderText("true");
		}else{
			renderText("false");
		}
	}
}
