package com.icia.cheatingday.common.dto;


import java.util.*;

import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.user.dto.*;

import lombok.*;

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
	List<UserDto.DtoForList> ulist;
}
