package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ManagerServiceTest {

	@Autowired
	private ManagerService service;
	
	/*
	@Test
	public void myinforeadTest() {
<<<<<<< HEAD
	//	assertThat(service.read(111111113), is(notNullValue()));
		//System.out.println(service.read(111111113));
=======
<<<<<<< HEAD
	//	assertThat(service.read(111111113), is(notNullValue()));
	//	System.out.println(service.read(111111113));
=======
		assertThat(service.read("11111"), is(notNullValue()));
		System.out.println(service.read("11111"));
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
		
	}
	*/

}
