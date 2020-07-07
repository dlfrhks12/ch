package com.icia.cheatingday.center.controller.rest;

import java.security.*;

import javax.validation.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.center.service.rest.*;

@RestController
public class QnARestController {
	@Autowired
	private QnARestService service;
	
	
	@PatchMapping("/center/update")
	public ResponseEntity<Void> updateQna(@Valid QnADto.DtoForUpdate dto, BindingResult results) throws BindException {
		if(results.hasErrors())
			throw new BindException(results);
		service.updateQnA(dto);
		return ResponseEntity.ok(null);
	}
	@DeleteMapping("/center/delete")
	public ResponseEntity<?> deleteQna(Integer qNo, Principal principal) {
		service.deletQna(qNo, principal.getName());
		return ResponseEntity.ok("/cheatingday/center/list");
	}
	@PostMapping("/center/comment_write")
	public ResponseEntity<?> writeComment(@Valid QnAComment qnAComment, BindingResult bindingResult, Principal principal) {
		return ResponseEntity.ok(service.writeQComment(qnAComment));	
	}
	@PatchMapping("/center/comment_update")
	public ResponseEntity<Void> updatecomment(@Valid QnAComment qnAComment, BindingResult results) throws BindException {
		if(results.hasErrors())
			throw new BindException(results);
		service.updateQnAcomment(qnAComment);
		return ResponseEntity.ok(null);
	}
	@DeleteMapping("/center/comment_delete")
	public ResponseEntity<?> deletecomment(Integer qNo, Integer qcNo, Principal principal) {
		service.deleteComment(qNo, qcNo, principal.getName());
		return ResponseEntity.ok("/cheatingday/center/read?qNo="+qNo);
	}
	
}
