package com.icia.cheatingday.common.dto;


import java.util.List;

import com.icia.cheatingday.notice.dto.NoticeDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page {
	private int startRowNum;
	private int endRowNum;
	private int pageno;
	private int startPage;
	private int endPage;
	private boolean isPrev;
	private boolean isNext;
	List<NoticeDto.DtoForList> nlist;
}
