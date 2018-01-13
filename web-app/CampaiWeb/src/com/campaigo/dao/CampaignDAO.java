package com.campaigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.campaigo.model.Campaign;
import com.campaigo.util.DBUtil;

public class CampaignDAO {
	public void add(Campaign campaign) {
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "insert into campaign(caname,endeadline,startline,endline,describes) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, campaign.getCaname());
			pst.setTimestamp(2,campaign.getEndeadline());
			System.out.print(campaign.getEndeadline()==null);
			pst.setTimestamp(3, campaign.getStartline());
			pst.setTimestamp(4, campaign.getEndline());
			pst.setString(5, campaign.getDescribe());
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
	}
	
	public int findIdByName(String caname){
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "select * from campaign where caname = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, caname);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return rs.getInt("camid");
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
		//如果没有找到就返回-1
		return -1;
	}
	public Campaign findById(int camid){
		Campaign ret = new Campaign();
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "select * from campaign where camid = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, camid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				ret.setCamid(rs.getInt("camid"));
				ret.setCaname(rs.getString("caname"));
				ret.setEndeadline(rs.getTimestamp("endeadline"));
				ret.setStartline(rs.getTimestamp("startline"));
				ret.setEndline(rs.getTimestamp("endline"));
				ret.setDescribe(rs.getString("describes"));
				ret.setIspass(rs.getBoolean("pass"));
			}else{
				ret.setCamid(-1);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
		return ret;
	}
	
	public List<Campaign> loadAll() {
		List<Campaign> campList = new ArrayList<>();
		Campaign ca;
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "select * from campaign where pass = 1";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ca = new Campaign();
				ca.setCamid(rs.getInt("camid"));
				ca.setCaname(rs.getString("caname"));
				ca.setEndeadline(rs.getTimestamp("endeadline"));
				ca.setStartline(rs.getTimestamp("startline"));
				ca.setEndline(rs.getTimestamp("endline"));
				ca.setDescribe(rs.getString("describes"));
				ca.setIspass(rs.getBoolean("pass"));
				campList.add(ca);
			}
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
		return campList;
	}
	//模糊搜索
	public List<Campaign> findByName(String caname){
		List<Campaign> campList = new ArrayList<>();
		Campaign ca;
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "select * from campaign where caname like ? and pass = 1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+caname+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ca = new Campaign();
				ca.setCamid(rs.getInt("camid"));
				ca.setCaname(rs.getString("caname"));
				ca.setEndeadline(rs.getTimestamp("endeadline"));
				ca.setStartline(rs.getTimestamp("startline"));
				ca.setEndline(rs.getTimestamp("endline"));
				ca.setDescribe(rs.getString("describes"));
				ca.setIspass(rs.getBoolean("pass"));
				campList.add(ca);
			}
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
		return campList;
	}
	
	public List<Campaign> findNoPass(){
		List<Campaign> campList = new ArrayList<>();
		Campaign ca;
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "select * from campaign where pass = 0";
			PreparedStatement pst = conn.prepareStatement(sql);
			//pst.setString(1, "%"+caname+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ca = new Campaign();
				ca.setCamid(rs.getInt("camid"));
				ca.setCaname(rs.getString("caname"));
				ca.setEndeadline(rs.getTimestamp("endeadline"));
				ca.setStartline(rs.getTimestamp("startline"));
				ca.setEndline(rs.getTimestamp("endline"));
				ca.setDescribe(rs.getString("describes"));
				ca.setIspass(rs.getBoolean("pass"));
				campList.add(ca);
			}
			pst.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"search error!");
			// TODO: handle exception
		}
		return campList;
	}
	
	public void passCampaign(int camid){
		
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "update campaign set pass = 1 where camid =?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, camid);
			pst.execute();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		System.out.println(new CampaignDAO().findByName("test").toString());
	}
}
