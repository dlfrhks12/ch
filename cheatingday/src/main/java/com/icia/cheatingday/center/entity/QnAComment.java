package com.icia.cheatingday.center.entity;

import java.time.*;
import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class QnAComment {
	private Integer qcNo;
	private Integer qNo;
	private String aUsername;
	private String qcContent;
	private LocalDateTime qcWriteTime;
}
