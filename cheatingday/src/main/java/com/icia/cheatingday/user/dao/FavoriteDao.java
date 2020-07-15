package com.icia.cheatingday.user.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.user.entity.*;

@Repository
public class FavoriteDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int favoriteIn(String uUsername,int sNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sNum", sNum);
		return tpl.insert("favoriteMapper.favoriteIn", map);
	}
	public int favoriteCount(String uUsername) {
		return tpl.selectOne("favoriteMapper.favoriteCount", uUsername);
	}
	public boolean findFavoriteById(String uUsername, int sNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sNum", sNum);
		return tpl.selectOne("favoriteMapper.findFavoriteById", map);
	}
	public int deleteFavorite(int sNum, String uUsername) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sNum", sNum);
		return tpl.delete("favoriteMapper.deleteFavorite", map);
	}
	public boolean existsByFav(String uUsername) {
		return tpl.selectOne("favoriteMapper.existsByFav", uUsername);
	}
	public boolean favCheck(String uUsername, int sNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", uUsername);
		map.put("sName", sNum);
		return tpl.selectOne("favoriteMapper.favCheck", map);
	}
	
}
