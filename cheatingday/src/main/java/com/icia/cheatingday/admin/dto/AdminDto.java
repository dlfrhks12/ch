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
		private String rTitle;
		private String uUsername;
		private String uIrum;
		private String rWriteTimeStr;
		private int rReport;
		private int sNum;
		private String sName;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForuserList{
		private String uUsername;
		private String uIrum;
		private String uTel;
		private String uEmail;
		private String uJoinDateStr;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForblockList{
		private String uUsername;
		private String uIrum;
		private String uTel;
		private String uEmail;
		private String uJoinDateStr;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoFormList{
		private String mUsername;
		private String mIrum;
		private String mTel;
		private String mEmail;
		private long mNum;
		private String mEnabledStr;
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
