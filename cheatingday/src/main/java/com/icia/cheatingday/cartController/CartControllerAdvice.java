package com.icia.cheatingday.cartController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icia.cheatingday.exception.CartFailException;
import com.icia.cheatingday.exception.NoStockException;
import com.icia.cheatingday.exception.OutOfStockException;


@RestControllerAdvice
public class CartControllerAdvice {
	// ResponseEntity 한글 출력을 위한 헤더 생성 메소드
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}
	
	// 재고가 없는 경우
	@ExceptionHandler(NoStockException.class)
	public ResponseEntity<String> NSEHandler() {
		return new ResponseEntity<String>("재고가 없습니다", getHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	// 작업이 실패한 경우(예를 들어 전달받은 제품번호가 장바구니에 없다든지)
	@ExceptionHandler(CartFailException.class)
	public ResponseEntity<?> CFEHandler(CartFailException e) {
		return new ResponseEntity<String>(e.getMessage(), getHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// 구입 개수가 재고량를 넘어서는 경우
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<?> OSEHandler() {
		return new ResponseEntity<String>("더 이상 구매할 수 없습니다", getHeaders(), HttpStatus.BAD_REQUEST);
	}
}
