package com.campaigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(new CampaignDAO().findIdByName("second"));
	}
}
