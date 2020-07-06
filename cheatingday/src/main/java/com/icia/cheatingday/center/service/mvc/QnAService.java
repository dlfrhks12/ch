package com.icia.cheatingday.center.service.mvc;

import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.util.*;

@Service
public class QnAService {
	@Autowired
	private QnADao qdao;
	@Autowired
	private QnACategoryDao qcdao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ManagerDao mdao;
	
	public int write(QnADto.DtoForWrite dto) {
		QnA qna = mapper.map(dto, QnA.class);
		qdao.insert(qna);
		return qna.getQNo();
	}
	public Page list(int pageno, int qCano) {
		int countOfBoard = qdao.count(qCano);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		
		List<QnA> qnalist = null;
		qnalist = qdao.findAllByqCano(srn, ern, qCano);
		
		List<QnADto.DtoForList> dtolist = new ArrayList<>();
		for(QnA qna:qnalist) {
			QnADto.DtoForList dto = mapper.map(qna, QnADto.DtoForList.class);
			dto.setQWriteTimeStr(qna.getQWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setMIrum(mdao.findById(dto.getMUsername()).getMIrum());
			dto.setQCategory(qcdao.findById(dto.getQCano()));
			dtolist.add(dto);
		}
		page.setQlist(dtolist);
		return page;
	}
}