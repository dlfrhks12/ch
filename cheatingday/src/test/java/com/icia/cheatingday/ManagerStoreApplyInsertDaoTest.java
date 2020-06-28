package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.dao.ManagerStoreApplyInsertDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ManagerStoreApplyInsertDaoTest {

	@Autowired
	private ManagerStoreApplyInsertDao dao;
	
	//@Test성공
	public void insertTest() {
		assertThat(dao.insert(1,"가-0111"), is(1));
		
	}
	
	//@Test성공
	public void findByIrum() {
		assertThat(dao.findByIrum("가-01"), is(notNullValue()));
		System.out.println(dao.findByIrum("가-01"));
	}
	
	//@Test성공
	public void findByEmail() {
		assertThat(dao.findByEmail("가-01"), is(notNullValue()));
		System.out.println("--------");
		System.out.println(dao.findByEmail("가-01"));
	}
	
	//@Test 이렇게하면 성공인데, 1로 쓰면 오류뜬다?
	public void findByTel() {
		assertThat(dao.findByTel("가-01"), is(1));
		System.out.println(dao.findByTel("가-01"));
	}
	
}
