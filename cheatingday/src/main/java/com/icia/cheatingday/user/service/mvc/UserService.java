package com.icia.cheatingday.user.service.mvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.buylist.dao.BuylistDao;
import com.icia.cheatingday.buylist.dto.BuylistDto;
import com.icia.cheatingday.buylist.entity.Buylist;
import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.exception.JobFailException;
import com.icia.cheatingday.exception.UserNotFoundException;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.user.dao.FavoriteDao;
import com.icia.cheatingday.user.dao.PointDao;
import com.icia.cheatingday.user.dao.UserDao;
import com.icia.cheatingday.user.dto.PointDto;
import com.icia.cheatingday.user.dto.UserDto;
import com.icia.cheatingday.user.entity.Point;
import com.icia.cheatingday.user.entity.User;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PointDao pointDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private BuylistDao buylistDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private FavoriteDao favDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;
	


	// 내정보 읽기
	public UserDto.DtoForRead myPage(String uUsername) {
		User user = userDao.findById(uUsername);
		//if(user==null)
		//	throw new UserNotFoundException();
		UserDto.DtoForRead dto = modelMapper.map(user, UserDto.DtoForRead.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		dto.setUJoinDateStr(user.getUJoinDate().format(dtf));
		LocalDate joinDate = user.getUJoinDate().toLocalDate();
		LocalDate today = LocalDate.now();
		long days = ChronoUnit.DAYS.between(joinDate, today);
		dto.setDays(days);
		return dto;
	}
	// 비밀번호 확인
	public void checkPwd(String uPassword, String uUsername) {
		User user = userDao.findById(uUsername);
		System.out.println(user);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		boolean result = pwdEncoder.matches(uPassword, encodedPassword);
		if(result==false)
			throw new JobFailException("비밀번호가 틀렸습니다");
	}
	// 비밀번호 변경	
	public void changePwd(String uPassword, String newUPassword, String UUsername) {
		User user = userDao.findById(UUsername);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		if(pwdEncoder.matches(uPassword, encodedPassword)==true) {
			String newEncodedPassword = pwdEncoder.encode(newUPassword);
			userDao.update(User.builder().uPassword(newEncodedPassword).uUsername(UUsername).build());
		}
		else 
			throw new JobFailException("잘못된 비밀번호입니다.");
	}
	// 포인트 리스트 페이징
	public Page pointList(int pageno, String uUsername) {
		int countOfBoard = pointDao.count();
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Point> pointList = pointDao.findAll(srn, ern);
		List<PointDto.DtoForList> dtoList = new ArrayList<>();
		for(Point point:pointList) {
			PointDto.DtoForList dto = modelMapper.map(point, PointDto.DtoForList.class);
			dto.setOOrderTimeStr(buylistDao.findById(uUsername).getOOrderTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSNum(buylistDao.findONoById(point.getONo()).getSNum());
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dtoList.add(dto);
		}
		page.setPlist(dtoList);
		return page;
	}
	// 리뷰 리스트 페이징
	public Page reviewList(int pageno, Integer rNo) {
		int countOfBoard = reviewDao.count(rNo);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> reviewList = reviewDao.findAll(srn, ern);
		List<ReviewDto.DtoForList> dtoList = new ArrayList<>();
		for(Review review:reviewList) {
			ReviewDto.DtoForList dto = modelMapper.map(review, ReviewDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dtoList.add(dto);
		}
		page.setRlist(dtoList);
		return page;
	}
	// 구매내역 리스트 페이징
	public Page buyList(int pageno, String uUsername) {
		int countOfBoard = buylistDao.count();
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Buylist> buyList = buylistDao.findAll(srn, ern, uUsername);
		List<BuylistDto.DtoForList> dtoList = new ArrayList<>();
		for(Buylist buylist:buyList) {
			BuylistDto.DtoForList dto = modelMapper.map(buylist, BuylistDto.DtoForList.class);
			dto.setOOrderTimeStr(buylist.getOOrderTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dto.setFavCheck(favDao.findFavoriteById(dto.getUUSername(),dto.getSNum()));
			dtoList.add(dto);
		}
		page.setBlist(dtoList);
		return page;
	}
}