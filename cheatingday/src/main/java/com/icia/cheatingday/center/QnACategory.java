package com.icia.cheatingday.center;

import com.icia.cheatingday.admin.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class QnACategory {
	private Integer qCano;
	private String qCategory;
}
