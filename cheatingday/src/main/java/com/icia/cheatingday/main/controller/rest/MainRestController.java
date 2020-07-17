package com.icia.cheatingday.main.controller.rest;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icia.cheatingday.main.service.rest.MainRestService;

@RestController
public class MainRestController {

   @Autowired
   private MainRestService service;

   // [일반] 아이디 중복확인
   @GetMapping("/main/u_id_check")
   public ResponseEntity<Boolean> checkUserId(@RequestParam @NotNull String uUsername) {
      return ResponseEntity.ok(service.checkUserId(uUsername));
   }

   // [일반] 이메일 중복확인
   @GetMapping("/main/u_email_check")
   public ResponseEntity<Boolean> checkUserEmail(@RequestParam @NotNull String uEmail) {
      return ResponseEntity.ok(service.checkUserEmail(uEmail));
   }

   
   ////////////////////////////////////////////////////////////////////////////////
   
   
   // [사업자] 아이디 중복확인
   @GetMapping("/main/m_id_check")
   public ResponseEntity<Boolean> checkManagerId(@RequestParam @NotNull String mUsername) {
      return ResponseEntity.ok(service.checkManagerId(mUsername));
   }

   // [사업자] 이메일 중복확인
   @GetMapping("/main/m_email_check")
   public ResponseEntity<Boolean> checkManagerEmail(@RequestParam @NotNull String mEmail) {
      return ResponseEntity.ok(service.checkManagerEmail(mEmail));
   }

   // [사업자] 사업자 등록번호 중복확인
   @GetMapping("/main/m_num_check")
   public ResponseEntity<Boolean> checkManagerNumber(@RequestParam @NotNull Long mNum) {
      return ResponseEntity.ok(service.checkManagerNum(mNum));
   }
}
