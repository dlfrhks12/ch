package com.icia.cheatingday.manager.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class sNumNotFoundException extends RuntimeException {

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
	
	
}
