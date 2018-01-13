import java.util.List;

import com.alibaba.fastjson.JSON;
import com.campaigo.dao.CampaignDAO;
import com.campaigo.model.Campaign;

public class Test2 {
	public static void main(String[] args) {
		String a = JSON.toJSONString(new CampaignDAO().findByName("test"));
		List<Campaign> x = JSON.parseArray(a, Campaign.class);
		Campaign c = x.get(0);
		System.out.println(c.getCaname());
	}
}
