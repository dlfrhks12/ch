package com.icia.cheatingday.order.dao;

import java.util.*;

import javax.inject.*;

import org.mybatis.spring.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.order.entity.*;

@Repository
public class OrderDao {
	@Inject
	private SqlSessionTemplate tpl;
	
	// 1. 장바구니 추가
	public int orderInsert(DetailorderEntity order) {
		return tpl.insert("orderMapper.orderInsert", order);
	}
	
	// 2. 장바구니 목록
	public List<DetailorderEntity> order(String uUsername) {
		return tpl.selectList("orderMapper.order", uUsername);
	}
	
	// 3. 장바구니 삭제
	public int orderDelete(int dNo) {
		return tpl.delete("orderMapper.orderDelete", dNo); 
	}
	
	// 4. 장바구니 수정?
	public int orderUpdate(DetailorderEntity order) {
		return tpl.update("orderMapper.orderUpdate", order);
	}
	
	// 5. 장바구니 금액 합계
	public int orderAllSum(String uUsername) {
		return tpl.selectOne("orderMapper.orderAllSum", uUsername);
	}
	
	// 6. 장바구니 상품수량변경
	public int orderUpdatedCnt(DetailorderEntity order) {
		return tpl.update("orderMapper.orderUpdatedCnt", order);
	}
	
	public List<DetailorderEntity> myBas(String uUsername) {
		return tpl.selectList("orderMapper.basList", uUsername);
	}
	
	
	public List<OrderEntity> resList() {
		return tpl.selectList("orderMapper.resList");
	}

	public List<OrderEntity> findAll(String uUsername) {
		return tpl.selectList("orderMapper.findAll", uUsername);
	}
		
	
}
