package com.icia.cheatingday.cart;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired private SqlSessionTemplate tpl;
	@Inject ProductService service;
	
	// 메뉴
	
	// 메뉴 목록
	public List<ProductEntity> findAll() {
		return tpl.selectList("productMapper.findAll");
	}
	
	// 카트에 메뉴 번호 넘겨주기 위함
	public ProductEntity findByMNo(Integer mNo) {
		return tpl.selectOne("productMapper.findByMNo",mNo);
	}
	
}
