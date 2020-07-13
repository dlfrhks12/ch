package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.manager.entity.ManagerEntity;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
//public class ManagerDaoTest {
	//@Autowired
//	private ManagerDao dao;

	
/*	
	//@Test
	public void insertTest() {
		ManagerEntity m = ManagerEntity.builder().mNum(1234567890).mUsername("spring").mPassword("1234").mEmail("a@a.com")
				.mTel("01011111111").mAccount("123").sName("상호명").mIrum("사업자").build();
		assertThat(dao.insert(m), is(1));
	}
	
	// @Test
	
	 * public void existsByManagerNumberTest() {
	 * assertThat(dao.existsByManagerNumber("111111"), is(true));
	 * assertThat(dao.existsByManagerNumber("010101"), is(false)); }
	 
	
	//@Test
	public void existsByIdTest() {
		assertThat(dao.existsById("spring"), is(true));
		assertThat(dao.existsById("summer"), is(false));
	}
	
	//@Test
	public void existsByEmailTest() {
		assertThat(dao.existsByEmail("a@a.com"), is(true));
		assertThat(dao.existsByEmail("b@b.com"), is(false));
	}
	
	@Test
	public void existsByManagerNumberTest() {
		assertThat(dao.existsByManagerNumber(1234567892), is(false));
		
	}
	
	 //@Test
	public void findById() {
	//	assertThat(dao.findById(111111113), is(notNullValue()));
	//	System.out.println(dao.findById(111111113));
<<<<<<< HEAD
		assertThat(dao.findById("1111"), is(notNullValue()));
=======
	//	assertThat(dao.findById(111111113), is(notNullValue()));
	//	System.out.println(dao.findById(111111113));
	//	assertThat(dao.findById("1111"), is(notNullValue()));
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
		System.out.println(dao.findById("1111"));
	}
	
	//@Test
	public void deleteTest() {
		//dao.delete("1111111");
	}
	
	//매니저정보수정 update테스트 dao
	//@Test성공
	public void updateTest() {
		ManagerEntity manager = ManagerEntity.builder().mNum(1).mPassword("0101").mEmail("red@naver.com").mTel("010-123-456")
				.mIrum("예지사장").build();
		
		assertThat(dao.update(manager), is(1));
	}
	
	//매니저 내정보 읽어오기
	//@Test 성공
	public void findByID() {
		//assertThat(dao.findById("가-01"), is(notNullValue()));
	}
}
*/