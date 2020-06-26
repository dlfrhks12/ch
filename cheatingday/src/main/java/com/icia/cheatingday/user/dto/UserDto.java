package com.icia.cheatingday.user.dto;

import lombok.*;
import lombok.experimental.*;

public class UserDto {
	private UserDto() {}
	@Data
	@Accessors(chain=true)
	public static class DtoForRead {
		private String uUsername;
		private String uIrum;
		private String uEmail;
		private String uTel;
		private String uAddress;
		private String uPassword;
		private String joinDateStr;
		private long days;
		private int uPoint;
	}
	
}
