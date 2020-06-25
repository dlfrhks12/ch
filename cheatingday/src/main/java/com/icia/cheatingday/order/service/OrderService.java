package com.icia.cheatingday.order.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.icia.cheatingday.order.dao.OrderDao;
import com.icia.cheatingday.order.entity.DetailorderEntity;
import com.icia.cheatingday.order.entity.OrderEntity;

@Service
public class OrderService {
	@Inject OrderDao dao;
	
	// orderInsert, order(목록), orderDelete, orderUpdate, orderAllSum
	
	// 1. 장바구니 추가
	public void orderInsert(DetailorderEntity order) {
		dao.orderInsert(order);
	}
	
	// 2. 장바구니 목록
	public List<DetailorderEntity> order(String uUsername) {
		return dao.order(uUsername);
	}
	
	// 3. 장바구니 삭제
	public void orderDelete(int oNo) {
		dao.orderDelete(oNo);
	}
	
	// 4. 장바구니 수정
	public void orderUpdate(DetailorderEntity order) {
		dao.orderUpdate(order);
	}
	
	// 5. 장바구니 금액 합계
	public int orderAllSum(String uUsername) {
		return dao.orderAllSum(uUsername);
	}
	
	// 6. 장바구니 상품수량 변경
	public int orderUpdatedCnt(DetailorderEntity order) {
		return dao.orderUpdatedCnt(order);
	}
}
