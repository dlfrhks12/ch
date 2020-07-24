package com.icia.cheatingday.review.controller.rest;

import java.io.IOException;
import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/review/read")
	public ResponseEntity<?> read(@RequestParam @NotNull Integer rNo, Principal principal){
		String username = principal!=null? principal.getName():null;
		
		ReviewDto.DtoForRead dto = service.read(rNo, username);
		
		return ResponseEntity.ok(dto);
	}
	@PostMapping("/reviewComment/write")
	public ResponseEntity<?> writeComment(@Valid ReviewComment comment, BindingResult bindingResult, Principal principal){
		return ResponseEntity.ok(restService.writeComment(comment, principal.getName()));
	}
	@PatchMapping("/reviewComment/update")
	public ResponseEntity<?> updateCommnet(@Valid ReviewComment comment){
		return ResponseEntity.ok(restService.updateComment(comment));
	}
	@DeleteMapping("/reviewComment/delete")
	public ResponseEntity<?> deleteComment(int rcNo, int rNo, String uUsername){
		return ResponseEntity.ok(restService.deleteComment(rcNo, rNo, uUsername));
	}
	@PatchMapping("/review/update")
	public ResponseEntity<Void> updateReview(@Valid ReviewDto.DtoForUpdate dto, BindingResult bindingResult, Principal principal) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUUsername(principal.getName());
		restService.updateReview(dto);
		return ResponseEntity.ok(null);
	}
	@DeleteMapping("/review/delete")
	public ResponseEntity<?> deleteReview(int rNo){
		restService.deleteReview(rNo);
		return ResponseEntity.ok("/cheatingday/review/list");
	}
	@PostMapping("/review/ckupload")
	public ResponseEntity<?> ckUpload(MultipartFile upload) throws IOException{
		return ResponseEntity.ok(restService.saveCkImage(upload));
	}
}
