package com.icia.cheatingday.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class MenuEntity {

	private Integer menuno;        //메뉴번호
	private int menusal;       //가격
	private String menusajin;  //사진
	private String menuname;   //이름
	private int sNum;       //음식점 고유번호
}