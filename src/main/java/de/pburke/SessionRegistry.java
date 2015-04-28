package de.pburke;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;

public class SessionRegistry {
    protected static ArrayList<Session> sessions = new ArrayList<Session>();

    public static void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Nachricht an alle in dieser Registry gespeicherten Verbindungen senden.
     * @param message String
     */
    public static void sendMessage(String message) {
        for (Session session : sessions) {
            try {
                // Hier wird die Nachricht an die Session geschickt
                session.getRemote().sendString(message);
            } catch(IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
