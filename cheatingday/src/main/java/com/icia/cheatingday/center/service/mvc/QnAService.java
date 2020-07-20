package com.icia.cheatingday.center.service.mvc;

import java.time.format.*;
import java.util.*;

import javax.annotation.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.util.*;

import lombok.*;

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
	@Getter
	private List<QnACategory> Qcano;
	
	//카테고리 작성시 출력용
	@PostConstruct
	public void init() {
		Qcano = qcdao.findAll();
	}
	//[사업자]QNA작성
	public int write(QnADto.DtoForWrite dto, String musername) {
		dto.setMNum(mdao.findById(musername).getMNum());
		QnA qna = mapper.map(dto, QnA.class);
		qdao.insert(qna);		
		return qna.getQNo();
	}
	//[관리자,사업자]QNA리스트 카테고리 유무 판단후 출력
	public Page list(int pageno, Integer qCano) {
		int countOfBoard = qdao.count(qCano);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<QnA> qnalist = null;
		if(qCano!=null)
			qnalist = qdao.findAllByqCano(srn, ern, qCano);
		else
			qnalist = qdao.findAll(srn, ern);
		List<QnADto.DtoForList> dtolist = new ArrayList<>();
		for(QnA qna:qnalist) {
			QnADto.DtoForList dto = mapper.map(qna, QnADto.DtoForList.class);
			dto.setQWriteTimeStr(qna.getQWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setMIrum(mdao.findMusernameByMnum(dto.getMNum()));
			dto.setQCategory(qcdao.findById(dto.getQCano()));
			dtolist.add(dto);
		}
		page.setQlist(dtolist);
		return page;
	}

}