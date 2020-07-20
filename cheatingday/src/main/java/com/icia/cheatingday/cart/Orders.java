package com.icia.cheatingday.cart;

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
public class Orders {
	/*
	o_no NUMBER(10) NOT NULL,  주문번호 
    u_username VARCHAR2(20),  아이디 
    m_name NVARCHAR2(30),  메뉴이름 
	o_total NUMBER(10) NOT NULL,  총금액 
	o_order_time DATE NOT NULL, /주문일 
	s_num NVARCHAR2(50) 음식점 고유번호 */
	
	private int oNo;	// 주문번호
	private String uUsername; // 유저 아이디
	private int oTotal; // 총금액
	private LocalDateTime orderTime; // 주문시간
	private int sNum; // 음식점 고유번호
}
