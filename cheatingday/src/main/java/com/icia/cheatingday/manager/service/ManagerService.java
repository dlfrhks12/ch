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

import com.icia.cheatingday.exception.UserNotFoundException;
import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.manager.dao.ManagerStoreApplyInsertDao;
import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.MenuDto;
import com.icia.cheatingday.manager.entity.ManagerEntity;
import com.icia.cheatingday.manager.entity.MenuEntity;

@Service
public class ManagerService {
	// 메뉴
	@Autowired
	private MenuDao dao;
	@Value("c:/upload/menusajin")
	private String menuFolder;
	@Value("http://localhost:8081/menusajin/")
	private String menuPath;
	// 입점신청
	@Autowired
	private ManagerStoreApplyInsertDao storeApplyInsertDao;
	@Autowired
	private ModelMapper modelMapper;
	// 내정보 수정
	@Autowired
	private ManagerDao managerDao;
	
	///////////////////////////메뉴/////////////////////////////////////
	// 메뉴리스트
	public List<MenuEntity> menuList() {
		List<MenuEntity> result = new ArrayList<MenuEntity>();
		List<MenuEntity> list = dao.findAll();
		for (MenuEntity menuEntity : list) {
			result.add(menuEntity);
		}
		return result;
	}

	//메뉴 읽기
	public MenuEntity menuRead(Integer menuno) {
		System.out.println("-----------");
		System.out.println(dao.findById(menuno));
		MenuEntity menu = dao.findById(menuno);
		
		
		System.out.println(menu);
		return menu;
		
	}
	
	// 메뉴쓰기 - 메뉴사진 올리는 기능
	public void write(MenuDto.DtoForRead dto, MultipartFile sajin) throws IllegalStateException, IOException {
		MenuEntity menu = modelMapper.map(dto, MenuEntity.class);
		
		if (sajin != null && sajin.isEmpty() == false) {// 사진이 존재하면서 사진형식이면
			if (sajin.getContentType().toLowerCase().startsWith("image/") == true) {
				// 사진이 맞으면
				int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
				String extension = sajin.getOriginalFilename().substring(lastIndexOfDot + 1);
				File profile = new File(menuFolder, menu.getMenuname() + "." + extension);

				sajin.transferTo(profile);
				menu.setMenusajin(menuPath + profile.getName());
			} else {
				// 메뉴사진이라고 올린 파일이 사진이 아닌 경우 => menubasic.jpg
				menu.setMenusajin(menuPath + "menubasic.jpg");
			}

		} else {
			// 메뉴사진을 안올린 경우 => menubasic.jpg
			menu.setMenusajin(menuPath + "menubasic.jpg");
		}
		dao.insert(menu);
	}

	// 메뉴업뎃
	public int menuUpdate(MenuDto.DtoForRead dto, MultipartFile sajin) throws IllegalStateException, IOException {
		MenuEntity menu = modelMapper.map(dto, MenuEntity.class);
		
		if (sajin != null && !sajin.isEmpty()) {
			if (sajin.getContentType().toLowerCase().startsWith("image/") == true) {
				int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
				String extension = sajin.getOriginalFilename().substring(lastIndexOfDot + 1);
				File file = new File(menuFolder, menu.getMenuname() + "." + extension);
				sajin.transferTo(file);
				menu.setMenusajin(menuPath + file.getName());
			}
		}
		return dao.update(menu);
	}

	// 메뉴삭제
	public int menuDelete(int menuno) {
		return dao.delete(menuno);
	}

	/////////////////////////////////////////입점신청//////////////////////////////////////////////////////////////
	// 입점신청추가
	public int write(ManagerDto.DtoForWrite dto) {
		ManagerDto.DtoForWrite storeapplyInsert = modelMapper.map(dto, ManagerDto.DtoForWrite.class);
		storeapplyInsert.setMIrum(storeApplyInsertDao.findByIrum(storeapplyInsert.getMNum()));
		storeapplyInsert.setMTel(storeApplyInsertDao.findByTel(storeapplyInsert.getMNum()));
		storeapplyInsert.setMEmail(storeApplyInsertDao.findByEmail(storeapplyInsert.getMNum()));
		storeApplyInsertDao.insert(storeapplyInsert);
		return storeapplyInsert.getINo();////////////입점신청번호를 리턴
		
	}
	//////////////////////////////////////사업자 내정보 ///////////////////////////////////////////////////
	//내 정보 수정
	public int update(ManagerEntity manager) {
		return managerDao.update(manager);
		
	}
	
	//내 정보 읽기
	public ManagerEntity read(int mNum) {
		ManagerEntity manager = managerDao.findById(mNum);
		
		return manager;
	}
	
}
