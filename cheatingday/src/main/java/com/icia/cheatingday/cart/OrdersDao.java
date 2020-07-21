package com.icia.cheatingday.cart;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int insert(List<CartEntity> cartList) {
		return tpl.insert("cartMapper.insert",cartList);
	}
	
	public List<Orders> findAll(String uUsername) {
		return tpl.selectList("cartMapper.findAll", uUsername);
	}
}
