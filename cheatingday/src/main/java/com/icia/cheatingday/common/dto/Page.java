package com.icia.cheatingday.common.dto;


import java.util.*;

import com.icia.cheatingday.admin.dto.*;
import com.icia.cheatingday.buylist.dto.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.freeboard.dto.*;
import com.icia.cheatingday.main.dto.*;
import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.ManagerDto.DtoForList;
import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.review.dto.*;
import com.icia.cheatingday.user.dto.*;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
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
	List<ReviewDto.DtoForList> rlist;
	List<BuylistDto.DtoForList> blist;
	List<ManagerDto.DtoForList> mList;
	List<AdminDto.DtoForuserList> aulist;
	List<AdminDto.DtoForblockList> ablist;
	List<ManagerDto.DtoForOrderList> olist;
	List<MainDto.DtoForList> mainlist;
	List<ReviewDto.DtoForReviewList>reviewlist;
}
