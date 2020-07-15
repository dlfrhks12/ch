package com.icia.cheatingday.cart;

import java.util.*;

import javax.inject.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class CartDao {
	@Autowired
	private SqlSessionTemplate tpl;
	@Inject
	private CartService service;

	// 장바구니
	// 장바구니추가
	public int insert(CartEntity cart) {
		return tpl.insert("cartMapper.insert", cart);
	}

	// 카트 리스트(인서트에 씀)
	public List<CartEntity> findCartList(String uUsername) {
		return tpl.selectList("cartMapper.findCartList", uUsername);
	}
	
	// 카트 목록 리스트
	public CartEntity findAllCart(Integer mNo) {
		return tpl.selectOne("cartMapper.findAllCart", mNo);
	}


	// 카트 카운트에 쓰일 카트 번호
	public CartEntity findByCartNo(int mNo) {
		return tpl.selectOne("cartMapper.findByCartNo", mNo);
	}
	
	// 개수 감소
	public int decreaseByAmount(int mNo) {
		return tpl.update("cartMapper.decreaseByAmount", mNo);
	}
	
	// 개수 증가
	public int increaseByAmount(int mNo) {
		return tpl.update("cartMapper.increaseByAmount", mNo);
	}
	
	// 3.장바구니 수정
	public int cartUpdate(CartEntity cart) {
		return tpl.update("cartMapper.cartUpdate", cart);
	}

	// 4.장바구니 삭제
	public int cartDelete(Integer mNo) {
		return tpl.delete("cartMapper.cartDelete", mNo);
	}

	
	public List<CartEntity> cartList(int mNo) {
		return tpl.selectList("cartMapper.cartList", mNo);
	}
	
}
