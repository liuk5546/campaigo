package com.campaigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.campaigo.util.DBUtil;

public class RelationshipDao {
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
	
	public static void main(String[] args) {
		new RelationshipDao().add("31501337", 2, 1);
	}
}
