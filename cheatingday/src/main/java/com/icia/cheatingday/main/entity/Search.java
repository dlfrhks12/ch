package com.icia.cheatingday.main.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Search {
	private String searchOption;
	private String keyword;
}
