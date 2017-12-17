package com.campaigo.dbcontrol;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.CampaignDAO;
import com.campaigo.dao.RelationshipDao;
import com.campaigo.model.Campaign;
import com.campaigo.model.PartyRelationShip;

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
	
	public List<Campaign> getAll() {
		return dao.loadAll();
	}
	//报名
	public boolean register(String id,String caid){
		PartyRelationShip prs = rdao.findbyKey(Integer.valueOf(caid), id);
		if(prs.getPartyRelationShipIdCamId()==-1){
			rdao.add(id, Integer.valueOf(caid), 1);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExist(int camid){
		int a = dao.findById(camid).getCamid();
		if(a == -1){
			return false;
		}else{
			return true;
		}
	}
	
	//报名状态查询
	public boolean isRegister(String camid,String userid){
		PartyRelationShip prs = rdao.findbyKey(Integer.valueOf(camid), userid);
		if(prs.getPartyRelationShipIdCamId()==-1){
			return false;
		}
		return true;
	}
	
	//Upcoming列表&Past列表
	public String findMyList(String userid){
		String json;
		List<PartyRelationShip> prsList =  rdao.findByUserId(userid);
		List<Campaign> cpList = new ArrayList<>();
		for(PartyRelationShip i:prsList){
			int id = i.getPartyRelationShipIdCamId();
			cpList.add(dao.findById(id));
		}
		json = JSON.toJSONString(cpList);
		return json;
	}
	
	public String findNoPass(){
		return JSON.toJSONString(dao.findNoPass());
	}
	//活动管理通过
	public boolean passCampaign(String camid){
		int id = Integer.valueOf(camid);
		if(dao.findById(id).getCamid()!=-1){
			dao.passCampaign(id);
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		System.out.println(new CampainManager().findMyList("31501337"));
	}
}
