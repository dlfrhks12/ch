package com.icia.cheatingday.user.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Point {
	private String uUsername;		// 아이디
	private int oNo;				// 주문번호
	private int accumulationSal;	// 적립금액
	private int totalPoint;			// 총 포인트
}
