package com.icia.cheatingday.cart;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int insertOrderAll(List<Orders> orders) {
		return tpl.insert("orderMapper.insertOrderAll",orders);
	}
	
	// 주문상세 출력
	public List<Orders> findAlls() {
		return tpl.selectList("orderMapper.findAlls");
	}

	public int findByONo(int oNo) {
		return tpl.selectOne("orderMapper.findByONo", oNo);
	}
}
