package com.icia.cheatingday.freeboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatController extends TextWebSocketHandler {
	List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	//클라이언트가 접속할 시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		for(WebSocketSession s: list)
			s.sendMessage(new TextMessage(message.getPayload()+""));
	}
	//클라이언트가 접속을 끊을 시
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
	}
}
