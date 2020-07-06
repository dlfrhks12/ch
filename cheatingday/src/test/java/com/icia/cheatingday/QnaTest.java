package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import javax.inject.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.dto.QnADto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.center.service.mvc.*;
import com.icia.cheatingday.center.service.rest.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class QnaTest {
	
		@Inject
		private QnADao dao;
		@Inject 
		private QnACommentDao cdao;
		@Inject
		private QnACategoryDao qcdao;
		@Inject
		private QnAService qs;
		@Inject
		private QnARestService qrs;
		
		//@Test
		public void intT() {
			QnA qna = QnA.builder().qTitle("질뮨이요").qContent("항복").qWriteTime(LocalDateTime.now()).qIscomment(false).mNum("2315615").qCano(1).build();
			dao.insert(qna);
			System.out.println(qna);
		}
		//@Test
		public void cout() {
			System.out.println(dao.count(1));
		}
		//@Test
		public void afwe() {
			Page page = PagingUtil.getPage(2, 2);
			List<QnA> list = dao.findAllByqCano(page.getStartRowNum(), page.getEndRowNum(), 1);
			System.out.println(list);
		}
		//@Test
		public void geqageq() {
			System.out.println(dao.findById(3));
		}
		//@Test
		public void wqfqwf() {
			QnA a = QnA.builder().qNo(3).qCano(3).build();
			System.out.println(dao.update(a));
		}
		
		//@Test
		public void egegwe() {
			System.out.println(qcdao.findAll());
		}
		//@Test
		public void eqwgwe() {
			System.out.println(qcdao.findById(1));
		}
		//@Test
		public void iegtem()	{
			QnAComment a = QnAComment.builder().qNo(1).aUsername("sege").qcContent("whrhrh").qcWriteTime(LocalDateTime.now()).build();
			cdao.insert(a);
			System.out.println(a);
		}
		
		
		//@Test
		public void emmff() {
			System.out.println(cdao.findAllByQno(1));
		}
		
		//@Test
		public void  hge() {
			System.out.println(cdao.findById(2));
		}
		
		//@Test
		public void gjjt()	{
			QnAComment a = QnAComment.builder().qcContent("예제").qcNo(2).build();
			cdao.update(a);
			assertThat(cdao.update(a), is(1));
		}
		//@Test
		public void agte() {
			assertThat(cdao.delete(3), is(1));
		}
		//@Test
		public void qwrh() {
			System.out.println(qs.list(2, 1));
		}
		//@Test
		public void qwhnn() {
			System.out.println(qrs.read(1, "usy1473"));
		}
		//@Test
		public void erhtng() {
			QnADto.DtoForWrite dto = new DtoForWrite();
			//dto.setMNum(121221);
			dto.setMUsername("spring");
			dto.setQContent("qwgehw");
			dto.setQTitle("vvh");
			dto.setQCano(1);
			System.out.println(qs.write(dto));
		}
		//@Test
		public void fgehrnrg() {
			QnAComment qComment = QnAComment.builder().qNo(5).aUsername("usy1413").qcContent("대답").build();
			qrs.writeQComment(qComment);		
		}
		//@Test
		public void fdbv() {
			QnAComment aComment = QnAComment.builder().qcNo(25).qcContent("asfgehwhw").build();
			qrs.updateQnAcomment(aComment);
		}
		//@Test
		public void dhrn() {
			qrs.deleteComment(5, 27, null);
		}
		//@Test
		public void aegte() {
			QnADto.DtoForUpdate dto = new DtoForUpdate();
			dto.setQNo(14);
			dto.setMUsername("dkdkdk");
			dto.setQCano(2);
			dto.setQContent("아야아야아야");
			dto.setQTitle("gegege");
			qrs.updateQnA(dto);
		}
		//@Test
		public void dqw() {
			qrs.deletQna(15, null);
		}
		
}
