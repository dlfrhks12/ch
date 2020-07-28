package com.icia.cheatingday.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.dao.FoodCategoryDao;
import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.dto.StoreDto;
import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.exception.sNumExistsException;

@Service
public class StoreService {
	@Autowired
	private StoreDao dao;
	@Value("c:/upload/storesajin")
	private String storeFolder;
	@Value("http://localhost:8081/storesajin/")
	private String storePath;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private FoodCategoryDao foodCategoryDao;
	@Autowired
	private MenuDao menuDao;

	
	// 가게등록 
		public void storeInsert(Store store, MultipartFile sajin) throws IllegalStateException, IOException { 
			if(sajin!=null && sajin.isEmpty()==false) { 	
				if (sajin.getContentType().toLowerCase().startsWith("image/") == true) {
				//사진이 존재하면서 사진형식이면
					int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.'); 
					String extension = sajin.getOriginalFilename().substring(lastIndexOfDot+1); 
					File profile = new File(storeFolder, store.getSName()+"."+extension);
		  
					sajin.transferTo(profile); 
					store.setSSajin(storePath+profile.getName());
				} else {
					//메뉴사진이라고 올린 파일이 사진이 아닌 경우
					store.setSSajin(storePath+"storebasic.jpg"); 
			} 
				} else {//메뉴사진을 안올린 경우
				store.setSSajin(storePath+"storebasic.jpg"); 
				} 
			dao.insert(store);
			
		}
		
		// 가게읽기 - 사업자
		public StoreDto.DtoForRead storeRead(int sNum, String username){
			Store store = dao.findBysNum(sNum);
			StoreDto.DtoForRead dto = modelMapper.map(store, StoreDto.DtoForRead.class);
			//로그인한사람이랑 글쓴사람이랑 같아야헤.
		
			dto.setFoodCategory(foodCategoryDao.findByFoodNo(dto.getFoodNo()));
			return dto;
		} 
		
		
	
		
		// 가게수정 
		public int storeUpdate(Store store, MultipartFile sajin) throws IllegalStateException,IOException{
			if(sajin!=null&&!sajin.isEmpty()){
			if (sajin.getContentType().toLowerCase().startsWith("image/") == true) {
				int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
				String extension = sajin.getOriginalFilename().substring(lastIndexOfDot + 1);
				File file = new File(storeFolder, store.getSName() + "." + extension);
				sajin.transferTo(file);
				store.setSSajin(storePath + file.getName());
				}
			}return dao.update(store);
		}
		
		
		// 가게삭제 
		public int storeDelete(int sNum) {
			menuDao.deleteBySnum(sNum);
			return dao.delete(sNum); 
		}
		
		//매장이 존재하는지 확인
		public Boolean existsSnum(String mUsername) {
			return dao.existsSnum(mUsername);
		}
		
		//(매장이 존재하고)해당매장리뷰가 존재하는지 확인
		public boolean existsreview(String mUsername) {
			Store store = dao.findBymUsername(mUsername);
			Integer count = store.getSReviewCnt(); //리뷰갯수를 가져와서
				if(count==0)
					return false;
				else    
					return true;
		}
		
		
	/*//내 매장 리뷰갯수 읽기
	public int myReviewCnt(int sNum) {
		return dao.myReviewCnt(sNum);
	}
	*/

		
	
	
	// 가게리스트 해당하는 사장님만 자신의 가게 리스트를 볼 수 있어 
	public List<Store> storeList(String mUsername){ 
		
		System.out.println("==============");
		System.out.println(dao.findAllBymUsername(mUsername));
		System.out.println("--------------");
		//List<Store> result = new ArrayList<Store>();
		List<Store> list = dao.findAllBymUsername(mUsername);
		return list;
	}

	
	// 주문을 위한 가게읽기 - 모든회원
	public StoreDto.DtoForRead orderStoreRead(int sNum) {
		Store store = dao.findBysNum(sNum);
		StoreDto.DtoForRead dto = modelMapper.map(store, StoreDto.DtoForRead.class);
		dto.setFoodCategory(foodCategoryDao.findByFoodNo(dto.getFoodNo()));
		return dto;
		
	}
	
	
	// 가게수정 
	public int storeUpdate(StoreDto.DtoForUpdate dto, MultipartFile sajin) throws IllegalStateException,IOException{
		Store store = dao.findBysNum(dto.getSNum());
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		System.out.println(store);
		store = modelMapper.map(dto, Store.class);
		if(sajin!=null&&!sajin.isEmpty()){
		if (sajin.getContentType().toLowerCase().startsWith("image/") == true) {
			int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
			String extension = sajin.getOriginalFilename().substring(lastIndexOfDot + 1);
			File file = new File(storeFolder, store.getSName() + "." + extension);
			sajin.transferTo(file);
			store.setSSajin(storePath + file.getName());
			}
		System.out.println(store);
		}return dao.update(store);
	}




}
