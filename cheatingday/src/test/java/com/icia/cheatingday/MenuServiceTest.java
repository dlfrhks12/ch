package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class MenuServiceTest {

	@Autowired
	private ManagerService service;
	
	@Test 
	public void menureadTest() { 
		assertThat(service.menuRead(22), is(notNullValue())); 
		System.out.println(service.menuRead(22));
	
	}
		
	
	
	/*
	 * //@Test public void writeTest() throws IllegalStateException, IOException {
	 * 
	 * MenuEntity menu = MenuEntity.builder().menuno(1).menusal(15000)
	 * .menusajin("abc.jpg").menuname("탕수육").sNum("다03").build(); MultipartFile
	 * sajin = null; //service.write(menu, sajin); }
	 * 
	 * //@Test public void updateTest() throws IllegalStateException, IOException {
	 * MenuEntity menu =
	 * MenuEntity.builder().menuno(4).menusal(4000).menusajin("rice.jpg")
	 * .menuname("밥").sNum("왜이래").build(); MultipartFile sajin = null;
	 * service.write(menu, sajin); }
	 * 
	 * //@Test public void listTest() { assertThat(service.menuList().size(),
	 * is(notNullValue())); }
	 * 
	 @Test public void menureadTest() { assertThat(service.menuRead(5),
	 * is(notNullValue())); }
	 * 
	 * //@Test public void deleteTest() { assertThat(service.menuDelete(4), is(1));
	 * }
	 * 
	 */
}
