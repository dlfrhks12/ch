package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.cart.CartEntity;
import com.icia.cheatingday.cart.CartService;
import com.icia.cheatingday.cart.ProductDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class cartServiceTest {
	@Inject private CartService service;
	@Inject private ProductDao dao;
	@Inject private List<CartEntity> cartList;
	/*
	 * @Test public void cartAddTest() { CartEntity cart = CartEntity.builder()
	 * .cartNo(1) .uUsername("spring") .cartName("짜장면") .cartPrice(10000)
	 * .cartDateTime(LocalDateTime.now()) .cartCount(3) .image("짜장면.jpg") .build();
	 * assertThat(service.insert(session, mNo) is(1)); }
	 */
}
