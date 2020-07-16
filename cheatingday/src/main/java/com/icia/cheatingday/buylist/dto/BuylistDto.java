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
	private int oNo;
	private Integer oTotal;
	private String oOrderTimeStr;
	private String uUSername;
	private Integer oCheck;
	private int sNum;
	private String sName;
	private Boolean favCheck;
	}
}
