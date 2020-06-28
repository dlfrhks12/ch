package com.icia.cheatingday.admin.entity;


import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class InsertStore {
	private Integer iNo;
	private String sName;
	private String mNum;
	private Boolean iCheck;
	private String aUsername;
}
