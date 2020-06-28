package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.order.dao.OrderDao;
import com.icia.cheatingday.order.entity.DetailorderEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class OrderTest {
	@Autowired private OrderDao dao;

	// @Test
	public void orderInsertTest() {
		DetailorderEntity d = DetailorderEntity.builder()
				.dNo(2)
				.dSal(200)
				.dMenuCnt(6)
				.dMenuName("짜짱명")
				.oNo(8)
				.menuno(2)
				.uUserName("spring")
				.build();
		assertThat(dao.orderInsert(d), is(1));
	}
	
	// @Test
	public void orderTest() {
		assertThat((dao.order("spring")).size(), is(1));
	}
}
