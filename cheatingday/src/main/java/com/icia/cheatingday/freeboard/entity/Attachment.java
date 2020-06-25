package com.icia.cheatingday.freeboard.entity;


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
public class Attachment {
	private int fno;
	private String writer;
	private String originalFileName;
	private String saveFileName;
	private int flength;
	private int bno;
	private boolean isImage;
}