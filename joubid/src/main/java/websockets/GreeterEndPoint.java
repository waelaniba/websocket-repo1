package websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hello")
public class GreeterEndPoint {
	
	@OnOpen
	public void onOpen(Session session) {

		System.out.println("New session id: " + session.getId());
	}
	
	@OnClose
	public void onClose(Session session){
		
		System.out.println("Closed session id: " + session.getId());
		
	}
	
	@OnMessage
	public void onMessage(Session session, String msg){
		
		for (Session s : session.getOpenSessions()) {
			
			try {
				s.getBasicRemote().sendText("received: " + msg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		
		System.out.println("Error ....");
	}
	

	
}
