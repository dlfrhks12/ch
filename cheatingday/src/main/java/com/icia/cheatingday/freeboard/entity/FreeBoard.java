package com.icia.cheatingday.freeboard.entity;

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
public class FreeBoard {
	private Integer bno;
	private String title;
	private String content;
	private LocalDateTime writeTime;
	private Integer readCnt;
	private Integer goodCnt;
	private Integer badCnt;
	private Integer cateno;
	private Integer commentCnt;
	private Integer attachmentCnt;
	private Integer deleteCommentCnt;
	private Integer fno;
	private Integer cno;
	private String username;
}
//ass

