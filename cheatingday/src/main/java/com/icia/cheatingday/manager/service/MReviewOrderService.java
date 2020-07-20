package com.icia.cheatingday.manager.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.dao.ReviewCheckDao;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.order.dao.OrderDao;
import com.icia.cheatingday.order.entity.DetailorderEntity;
import com.icia.cheatingday.order.entity.OrderEntity;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class MReviewOrderService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private ReviewCheckDao reviewCheckDao;
	
	@Autowired
	private OrderDao orderDao;
	
	///////////////////////////////////////////////////리뷰////////////////////////////////////////////////////////
	//사업자 리뷰 신고--신고는 한번만 할수있게 막아.
	public int reviewSingoUpdate(int rNo, String username) {
		Review review = reviewDao.findByRno(rNo);
		System.out.println(review);
		
		if(reviewCheckDao.alreadyExist(username,rNo)!=null) { //username이 존재한다면
			int result = review.getRReport();  //결과에 신고를 불러와.
			return result;
		}
			//username이 존재하지 않다면
		reviewCheckDao.insert(username,rNo);
			//신고수를 1개 늘려
		return reviewDao.reviewSingoUpdate(rNo);
	}
	
	
	//sNum으로 리뷰리스트 페이징
	public Page list (int pageno, String mUsername) {
		int sNum = storeDao.findBymUsername(mUsername).getSNum();
		
		int countOfBoard = reviewDao.countBySnum(sNum);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		
		List<Review> reviewList = null;
			reviewList = reviewDao.findAllBysNum(srn, ern, sNum);
		
		List<ManagerDto.DtoForList> dtoList = new ArrayList<>();
		for(Review review:reviewList) {
			ManagerDto.DtoForList dto = modelMapper.map(review, ManagerDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtoList.add(dto);
		}
		page.setMList(dtoList);
		return page;
		
	}
	
	//음식점 리뷰읽기
	public ManagerDto.DtoForReviewRead read(int rNo) {
		
		Review review = reviewDao.findByRno(rNo);
		ManagerDto.DtoForReviewRead dto = modelMapper.map(review, ManagerDto.DtoForReviewRead.class);
		dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		return dto;
		
	}
//////////////////////////////////////////////////주문/////////////////////////////////////////////////////
	
	
	 // 해당매장 주문 리스트 - 페이징 
	public Page orderList (int pageno, int sNum) {
		
		int countOfBoard = orderDao.countBysNum(sNum);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		
		List<OrderEntity> orderList = orderDao.orderListBySNum(srn, ern,sNum);
			
		List<ManagerDto.DtoForOrderList> dtoList = new ArrayList<>();
		for(OrderEntity order: orderList) {
			ManagerDto.DtoForOrderList dto = modelMapper.map(order, ManagerDto.DtoForOrderList.class);	
			dto.setOOrderTimeStr(order.getOOrderTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일")));
			
			int ono = dto.getONo(); 
			int count = orderDao.countByOno(ono);
			if(count>1) {
				dto.setOrderName(orderDao.ordercheck(ono).getDMenuName()+"외 총"+count+"개");
			}
			
			dtoList.add(dto);
		}
		page.setOlist(dtoList);
		return page;
	}
	  
	 
	
	// 해당 주문번호 내용 읽기 
	public List<ManagerDto.DtoForOrderRead> orderRead(int oNo){
		
		List<DetailorderEntity> list = orderDao.orderReadByONo(oNo);
		
		List<ManagerDto.DtoForOrderRead> dtoList = new ArrayList<>();
		for(DetailorderEntity detailOrder:list) {
			ManagerDto.DtoForOrderRead dto = modelMapper.map(detailOrder,ManagerDto.DtoForOrderRead.class);
			dto.setDMenuName(menuDao.findBymenuno(dto.getMenuno()).getMenuname());//메뉴번호로 메뉴이름을 불러온다.
			dtoList.add(dto);
		}
		return dtoList;
	}
}
