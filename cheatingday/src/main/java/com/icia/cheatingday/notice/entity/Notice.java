package com.icia.cheatingday.notice.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Notice {
	private Integer nNo;
	private String nTitle;
	private LocalDateTime nWriteTime;
	private Integer nReadCnt;
	private String content;
	private String aUsername;
}
