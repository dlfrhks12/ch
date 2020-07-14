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
public class Comment {
	private Integer cno;
	private Integer bno;
	private String writer;
	private String content;
	private LocalDateTime writeTime;

}