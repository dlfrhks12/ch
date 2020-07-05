package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.entity.MenuEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class menuDaoTest {

	@Autowired
	private MenuDao dao;
	/*
	 * @Test public void insertTest() { MenuEntity menu =
	 * MenuEntity.builder().menuno(1).menusal(8000)
	 * .menusajin("aaa.jpg").menuname("짬뽕").sNum("가01").build();
	 * assertThat(dao.insert(menu),is(1)); }
	 */
	/*
	 * //@Test public void findAllTest() { assertThat((dao.findAll(3)).size(),
	 * is(1)); }
	 */
	
	//@Test
	public void updateTest() {
		MenuEntity menu = MenuEntity.builder().menuno(3).menusal(9000).menusajin("noodle.jpg")
				.menuname("볶음면").build();
		assertThat(dao.update(menu), is(1));
	}
	
	//@Test
	public void findById() {
		assertThat(dao.findById(3), is(notNullValue()));
		
	}
	
	//@Test
	public void delete() {
		assertThat(dao.delete(3),is(1));
	}
}
	

