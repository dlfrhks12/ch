package com.icia.cheatingday.freeboard.controller.rest;

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

import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Comment;
import com.icia.cheatingday.freeboard.service.FreeBoardService;
import com.icia.cheatingday.freeboard.service.rest.FreeBoardRestService;
import com.sun.mail.iap.Response;

@RestController
public class FreeBoardRestController {
	@Autowired
	private FreeBoardService service;
	@Autowired
	private FreeBoardRestService restService;
	@PostMapping("/board/read")
	public ResponseEntity<?> read(@RequestParam @NotNull int bno, Principal principal){
		String username = principal!=null? principal.getName():null;
		FreeBoardDto.DtoForRead dto = service.read(bno, username);
		return ResponseEntity.ok(dto);
	}
	@PostMapping("/comment/write")
	public ResponseEntity<?> writeComment(@Valid Comment comment, BindingResult bindingResult, Principal principal){
		comment.setWriter(principal.getName());
		return ResponseEntity.ok(restService.writeComment(comment));
	}
	@PatchMapping("/comment/update")
	public ResponseEntity<?>updateComment(@Valid Comment comment){
		return ResponseEntity.ok(restService.updateComment(comment));
	}
	@DeleteMapping("/comment/delete")
	public ResponseEntity<?>deleteComment(int cno, int bno, String username){
		return ResponseEntity.ok(restService.deleteComment(cno, bno, username));
	}
	@PatchMapping("/board/update")
	public ResponseEntity<Void> updateBoard(@Valid FreeBoardDto.DtoForUpdate dto, BindingResult bindingResult, Principal principal) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUsername(principal.getName());
		restService.updateBoard(dto);
		return ResponseEntity.ok(null);
	}
	@DeleteMapping("/board/delete")
	public ResponseEntity<?> deleteBoard(int bno, Principal principal){
		restService.deleteBoard(bno, principal.getName());
		return ResponseEntity.ok("/freeboard/board/list");
	}
	@PostMapping("/board/ckupload")
	public ResponseEntity<?> ckUpload(MultipartFile upload){
		return ResponseEntity.ok(restService.saveCkImage(upload));
	}
	@DeleteMapping("/attachment/delete")
	public ResponseEntity<?> deleteAttachment(int fno, int bno,Principal principal){
		return ResponseEntity.ok(restService.deleteAttachment(fno, bno, principal.getName()));
		//aa
	}
}
