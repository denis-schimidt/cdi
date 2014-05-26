package com.schimidtsolutions.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketEndPoint {
	
	@OnMessage
	public String hello( final String message ) {
		System.out.println( "Received:  " + message );
		return message;
	}
	
	@OnOpen
	public void myOnOpen( final Session session ) {
		System.out.println( "WebSocket opened: " + session.getId() );
	}
	
	@OnClose
	public void myOnClose( final CloseReason reason ) {
		System.out.println( "Closing a WebSocket due to " + reason.getReasonPhrase() );
	}
}
