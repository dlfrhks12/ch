package com.icia.cheatingday.user.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Favorite {
	private String uUsername;	// 아이디
	private int sNum;			// 음식점 고유번호
}
