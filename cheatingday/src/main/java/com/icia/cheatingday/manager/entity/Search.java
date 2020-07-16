package com.icia.cheatingday.manager.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Search {
	private String searchOption;	// 검색옵션
	private String keyword;			// 키워드
}
