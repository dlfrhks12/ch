package com.icia.cheatingday.user.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class FavoriteDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int insertFavorite(String uUsername, int sNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sNum", sNum);
		return tpl.insert("favoriteMapper.insertFavorite", map);
	}
	public int favoriteCount(String uUsername) {
		return tpl.selectOne("favoriteMapper.favoriteCount", uUsername);
	}
	public int findFavoriteById(String uUsername, int sNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sNum", sNum);
		return tpl.selectOne("favoriteMapper.findFavoriteById", map);
	}
	
}
