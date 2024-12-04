package chat.server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;

public class SessionManager {
    private List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public synchronized void sendAll(Session sender, String message) {
        for (Session session : sessions) {
            if (session == sender) {
                continue;
            }
            if (session.getUsername() == null || session.getUsername().isEmpty()) {
                continue;
            }
            try {
                session.send(message);
            } catch (IOException e) {
                log(e);
            }

        }
    }

    public synchronized List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getUsername() != null) {
                users.add(session.getUsername());
            }
        }
        return users;
    }
}
