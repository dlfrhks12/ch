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
public class OrderDetails implements Serializable {
	// 예비주문상세내역 Entity DB table = cart
	private static final long serialVersionUID = 1L;
	
	private int orderNo; // 주문번호
	private int menuno; // 메뉴 번호
	private String uUsername; // 유저 아이디
	private String cartName; // 메뉴이름
	private int cartPrice; // 메뉴 가격
	private LocalDateTime cartDay; // 주문일자
	private int cartCount; // 수량
	private String image; // 메뉴사진
	private int sNum; // 사업자 번호
	private int cartJumunMoney; // 해당메뉴 총 가격
}



