package com.icia.cheatingday.freeboard.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.freeboard.entity.Attachment;
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
	public static class DtoForLIst{
		private int bno;
		private String username;
		private String title;
		private String category;
		private String writeTimeStr;
		private int readCnt;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForeRead{
		private int bno;
		private String username;
		private String title;
		private String category;
		private String writeTimeStr;
		private int readCnt;
		private int goodCnt;
		private int badCnt;
		private List<Comment> comment;
		private List<Attachment> attachment;
		
	}
	@Data
	@Accessors(chain=true)
	public static class DtoForWrite{
		@NotBlank
		@Pattern(regexp="^[\\w\\s가-힣!]{1,50}$", message ="제목은 영숫자와 한글, 특수문자 !만 사용할 수 있습니다")
		private String title;
		private String content;
		private String username;
		private List<MultipartFile> attachment;
	}
	
	@Data
	public static class DtoForUpdate{
		private int bno;
		private String title;
		private String content;
		private String username;
	}
}
