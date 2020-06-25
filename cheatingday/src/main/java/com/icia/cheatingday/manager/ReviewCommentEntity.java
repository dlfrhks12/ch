package com.icia.cheatingday.manager;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class ReviewCommentEntity {

	private int rcNo;                //리뷰답글번호
	private int rNo;                 //리뷰번호
	private String mNum;             //사업자 등록번호
	private String rcContent;        //답글내용
	private LocalDateTime rcDateTime;//답글시간
}
