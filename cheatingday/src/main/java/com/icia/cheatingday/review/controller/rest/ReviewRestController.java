package com.icia.cheatingday.review.controller.rest;

import java.io.IOException;
import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.ReviewComment;
import com.icia.cheatingday.review.service.ReviewService;
import com.icia.cheatingday.review.service.rest.ReviewRestService;

@RestController
public class ReviewRestController {
	@Autowired
	private ReviewService service;
	@Autowired
	private ReviewRestService restService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/review/read")
	public ResponseEntity<?> read(@RequestParam @NotNull Integer rNo, Principal principal){
		String username = principal!=null? principal.getName():null;
		ReviewDto.DtoForRead dto = service.read(rNo, username);
		return ResponseEntity.ok(dto);
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/reviewComment/write")
	public ResponseEntity<?> writeComment(@Valid ReviewComment comment, BindingResult bindingResult, Principal principal){
		return ResponseEntity.ok(restService.writeComment(comment, principal.getName()));
	}
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/reviewComment/delete")
	public ResponseEntity<?> deleteComment(int rcNo, int rNo, Principal principal){
		return ResponseEntity.ok(restService.deleteComment(rcNo, rNo, principal.getName()));
	}
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/review/update")
	public ResponseEntity<Void> updateReview(@Valid ReviewDto.DtoForUpdate dto, BindingResult bindingResult, Principal principal) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUUsername(principal.getName());
		restService.updateReview(dto);
		return ResponseEntity.ok(null);
	}
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/review/singo")
	public ResponseEntity<?> singoReview(int rNo, Principal principal) {
		return ResponseEntity.ok(restService.singoReview(rNo, principal.getName()));
	}
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/review/delete")
	public ResponseEntity<?> deleteReview(int rNo){
		restService.deleteReview(rNo);
		return ResponseEntity.ok("/cheatingday/review/list");
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/review/ckupload")
	public ResponseEntity<?> ckUpload(MultipartFile upload) throws IOException{
		return ResponseEntity.ok(restService.saveCkImage(upload));
	}
}
