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
		private int bno;
		private String uUsername;
		private String sName;
		private String accumulationDayStr;
		private String accumulationSal;
	}
}
