package com.icia.cheatingday.buylist.dto;

import lombok.*;
import lombok.experimental.*;

public class BuylistDto {
	private BuylistDto() {}
	@Data
	@AllArgsConstructor 
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForList {
		private int orderNo; //주문번호
		private String cartDayStr; //주문시간
		private String uUsername; //사용자아이디
		private int sNum; //상호번호
		private String sName;
		private int cartPrice;//메뉴금액
		private int cartCount;//메뉴수량
		private String cartName; //메뉴이름
		private int menuno; //메뉴번호
		private Boolean favCheck;
	}
	
}
