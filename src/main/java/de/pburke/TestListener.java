package de.pburke;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class TestListener extends WebSocketAdapter {

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);

        SessionRegistry.addSession(session);
    }

    @Override
    public void onWebSocketText(String s) {
        if (!isConnected()) return;

        if (s.equals("---ping")) {
            //System.out.println("Received ping.");
        } else {
            //System.out.printf("Echoing back message [%s]%n", s);
            SessionRegistry.sendMessage(s);
        }
    }
}
