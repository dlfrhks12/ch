package com.icia.cheatingday.admin.dto;

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
		private String sNum;
		private String sName;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForUlist{
		private String uUsername;
		private String uIrum;
		private Boolean uEnabled;
		private String uJoinDateStr;
		private int uLoginFailCnt;
	}
}
