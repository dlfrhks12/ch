package com.icia.cheatingday.exception;

import lombok.*;

@AllArgsConstructor
public class CartFailException extends RuntimeException {
	private String msg;
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
