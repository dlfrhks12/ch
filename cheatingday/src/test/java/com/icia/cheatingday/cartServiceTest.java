package com.icia.cheatingday;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.cart.OrderDetailsDao;
import com.icia.cheatingday.cart.CartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class cartServiceTest {
	@Inject private CartService service;
	@Inject private OrderDetailsDao dao;
	
	/*
	 * @Test public void cartAddTest() { CartEntity cart = CartEntity.builder()
	 * .cartNo(1) .uUsername("spring") .cartName("짜장면") .cartPrice(10000)
	 * .cartDateTime(LocalDateTime.now()) .cartCount(3) .image("짜장면.jpg") .build();
	 * assertThat(service.insert(session, menuno) is(1)); }
	 */
	
	
}
