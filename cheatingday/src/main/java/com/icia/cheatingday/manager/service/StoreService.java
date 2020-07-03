package com.icia.cheatingday.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.dao.FoodCategoryDao;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.dto.StoreDto;
import com.icia.cheatingday.manager.entity.Store;

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
	
	// 가게리스트 
	public List<Store> storeList(){ 
		List<Store> result = new ArrayList<Store>();
		List<Store> list = dao.findAll();
			for(Store store:list) {
				result.add(store);}
				return result;
	}
		
	// 가게읽기
	public StoreDto storeRead(int sNum){
		Store store = dao.findBysNum(sNum);
		StoreDto dto = modelMapper.map(store, StoreDto.class);
		return dto;
	} 
	
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
		return dao.delete(sNum); 
		}

}
