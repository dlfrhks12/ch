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
//o_no NUMBER(10) NOT NULL, /* 주문번호 */
//o_total NUMBER(10) NOT NULL, /* 총금액 */
//o_order_time DATE NOT NULL, /* 주문일 */
//u_username VARCHAR2(20), /* 아이디 */
//o_check NUMBER(1), /* 주문확인여부 */
//s_num NVARCHAR2(50) /* 음식점 고유번호 */
public class OrderEntity {
	private Integer oNo; // 주문번호
	private Integer oTotal; // 총금액
	private LocalDateTime oOrderTime; // 주문일
	private String uUserName; // 아이디
	private Integer oCheck; // 주문여부 확인
	private String sNum; // 음식점 고유번호
}
