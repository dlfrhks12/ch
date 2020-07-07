package com.icia.cheatingday.admin.dto;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

public class AdminDto {
	private AdminDto() {}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForList{
		private int rNo;
		private int rTitle;
		private String uUsername;
		private String uIrum;
		private String rWriteTimeStr;
		private int rReprot;
		private int sNum;
		private String sName;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForJoin{
		private String aUsername ;
		private String aPassword;
		private String aIrum;
		private List<String> authorities;
	}
}
