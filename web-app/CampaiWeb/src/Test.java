import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.alibaba.fastjson.JSON;
import com.campaigo.model.Campaign;
import com.campaigo.model.RoleType;
import com.campaigo.util.DBUtil;


public class Test {
	public static void main(String[] args) {
		Campaign ca = new Campaign();
		ca.setCaname("testCampai");
		ca.setEndeadline(new Timestamp(System.currentTimeMillis()));
		ca.setStartline(new Timestamp(System.currentTimeMillis()));
		ca.setEndline(new Timestamp(System.currentTimeMillis()));
		ca.setDescribe("This is a test Campain.");
		String a = JSON.toJSONString(ca);
		System.out.println(a);
		Campaign ba = JSON.parseObject(a, Campaign.class);
		System.out.println("name:"+ba.getCaname()+"\n"
				+ "time:"+ba.getEndeadline().toString());
//		try {
//			Connection conn = DBUtil.getConnection();
//			//RoleType roleType = new RoleType();
//			String sql = "insert into campaign(caname,endeadline,startline,endline,describes) values(?,?,?,?,?)";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, ca.getCaname());
//			pst.setTimestamp(2,ca.getEndeadline());
//			pst.setTimestamp(3, ca.getStartline());
//			pst.setTimestamp(4, ca.getEndline());
//			pst.setString(5, ca.getDescribe());
//			pst.execute();
//			pst.close();
//			conn.close();
//			System.out.println("Su");
//		} catch (SQLException e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//			System.out.println("Fa");
//		}
	}
}
