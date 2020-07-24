package com.icia.cheatingday.cart;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsDao {
	@Autowired
	private SqlSessionTemplate tpl;

	// 장바구니
	// 장바구니추가
	public int insertAll(List<OrderDetails> list) {
		return tpl.insert("cartMapper.insertAll", list);
	}
	
	// 주문상세 출력
	public List<OrderDetails> findAll() {
		return tpl.selectList("cartMapper.findAll");
	}
	
	// 카트 삭제
	public int deletes() {
		return tpl.delete("cartMapper.delete");
	}

}

