package com.icia.cheatingday.buylist.dto;

import java.time.*;

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
		private int orderNo;
		private int menuno;
		private String uUsername;
		private String cartName;
		private int cartPrice;
		private String cartDayStr;
		private int cartCount;
		private String image;
		private int cartJumunMoney;
		private int sNum;
		private String sName;
		private String menuname;
		private Boolean favCheck;
	}
	
}
