package com.icia.cheatingday.center.dto;

import java.util.*;

import javax.validation.constraints.*;

import com.icia.cheatingday.center.entity.*;

import lombok.*;
import lombok.experimental.*;

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
		private Boolean qIscomment;
		private String mIrum;
		private int mNum;
		private String qCategory;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead {
		private int qNo;
		private String qTitle;
		private String qContent;
		private String qWriteTimeStr;
		private Boolean qIscomment;
		private int mNum;
		private String mIrum;
		private int qCano;
		private String qCategory;
		private List<QnAComment> comments;
	}
	@Data
	@Accessors(chain=true)
	public static class DtoForWrite {
		private String qTitle;
		private String qContent;
		private int mNum;
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
		private int mNum;
	}

}
