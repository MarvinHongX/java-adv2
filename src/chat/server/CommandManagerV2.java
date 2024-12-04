package chat.server;

import chat.server.command.ChangeCommand;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {
    private static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String message, Session session) throws IOException {
        if (message.startsWith("/join")) {
            String[] split = message.split(DELIMITER);
            String username = split[1];
            session.setUsername(username);
            sessionManager.sendAll(session, username + " joined");
        } else if (message.startsWith("/message")) {
            String[] split = message.split(DELIMITER);
            String msg = split[1];
            sessionManager.sendAll(session, "[" + session.getUsername() + "] " + msg);
        } else if (message.startsWith("/change")) {
            String[] split = message.split(DELIMITER);
            String username = split[1];
            String oldUsername = session.getUsername();
            session.setUsername(username);
            sessionManager.sendAll(session, oldUsername + " changed to " + username);
        } else if (message.startsWith("/users")) {
            List<String> Users = sessionManager.getAllUsers();
            StringBuilder sb = new StringBuilder();
            sb.append("total active user: ").append(Users.size()).append("\n");
            for (String user : Users) {
                sb.append(" - ").append(user).append("\n");
            }
            session.send(sb.toString());
        } else if (message.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("Unknown command: " + message);
        }
    }
}
