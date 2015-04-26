package de.pburke;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Example EchoSocket using Annotations.
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class Socket {

    @OnWebSocketMessage
    public void onText(Session session, String s) {
        if (!session.isOpen()) return;

        if (s.equals("---ping")) {
            System.out.println("Received ping.");
        } else {
            System.out.printf("Echoing back message [%s]%n", s);
            session.getRemote().sendString(s, null);
        }
    }
}
