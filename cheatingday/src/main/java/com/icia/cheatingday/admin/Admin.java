package com.icia.cheatingday.admin;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Admin {
	private String aUsername;
	private String aPassword;
	private String aIrum;
}
