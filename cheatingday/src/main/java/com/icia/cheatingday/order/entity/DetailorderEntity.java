package com.icia.cheatingday.order.entity;

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
@Accessors(chain = true)
public class DetailorderEntity {
	
//	   d_no NUMBER(10) NOT NULL, /* 상세번호 */
//	   d_sal NUMBER(10) NOT NULL, /* 금액 */
//	   d_menu_cnt NUMBER(10) NOT NULL, /* 수량 */
//	   d_menu_name NVARCHAR2(30) NOT NULL, /* 이름 */
//	   o_no NUMBER(10), /* 주문번호 */
//	   menuno NUMBER(10) /* 메뉴번호 */
//	   u_username VARCHAR2(20) /* 아이디 */
	
	private int dNo;			// 상세번호
	private int dSal;			// 금액
	private int dMenuCnt;		// 수량
	private String dMenuName;	// 이름
	private int oNo;			// 주문번호
	private int menuno; 		// 메뉴번호
	private String uUserName;	// 유저아이디

}
