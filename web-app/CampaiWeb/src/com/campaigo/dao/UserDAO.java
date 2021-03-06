package com.campaigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.campaigo.model.User;
import com.campaigo.util.DBUtil;


public class UserDAO {
	public User findUserById(String id){
		User retUser = new User();
		String sql = new String("select * from user where id = ?");
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				retUser.setId(rs.getString("id"));
				retUser.setUsname(rs.getString("Usname"));
				retUser.setPwd(rs.getString("pwd"));
				retUser.setClassName(rs.getString("className"));
				retUser.setPosition(rs.getString("position"));
				retUser.setErrorLogin(false);
			}
			conn.close();
			pst.close();
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		//System.out.println(retUser.getPwd());
		return retUser;
	}
}
