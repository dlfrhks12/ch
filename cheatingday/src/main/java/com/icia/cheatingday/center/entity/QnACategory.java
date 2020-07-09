package com.icia.cheatingday.center.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class QnACategory {
	private Integer qcano;
	private String qcategory;
}
