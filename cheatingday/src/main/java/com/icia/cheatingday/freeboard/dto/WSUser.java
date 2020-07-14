package com.icia.cheatingday.freeboard.dto;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
public class WSUser {
	private String id;
	private List<WebSocketSession> list = new Vector<WebSocketSession>();
	
	public WSUser(String id, WebSocketSession session) {
		this.id = id;
		list.add(session);
	}
	public void add(WebSocketSession session) {
		list.add(session);
	}
	public void remove(WebSocketSession session) {
		list.remove(session);
	}
	public int getCount() {
		return list.size();
	}
	public void send(String msg) {
		TextMessage message = new TextMessage(msg);
		for(WebSocketSession session:list) {
			try {
				session.sendMessage(message);
			} catch (IOException e) {
				log.info("{}",e.getMessage());
			}
		}
	}
}
