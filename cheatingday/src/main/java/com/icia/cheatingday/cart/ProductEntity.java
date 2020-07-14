package com.icia.cheatingday.cart;

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
public class ProductEntity {
//	m_no NUMBER(10) NOT NULL, /* 메뉴번호 */
//	m_name NVARCHAR2(30) NOT NULL, /* 이름 */
//	m_price NUMBER(10) NOT NULL, /* 가격 */
//	m_count NUMBER(10), /* 수량 */
//	image VARCHAR2(100), /* 사진 */
//	m_stock NUMBER(10) /* 재고 */
	private int mNo;
	private String mName;
	private int mPrice;
	private int mCount;
	private String image;
}
