package com.icia.cheatingday.exception;

import lombok.*;

@AllArgsConstructor
public class JobFailException extends RuntimeException {
	private String message;
	
	@Override
	public String getMessage() {
		return message ;
	}
}
