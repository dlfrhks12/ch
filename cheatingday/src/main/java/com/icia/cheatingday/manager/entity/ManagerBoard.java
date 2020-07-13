package com.icia.cheatingday.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Builder
public class ManagerBoard {
	
	//사업자회원 신고 1번만 할 수 있게 하려고 만든 테이블
	private String username;
	private int rNo;

}
