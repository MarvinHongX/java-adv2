package chatEx.server;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {
    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
    }

    public void broadcastMessage(Session sender, String message) {
        for (Session session : sessions) {
            if (session == sender) {
                continue;
            }

            if (session.getUsername() == null || session.getUsername().isEmpty()) {
                continue;
            }

            session.broadcastMessage(message);
        }
    }
}
