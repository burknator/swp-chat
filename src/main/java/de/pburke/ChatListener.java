package de.pburke;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class ChatListener extends WebSocketAdapter {

    /**
     * Wird ausgeführt, wenn erfolgreich eine Verbindung zu einem Browser hergestellt wurde. Sozusagen das
     * Server-Gegenstück zu "connection.onopen" in app.js
     * @param session Session Ein Objekt das Informationen zu der neuen Verbindung enthält (von Java, das war ich nicht).
     */
    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);

        // Alle Verbindungen werden in einer "großen Liste" gespeichert
        SessionRegistry.addSession(session);
    }

    /**
     * Wird ausgeführt, wenn der Server eine Nachricht vom Client (dem Browser) bekommt. Also wenn der Benutzer auf
     * "Senden" klickt, bzw. Enter drückt.
     * @param s String Die Nachricht vom Client.
     */
    @Override
    public void onWebSocketText(String s) {
        if (!isConnected() || s.equals("---ping")) return;

        // Diese Nachricht an alle aufgenommenen Verbindungen weiterleiten
        SessionRegistry.sendMessage(s);
    }
}
