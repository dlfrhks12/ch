package com.icia.cheatingday.user.dto;

import lombok.*;
import lombok.experimental.*;

public class PointDto {
	private PointDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
		private String uUsername;
		private int oNo;
		private int accumulationSal;
		private String oOrderTimeStr;
		private int SNum;
		private String SName;
	}
}
