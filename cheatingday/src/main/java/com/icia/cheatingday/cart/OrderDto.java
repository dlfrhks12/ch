package com.icia.cheatingday.cart;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


public class OrderDto {
	private OrderDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
	private int orderNo;
	private int menuno;
	private String uUsername;
	private String cartName;
	private int cartPrice;
	private LocalDateTime cartDay;
	private int cartCount;
	private String image;
	private int cartJumunMoney;
	private int sNum;
	private Boolean OCheck;
	}
}
