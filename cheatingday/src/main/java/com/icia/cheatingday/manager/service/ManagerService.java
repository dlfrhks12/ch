package com.icia.cheatingday.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.authority.dao.AuthorityDao;
import com.icia.cheatingday.exception.JobFailException;
import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.manager.dao.ManagerStoreApplyInsertDao;
import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.ManagerDto.DtoForUpdate;
import com.icia.cheatingday.manager.dto.MenuDto;
import com.icia.cheatingday.manager.entity.ManagerEntity;
import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.exception.sNumNotFoundException;

@Service
public class ManagerService {
	// 메뉴
	@Autowired
	private MenuDao dao;
	@Value("c:/upload/menusajin")
	private String menuFolder;
	@Value("http://localhost:8081/menusajin/")
	private String menuPath;
	@Autowired
	private PasswordEncoder pwdEncoder;
	// 입점신청
	@Autowired
	private ManagerStoreApplyInsertDao storeApplyInsertDao;
	@Autowired
	private ModelMapper modelMapper;
	// 내정보 수정
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private AuthorityDao authDao;
	@Autowired
	private MenuDao menuDao;
	
	///////////////////////////메뉴/////////////////////////////////////
	// 메뉴리스트
	public List<MenuEntity> menuList(String mUsername) {
		//음식점에서 m_username으로 s_num을 찾아야해
		System.out.println("==============");
		System.out.println(storeDao.findBymUsername(mUsername).getSNum());//mUsername이 null이래
		System.out.println("+++++++++++");
		//if(mUsername==null)
		//	throw new sNumNotFoundException("매장 등록 후 이용 가능합니다");
		int sNum = storeDao.findBymUsername(mUsername).getSNum(); 
		System.out.println(sNum);
		//if(sNum==0) //음식점이 등록되지 않아 sNum이 null일때,
		//	throw new sNumNotFoundException("매장 등록 후 이용 가능합니다");
		
		List<MenuEntity> list = dao.findAllBymUsername(sNum);   
		return list;
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
	public void update(DtoForUpdate dto) {
		if(dto.getMPassword()!=null) {
			ManagerEntity managerEntity = managerDao.findById(dto.getMUsername());
			String encodedPassword = managerEntity.getMPassword();
			if(pwdEncoder.matches(dto.getMPassword(), encodedPassword)==false)
				throw new JobFailException("비밀번호를 확인할 수 없습니다.");
			dto.setMPassword(pwdEncoder.encode(dto.getNewMPassword()));
			
		}
		ManagerEntity managerEntity =modelMapper.map(dto, ManagerEntity.class);
		managerDao.update(managerEntity);
	}

	
    //내 정보 읽기
	public ManagerDto.DtoForRead read(String mUsername) { 
			ManagerEntity manager = managerDao.findById(mUsername); 
			ManagerDto.DtoForRead dto
			= modelMapper.map(manager, ManagerDto.DtoForRead.class);
			return dto; 
			}

	
	//사업자 탈퇴
	public void resign(String mUsername) {
		if(managerDao.findById(mUsername)==null)
			throw new JobFailException("사업자 회원 탈퇴에 실패했습니다");
		managerDao.deleteByMusername(mUsername);
		authDao.delete(mUsername);
		
	}

}
