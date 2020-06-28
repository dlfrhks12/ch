package com.icia.cheatingday.user.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Favorite {
	private String uUsername;
	private int sNum;
}
