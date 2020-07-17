package com.icia.cheatingday.main.controller.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.icia.cheatingday.exception.*;

@ControllerAdvice
public class MainRestControllerAdvice {
   @ExceptionHandler(UsernameExistException.class)
   public ResponseEntity<String> usernameExistExceptionHandler() {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 아이디입니다");   //CONFLICT : 409 발생시키기
   }
   
   @ExceptionHandler(EmailExistException.class)
   public ResponseEntity<String> emailExistExceptionHandler() {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 이메일입니다");
   }
}
