package com.icia.cheatingday.center.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.icia.cheatingday.center.entity.QnAComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class QnADto {
	private QnADto() {}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain = true)
	public static class DtoForList{
		private int qNo;
		private int qCano;
		private String qTitle;
		private String qWriteTimeStr;
		private boolean qIscomment;
		private String mIrum;
		private long mNum;
		private String qCategory;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead {
		private int qNo;
		private String qTitle;
		private String qContent;
		private String qWriteTimeStr;
		private String mUsername;
		private String mIrum;
		private long mNum;
		private int qCano;
		private String qCategory;
		private List<QnAComment> comments;
	}
	@Data
	@Accessors(chain=true)
	public static class DtoForWrite {
		private String qTitle;
		private String qContent;
		private long mNum;
		private String mUsername;
		private int qCano;
	}
	@Data
	@Accessors(chain=true)
	public static class DtoForUpdate {
		private int qNo;
		@NotBlank
		@Pattern(regexp="^[\\w\\s가-힣!]{1,50}$", message ="제목은 영숫자와 한글, 특수문자 !만 사용할 수 있습니다")
		private String qTitle;
		private String qContent;
		private int qCano;
		private String mUsername;
	}

}
