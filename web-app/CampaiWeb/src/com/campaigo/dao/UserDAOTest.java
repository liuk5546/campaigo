package com.campaigo.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.campaigo.model.User;
import com.jfinal.aop.Before;

public class UserDAOTest {
	
	private static UserDAO userdao = new UserDAO();
	
	
	@Test
	public void testFindUserById() {
		User result = userdao.findUserById("31501337");
		System.out.println(result.getUsname());
		User anwser = new User();
		anwser.setId("31501337");
		anwser.setPwd("123456");
		anwser.setErrorLogin(false);
		anwser.setPosition("stu");
		anwser.setClassName("计算1501");
		anwser.setUsname("刘坤");
		assertEquals(result,anwser);
		
	}

}
