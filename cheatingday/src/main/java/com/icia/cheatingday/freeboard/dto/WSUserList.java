package com.icia.cheatingday.freeboard.dto;

import java.util.List;
import java.util.Vector;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class WSUserList {
	private List<WSUser> list = new Vector<>();
	public void add(WebSocketSession session) {
		String id = session.getPrincipal().getName();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getId().equals(id)) {
				list.get(i).add(session);
				return;
			}
		}
		WSUser user = new WSUser(id,session);
		list.add(user);
	}
	public void remove(WebSocketSession session) {
		String id = session.getPrincipal().getName();
		for(int i = 0;i<list.size();i++) {
			WSUser u = list.get(i);
			if(u.getId().equals(id)) {
				if(u.getCount()==1)
					list.remove(u);
				else
					u.remove(session);
			}
		}
	}
	public void sendAll(String msg) {
		for(WSUser user: list)
			user.send(msg);
	}
}
