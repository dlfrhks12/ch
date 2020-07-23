package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.ManagerDto.DtoForWrite;
import com.icia.cheatingday.manager.entity.StoreApplyInsert;
import com.icia.cheatingday.manager.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ManagerStoreApplyInsertTest {

	/*
	 * @Autowired private ManagerStoreApplyInsertDao dao;
	 * 
	 * @Autowired private ManagerService service;
	 * 
	 * /////////////////////////Dao 테스트다//////////////////////// //@Test성공 public
	 * void insertTest() { // assertThat(dao.insert(1,"가-0111"), is(1));
	 * 
	 * }
	 * 
	 * //@Test public void findByIrum() { assertThat(dao.findByIrum("가-01"),
	 * is(notNullValue())); System.out.println(dao.findByIrum("가-01")); }
	 * 
	 * //@Test public void findByEmail() { assertThat(dao.findByEmail("가-01"),
	 * is(notNullValue())); System.out.println("--------");
	 * System.out.println(dao.findByEmail("가-01")); }
	 * 
	 * //@Test public void findByTel() { assertThat(dao.findByTel("가-01"), is(1));
	 * System.out.println(dao.findByTel("가-01")); }
	 * 
	 * ///////////////////service 테스트다/////////////////// //@Test 성공햇다ㅜㅜㅜㅜㅜ겁나어려웡ㅠㅠ
	 * public void writeTest() { ManagerDto.DtoForWrite dto = new DtoForWrite();
	 * dto.setMNum("가-01"); System.out.println(service.write(dto)); }
	 */
}
