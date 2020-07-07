package com.icia.cheatingday.user.dto;

import java.time.*;

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
		private int pno;
		private String uUsername;
		private String sName;
		private String oOderTimeStr;
		private String oNO;
	}
}
