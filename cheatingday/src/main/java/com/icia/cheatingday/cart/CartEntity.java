package com.icia.cheatingday.cart;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	private static final long serialVersionUID = 1L;
	
		
	private int menuno; // 제품번호 
	private String uUsername;  // 유저 아이디 
	private String cartName; // 제품이름 
	private int cartPrice; //금액
	private LocalDateTime cartDay; // 주문일 
	private int cartCount; // 수량 
	private String image; // 메뉴사진 
	private int cartJumunMoney; // 주문총가격(금액 * 수량) 
	private int sNum; // 사업자 번호 
	
	// cartJumunMoney의 계산식
	public void increase() {
		cartCount++;
		cartJumunMoney = cartCount * cartPrice;
	}
	public void decrease() {
		cartCount--;
		cartJumunMoney = cartCount * cartPrice;
	}
}

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