package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.entity.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ManagerDaoTest {
	@Autowired
	private ManagerDao dao;
	
	//@Test
	public void insertTest() {
		ManagerEntity m = ManagerEntity.builder().mNum("111111").mUsername("spring").mPassword("1234").mEmail("a@a.com")
				.mTel("01011111111").mAccount("123").sName("상호명").mIrum("사업자").build();
		assertThat(dao.insert(m), is(1));
	}
	
	// @Test
	public void existsByManagerNumberTest() {
		assertThat(dao.existsByManagerNumber("111111"), is(true));
		assertThat(dao.existsByManagerNumber("010101"), is(false));
	}
	
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
	
	// @Test
	public void findById() {
		assertThat(dao.findById("111111").getSName(), is("상호명"));
	}
	
	//@Test
	public void deleteTest() {
		dao.delete("1111111");
	}
}
