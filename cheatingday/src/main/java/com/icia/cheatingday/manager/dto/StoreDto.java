package com.icia.cheatingday.manager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class StoreDto {

	private String sSajin;
	private int sNum;
	private String sInfo;
	private String sName;
	private String sTel;
	private String sAddress;
	private int foodNo;
	private String foodCategory;
	
}
