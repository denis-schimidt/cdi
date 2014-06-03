package com.schimidtsolutions.websocket;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.WebSocketProtocolHandshakeHandler;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.spi.WebSocketHttpExchange;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WebSocketEndPointTest {
	private Undertow server;

	@BeforeClass
	public void init() {
		server = Undertow.builder()
				.addHttpListener(8080, "localhost")
				.setHandler( getWebSocketHandler() )
				.build();
        server.start();
	}

	private PathHandler getWebSocketHandler() {
		
		return new PathHandler().addPrefixPath( "/estudo-rest/websocket", new WebSocketProtocolHandshakeHandler( 
			new WebSocketConnectionCallback() {

				@Override
				public void onConnect( WebSocketHttpExchange exchange, WebSocketChannel channel ){
					
						
				}
			})	
		);		
	}

	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" },
				new Object[] { 2, "b" }, };
	}

	@AfterClass
	public void close() {
		server.stop();
	}
}
