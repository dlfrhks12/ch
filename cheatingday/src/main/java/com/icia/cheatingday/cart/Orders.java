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
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int orderNo; //주문번호
	private int menuno; //메뉴번호
	private String uUsername; //유저아이디
	private String cartName; //메뉴이름
	private int cartPrice; //메뉴가격
	private LocalDateTime cartDay; //주문일
	private int cartCount; //메뉴수량
	private String image; //메뉴사진
	private int cartJumunMoney; //메뉴금액
	private int sNum; //음식점고유번호
}
