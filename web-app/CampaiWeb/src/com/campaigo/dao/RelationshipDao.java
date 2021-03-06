package com.campaigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.campaigo.model.PartyRelationShip;
import com.campaigo.util.DBUtil;

public class RelationshipDao {
	//加入一个活动关系。
	public void add(String userid,int camid,int roletype){
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "insert into party_relationship(party_relationship_id,party_relationship_camid,role) values(?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setInt(2,camid);
			pst.setInt(3, roletype);
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
	}
	//找出用户与活动关系
	public PartyRelationShip findbyKey(int campaiId, String userid){
		//这里只有一个返回值，说明一个人仅能参加一次一个活动。
		PartyRelationShip ret = new PartyRelationShip();
		try {
			Connection conn = DBUtil.getConnection();
			String sql ="select role from party_relationship where party_relationship_id=? and party_relationship_camid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs;
			pst.setString(1, userid);
			pst.setInt(2, campaiId);
			rs = pst.executeQuery();
			if(rs.next()){
				ret.setRole(rs.getInt(1));
				ret.setPartyRelationShipId(userid);
				ret.setPartyRelationShipIdCamId(campaiId);
			}else{
				ret.setPartyRelationShipIdCamId(-1);
				//如果没有，则返回caid为-1的对象。
			}
			conn.close();
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	//找到活动发布者id
	public PartyRelationShip findbyKey(int camid){
		PartyRelationShip ret = new PartyRelationShip();
		try {
			Connection conn = DBUtil.getConnection();
			String sql ="select * from party_relationship where role=? and party_relationship_camid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs;
			pst.setInt(1, 0);
			pst.setInt(2, camid);
			rs = pst.executeQuery();
			if(rs.next()){
				ret.setRole(0);
				ret.setPartyRelationShipId(rs.getString("party_relationship_id"));
				ret.setPartyRelationShipIdCamId(camid);
			}else{
				ret.setPartyRelationShipIdCamId(-1);
				//如果没有，则返回caid为-1的对象。
			}
			conn.close();
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ret;
	}
	//通过用户id找到参加的活动id
	public List<PartyRelationShip> findByUserId(String userid){
		List<PartyRelationShip> retList = new ArrayList<PartyRelationShip>();
		PartyRelationShip prs;
		try {
			Connection conn = DBUtil.getConnection();
			String sql ="select * from party_relationship where party_relationship_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs;
			
			pst.setString(1, userid);
			rs = pst.executeQuery();
			while(rs.next()){
				prs = new PartyRelationShip();
				prs.setRole(rs.getInt("role"));
				prs.setPartyRelationShipId(rs.getString("party_relationship_id"));
				prs.setPartyRelationShipIdCamId(rs.getInt("party_relationship_camid"));
				retList.add(prs);
			}
			conn.close();
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return retList;
	}
	public static void main(String[] args) {
		List<PartyRelationShip> psr = new RelationshipDao()
				.findByUserId("31501337");
		System.out.println(psr.size());
		 
	}
}
