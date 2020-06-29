package com.icia.cheatingday.common.dto;


import java.util.*;

import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.dto.QnADto.*;
import com.icia.cheatingday.freeboard.dto.*;
import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.user.dto.*;

//github.com/tjddnjs5092/CheatingDay.git

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
	List<QnADto.DtoForList> qlist;
	List<UserDto.DtoForList> ulist;
	List<FreeBoardDto.DtoForList> freelist;
}
