package com.icia.cheatingday.common.dto;


import java.util.List;

import com.icia.cheatingday.admin.dto.AdminDto;
import com.icia.cheatingday.center.dto.QnADto;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.notice.dto.NoticeDto;
import com.icia.cheatingday.user.dto.PointDto;
import com.icia.cheatingday.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	List<PointDto.DtoForList> plist;
	List<QnADto.DtoForList> qlist;
	List<FreeBoardDto.DtoForList> freelist;
	List<AdminDto.DtoForList> alist;
}
