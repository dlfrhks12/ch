package com.icia.cheatingday.center;

import java.time.*;


import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class QnA {
	private Integer qNo;
	private String qUsername;
	private String qTitle;
	private String qContent;
	private LocalDateTime qWriteTime;
	private Boolean qIscomment;
	private Integer mNum;
	private Integer qCano;
}
