package com.icia.cheatingday.order.dto;

import lombok.*;
import lombok.experimental.*;

public class OrderDto {
	private OrderDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
	private int oNo;				// 주문번호
	private int oTotal;			// 총금액
	private String oOderTimeStr;	// 주문일
	private String uUsername;		// 아이디
	private Boolean oCheck;			// 주문확인여부
	private long sNum;				// 음식점 고유번호
	}
}
