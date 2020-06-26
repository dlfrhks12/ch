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
	private int bno;
	private String title;
	private String content;
	private LocalDateTime writeTime;
	private int readCnt;
	private int goodCnt;
	private int badCnt;
	private int cateno;
	private int commentCnt;
	private int attachementCnt;
	private int deleteCommentCnt;
	private int fno;
	private int cno;
	private String username;
}


