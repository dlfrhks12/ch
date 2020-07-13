package com.icia.cheatingday.cart;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class CartEntity implements Serializable {
	/*
	m_no NUMBER(10) NOT NULL, // 제품번호 
	u_username VARCHAR2(20), // 아이디 
	cart_name NVARCHAR2(30) NOT NULL, // 제품이름 
	cart_price NUMBER(10) NOT NULL, //금액
	cart_day DATE, // 주문일 
	cart_count NUMBER(10) NOT NULL, // 수량 
	image VARCHAR2(100), // 가격사진 
	cart_jumunMoney NUMBER(10)  주문총가격 
	*/
	private static final long serialVersionUID = 1L;
	
	
	private Integer mNo;
	private String uUsername;
	private String cartName;
	private int cartPrice;
	private LocalDateTime cartDay;
	private int cartCount;
	private String image;
	private int cartJumunMoney;
	
}
