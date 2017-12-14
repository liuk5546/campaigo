package com.campaigo.dbcontrol;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.CampaignDAO;
import com.campaigo.dao.RelationshipDao;
import com.campaigo.model.Campaign;

public class CampainManager {
	private CampaignDAO dao = new CampaignDAO();
	private RelationshipDao rdao = new RelationshipDao();
	//接收JSON来添加活动
	public boolean askForCampaign(String campaignJson,String ownid){
		int camid;
		Campaign campaign = JSON.parseObject(campaignJson, Campaign.class);
		if(dao.findIdByName(campaign.getCaname())!=-1){
			return false;
			//如果名字存在，返回false
		}
		dao.add(campaign);
		
		camid = dao.findIdByName(campaign.getCaname());
		
		if(camid!=-1){
			//活动关系表加入
			rdao.add(ownid, camid, 0);
		}
		return true;
	}
}
