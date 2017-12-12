import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.campaigo.model.RoleType;
import com.campaigo.util.DBUtil;


public class Test {
	public static void main(String[] args) {
		try {
			Connection conn = DBUtil.getConnection();
			//RoleType roleType = new RoleType();
			String sql = "insert into roletype value(1,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "组织者");
			pst.execute();
			pst.close();
			conn.close();
			System.out.println("Su");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Fa");
		}
	}
}
