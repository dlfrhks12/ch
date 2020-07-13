package com.icia.cheatingday.order.dto;

import lombok.*;
import lombok.experimental.*;

public class DetailorderDto {
	private DetailorderDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
	private int dNo;			// 상세번호
	private int dSal;			// 금액
	private int dMenuCnt;		// 수량
	private String dMenuName;	// 이름
	private int oNo;			// 주문번호
	private int menuno; 		// 메뉴번호
	private String uUserName;	// 유저아이디
	}
}
