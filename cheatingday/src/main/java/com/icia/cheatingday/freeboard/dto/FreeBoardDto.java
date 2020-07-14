package com.icia.cheatingday.freeboard.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.freeboard.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class FreeBoardDto {
	private FreeBoardDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList{
		private Integer bno;
		private String username;
		private String title;
		private String category;
		private Integer cateno;
		private String writeTimeStr;
		private Integer readCnt;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForRead{
		private Integer bno;
		private String username;
		private String title;
		private String content;
		private String category;
		private Integer cateno;
		private String writeTimeStr;
		private Integer readCnt;
		private Integer goodCnt;
		private Integer badCnt;
		private List<Comment> comments;
		
	}
	@Data
	@Accessors(chain=true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DtoForWrite{
		@NotBlank
		@Pattern(regexp="^[\\w\\s가-힣!]{1,50}$", message ="제목은 영숫자와 한글, 특수문자 !만 사용할 수 있습니다")
		private String title;
		private String content;
		private String username;
		private Integer cateno;
		private String category;
		private List<MultipartFile> attachments;
	}
	
	@Data
	public static class DtoForUpdate{
		private Integer bno;
		private String title;
		private String content;
		private String username;
	}
}
