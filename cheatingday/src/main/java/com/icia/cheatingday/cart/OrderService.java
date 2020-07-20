package com.icia.cheatingday.cart;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Inject
	private OrdersDao dao;
	
	public List<Orders> read(String uUsername) {
		return dao.findAll(uUsername);
	}
	
	
}
