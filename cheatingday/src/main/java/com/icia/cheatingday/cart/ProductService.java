package com.icia.cheatingday.cart;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Inject
	private ProductDao dao;
	
	public List<ProductEntity> list() {
		return dao.findAll();
	}
	
}
