package com.icia.cheatingday.manager;

import java.time.LocalDateTime;

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
public class PayBillEntity {
	
	private String mNum;    //사업자 등록번호
	private int pNo;        //고지서번호
	private int pMsal;      //월매출액
	private int pSal;       //납부금액
	private LocalDateTime pDay; //납부일
	private int pCheck;     //납입여부
	
}