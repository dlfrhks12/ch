package com.icia.cheatingday.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.icia.cheatingday.freeboard.dto.WSUser;
import com.icia.cheatingday.freeboard.dto.WSUserList;

public class MessagingHandler extends TextWebSocketHandler{
	WSUserList list = new WSUserList();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(session);
		System.out.println(list);
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
		System.out.println("ddddddddddddddddddddddddddddddddd");
		System.out.println(session);
		System.out.println(status);
		System.out.println(list);
	}
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String username = session.getPrincipal().getName();
		String msg = username +":"+message.getPayload();
		list.sendAll(msg);
		System.out.println("dkddddddddddddddddddddddddddddddddddddddddddkkkkkkkk");
		System.out.println(session);
		System.out.println(msg);
		System.out.println(list);
	}
}
 