package com.icia.cheatingday.user.dto;

import lombok.*;
import lombok.experimental.*;

public class FavoriteDto {
	private FavoriteDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForFav {
		private String uUsername;
		private int sNum;
	}
}
