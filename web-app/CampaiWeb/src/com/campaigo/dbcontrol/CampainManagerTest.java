package com.campaigo.dbcontrol;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.campaigo.model.Campaign;

public class CampainManagerTest {
	private CampainManager cm = new CampainManager();
	private String testJson;
	private String testUser;
	@Test
	public void testAskForCampaign() {
		Campaign campaign = new Campaign();
		campaign.setCaname("社团文化节");
		campaign.setEndeadline(new Timestamp(System.currentTimeMillis()));
		campaign.setStartline(new Timestamp(System.currentTimeMillis()));
		campaign.setEndline(new Timestamp(System.currentTimeMillis()));
		campaign.setDescribe("第十七节ZUCC社团文化节");
		//System.out.println(campaign.getEndeadline().toString());
		testJson = JSON.toJSONString(campaign);
		
		testUser = "zucc0001";
		
		boolean ret = cm.askForCampaign(testJson, testUser);
		//assertEquals(true, ret);
		//ret = cm.askForCampaign(testJson, testUser);
		assertEquals(true,ret);
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExist() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMyList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindNoPass() {
		fail("Not yet implemented");
	}

	@Test
	public void testPassCampaign() {
		fail("Not yet implemented");
	}

}
