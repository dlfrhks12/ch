package com.icia.cheatingday.cart;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartDto {
	private CartDto() { }
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class DtoForList implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Integer cartNo;
		private String uUsername;
		private Integer mNo;
		private String cartName;
		private int cartPrice;
		private LocalDateTime cartDay;
		private int cartCount;
		private String image;
		private int cartJumunMoney;
		private ProductEntity pro;
		
	}
	
}
