package com.icia.cheatingday.admin.entity;

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
	private Boolean aEnabled;
}
