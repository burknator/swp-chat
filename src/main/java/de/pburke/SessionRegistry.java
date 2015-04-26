package de.pburke;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;

public class SessionRegistry {
    protected static ArrayList<Session> sessions = new ArrayList<Session>();

    public static void addSession(Session session)
    {
        sessions.add(session);
    }

    public static void sendMessage(String message)
    {
        for (Session s : sessions) {
            try {
                s.getRemote().sendString(message);
            } catch(IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
