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
public class QnaEntity {

	private int qNo;                  //문의 글번호
	private String qUsername;         //아이디
	private String qTitle;            //제목
	private String qContent;          //내용
	private LocalDateTime qWriteTime; //작성시간
	private int qIsComment;           //답변유무
	private String mNum;              //사업자등록번호
	private int qCano;                //문의카테고리 번호
	
}