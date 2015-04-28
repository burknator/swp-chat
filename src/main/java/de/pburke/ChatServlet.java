package de.pburke;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ChatServlet", urlPatterns = {"/socket"})
public class ChatServlet extends WebSocketServlet {
    
    @Override
    public void configure(WebSocketServletFactory factory) {
        // Hier wird der Idle-Timeout festgelegt: Wird für diese Zeit keine Nachricht vom Browser an den Server
        // geschickt, trennt der Server die Verbindung.
        factory.getPolicy().setIdleTimeout(10000);
        factory.register(ChatListener.class);
    }
}
