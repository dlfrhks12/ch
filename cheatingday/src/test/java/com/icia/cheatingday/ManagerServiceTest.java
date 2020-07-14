package com.icia.cheatingday;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ManagerServiceTest {

	@Autowired
	private StoreDao sd;
	@Autowired
	private StoreService sr;
	private Object Review;
	
	@Test
	public void ss() {
		System.out.println(sr.orderStoreRead(41));
	}
	
	
	//@Test
	public void page() {
		
		//for(int i=0; i<123; i++) 
		//	Review review = Review.builder().rNo(1).rContent("내용들").rStarPoint(3).rWriteTime(LocalDateTime.now())
			//		.oNo(33).rTitle("제목이다").rReport(0).uUsername("닉네임").sNum(28).build();
		
		
		}
		
	
	
	//@Test
	public void sss() {
	//	System.out.println(sd.myReviewCnt(40));
	}
	
	//@Test
	public void sa() {
		//System.out.println(sr.myReviewCnt(40));
	}
	
	/*
	@Test
	public void myinforeadTest() {
	//	assertThat(service.read(111111113), is(notNullValue()));
		//System.out.println(service.read(111111113));
	//	assertThat(service.read(111111113), is(notNullValue()));
	//	System.out.println(service.read(111111113));
		assertThat(service.read("11111"), is(notNullValue()));
		System.out.println(service.read("11111"));
		
	}
	*/
	//@Test
	public void asadasfasf() {
		System.out.println(sd.findBymUsername("yyg12345"));
	}
	//@Test
	public void asfasf() {
		System.out.println(sr.existsreview("yyg12345"));
	}
}
