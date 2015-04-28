package de.pburke;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class ChatListener extends WebSocketAdapter {

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);

        SessionRegistry.addSession(session);
    }

    @Override
    public void onWebSocketText(String s) {
        if (!isConnected() || s.equals("---ping")) return;

        SessionRegistry.sendMessage(s);
    }
}
