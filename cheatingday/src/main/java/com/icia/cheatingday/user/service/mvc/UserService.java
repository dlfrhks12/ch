package com.icia.cheatingday.user.service.mvc;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.buylist.dao.*;
import com.icia.cheatingday.buylist.dto.*;
import com.icia.cheatingday.buylist.entity.*;
import com.icia.cheatingday.cart.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.dto.*;
import com.icia.cheatingday.review.entity.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.util.*;

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
	private MenuDao menuDao;
	@Autowired
	private OrdersDao orderDao;
	@Autowired
	private FavoriteDao favDao;
	@Autowired
	private FoodCategoryDao foodCategoryDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;
	


	// userDao에서 UPoint가져오기
	public int count(String uUsername) {
		int count = userDao.findById(uUsername).getUPoint();
		return count;
	}
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
		int countOfBoard = pointDao.count(uUsername);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Point> pointList = pointDao.findAll(srn, ern, uUsername);
		List<PointDto.DtoForList> dtoList = new ArrayList<>();
		for(Point point:pointList) {
			PointDto.DtoForList dto = modelMapper.map(point, PointDto.DtoForList.class);
			dto.setOOrderTimeStr(buylistDao.findById(uUsername).getCartDay().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSNum(buylistDao.findByOrderNo(point.getOrderNo()).getSNum());
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dtoList.add(dto);
		}
		page.setPlist(dtoList);
		return page;
	}
	// 리뷰 리스트 페이징
	public Page reviewList(int pageno, String uUsername) {
		int countOfBoard = reviewDao.count(uUsername);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> reviewList = reviewDao.findAllByUsername(srn, ern, uUsername);
		List<ReviewDto.DtoForList> dtoList = new ArrayList<>();
		for(Review review:reviewList) {
			ReviewDto.DtoForList dto = modelMapper.map(review, ReviewDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSName(storeDao.findBysNum(review.getSNum()).getSName());
			int foodNo = storeDao.findBysNum(review.getSNum()).getFoodNo();
			String categoryName = foodCategoryDao.findByFoodNo(foodNo);
			dto.setCategory(categoryName);
			dto.setMenuname(buylistDao.findByOrderNo(review.getOrderNo()).getCartName());
			int menuno = buylistDao.findByOrderNo(review.getOrderNo()).getMenuno();
			System.out.println(menuno);
			System.out.println(menuno);
			System.out.println(menuno);
			System.out.println(menuno);
			System.out.println(menuno);
			System.out.println(menuno);
			System.out.println(menuno);
			dto.setSajin(menuDao.findById(menuno).getMenusajin());
			dtoList.add(dto);
		}
		page.setRlist(dtoList);
		return page;
	}
	// 구매내역 리스트 페이징
	public Page buyList(int pageno, String uUsername) {
		int countOfBoard = buylistDao.count(uUsername);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Orders> orderlist = orderDao.findAllByusername(srn, ern, uUsername);
		List<BuylistDto.DtoForList> dtoList = new ArrayList<>();
		for(Orders order:orderlist) {
			BuylistDto.DtoForList dto = modelMapper.map(order, BuylistDto.DtoForList.class);
			dto.setCartDayStr(order.getCartDay().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dto.setFavCheck(favDao.findFavoriteById(dto.getUUsername(),dto.getSNum()));
			dtoList.add(dto);
		}
		page.setBlist(dtoList);
		return page;
	}
}